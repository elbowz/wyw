import { API_URL } from './config';
import Store from './store';

export const ApiService = {
  fetchInitDefault: {
    method: 'GET', // *GET, POST, PUT, DELETE, etc.
    mode: 'cors', // no-cors, *cors, same-origin
    cache: 'default', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, *same-origin, omit
    headers: {
      'Content-Type': 'application/json',
    },
    redirect: 'follow', // manual, *follow, error
    // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin
    referrerPolicy: 'no-referrer-when-downgrade',
  },
  _urlGenerator(path = '', params = {}) {
    const url = new URL(API_URL + path);
    Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));

    return url;
  },
  _findInstanceId(obj, instanceIds = new Set()) {
    if (_.has(obj, 'instanceId')) {
      instanceIds.add(obj.instanceId);
    } else if (_.has(obj, 'imdbID')) {
      instanceIds.add('omdb');
    }

    if (_.has(obj, 'ratings') && _.isArray(obj.ratings)) {
      instanceIds.add('omdb');
    }

    _.forEach(obj, (element) => {
      if (_.isObject(element)) this._findInstanceId(element, instanceIds);
    });

    return Array.from(instanceIds);
  },
  async _responseMiddleware(type, path, { params = null, data = null, response }) {

    let json = null;
    let instanceIds = [];

    try {
      json = await response.clone().json();
      instanceIds = this._findInstanceId(json);
    } catch (e) {
      console.log('no json body response');
    }

    Store.requestHistory.unshift({
      date: Date.now(),
      type,
      path,
      params,
      data,
      response: json,
      instanceIds,
    });

    return response;
  },
  async get(path = '', params = {}) {
    Store.loading += 1;
    return fetch(this._urlGenerator(path, params), this.fetchInitDefault)
      .then((response) =>
        this._responseMiddleware('GET', path, { params, response })
      )
      .then((response) => {

        if (Store.loading) Store.loading -= 1;
        if (response.ok) return response.json();

        throw response;
      })
      .catch(async (reason) => {
        if (Store.loading) Store.loading -= 1;
        throw await reason.text();
      });
  },
  async post(path = '', params = {}, data = {}) {
    Store.loading += 1;
    return fetch(this._urlGenerator(path, params), {
      ...this.fetchInitDefault,
      method: 'POST',
      body: JSON.stringify(data),
    })
      .then((response) =>
        this._responseMiddleware('POST', path, { params, data, response })
      )
      .then((response) => {

        if (Store.loading) Store.loading -= 1;
        if (response.ok) return response.json();

        throw response;
      })
      .catch(async (reason) => {

        if (Store.loading) Store.loading -= 1;
        throw await reason.text();
      });
  },
  async delete(path = '', params = {}) {
    Store.loading += 1;
    return fetch(this._urlGenerator(path, params), {
      ...this.fetchInitDefault,
      method: 'DELETE',
    })
      .then((response) =>
        this._responseMiddleware('DELETE', path, { params, response })
      )
      .then((response) => {

        if (Store.loading) Store.loading -= 1;
        if (response.ok) return true;

        throw response;
      })
      .catch(async (reason) => {

        if (Store.loading) Store.loading -= 1;
        throw await reason.text();
      });
  },
};

export default ApiService;

export const Auth = {
  tokenKey: 'user-id',
  async init() {
    const userId = this.isLogged();

    if (userId) {
      return ApiService.get('/userservice/user/' + userId)
        .then((user) => {
          Store.user = user;
          return user;
        })
        .catch((error) => {
          this.logout();
          throw error;
        });
    }

    return false;
  },
  async login(email, password) {
    return ApiService.post('/userservice/user/login', {}, { email, password })
      .then((user) => {
        localStorage.setItem(this.tokenKey, user.id);
        Store.user = user;

        return user;
      })
      .catch((error) => {
        this.logout();
        throw error;
      });
  },
  logout() {
    localStorage.removeItem(this.tokenKey);
    Store.user = {};
  },
  isLogged() {
    return localStorage.getItem(this.tokenKey) || false;
  },
};
