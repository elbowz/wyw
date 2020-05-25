import Vue from 'vue';
import VueLodash from 'vue-lodash';
import lodash from 'lodash';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faImdb, faTwitter } from '@fortawesome/free-brands-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

library.add(faImdb, faTwitter);

// TODO: add only the used modules (eg. import { AlertPlugin } from 'bootstrap-vue')
// see: https://bootstrap-vue.js.org/docs#component-groups-and-directives-as-vue-plugins
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue';

import App from './App.vue';
import router from './router';
import Store from './common/store';
import { Auth } from './common/api.service';
import { date, truncate, cdn } from './common/filter';

import './styles/index.scss';
import './registerServiceWorker';

Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);
Vue.use(VueLodash, { lodash });
Vue.component('font-awesome-icon', FontAwesomeIcon);

// Auth Init (check localstorage and fulfill Store.user)
Auth.init();

Vue.config.productionTip = false;

Vue.filter('date', date);
Vue.filter('truncate', truncate);
Vue.filter('cdn', cdn);

new Vue({
  router,
  data: {
    store: Store,
  },
  render: (h) => h(App),
}).$mount('#app');
