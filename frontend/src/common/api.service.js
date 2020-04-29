import { API_URL } from './config';
import Store from './store';

export const ApiBaseService = {
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
  async get(path = '') {
    return fetch(API_URL + path, this.fetchInitDefault)
      .then((response) => {
        if (response.ok) return response.json();
        throw response;
      });
  },
  async post(path = '', data = {}) {
    return fetch(API_URL + path, {
      ...this.fetchInitDefault,
      method: 'POST',
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (response.ok) return response.json();
        throw response;
      });
  },
};

export default ApiBaseService;

export const Auth = {
  tokenKey: 'user-token',
  async login(email, password) {
    return ApiBaseService.post('/userservice/user/login', { email, password })
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

