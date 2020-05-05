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
  async get(path = '', params = {}) {
    Store.loading += 1;
    return fetch(this._urlGenerator(path, params), this.fetchInitDefault)
      .then((response) => {
        Store.loading -= 1;
        if (response.ok) return response.json();
        throw response;
      })
      .catch((reason) => {
        Store.loading -= 1;
        throw reason;
      });
  },
  async post(path = '', params = {}, data = {}) {
    Store.loading += 1;
    return fetch(this._urlGenerator(path, params), {
      ...this.fetchInitDefault,
      method: 'POST',
      body: JSON.stringify(data),
    })
      .then((response) => {
        Store.loading -= 1;
        if (response.ok) return response.json();
        throw response;
      })
      .catch((reason) => {
        Store.loading -= 1;
        throw reason;
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
    return ApiService.post('/userservice/user/login', { }, { email, password })
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
    Store.user = null;
  },
  isLogged() {
    return localStorage.getItem(this.tokenKey) || false;
  },
};
