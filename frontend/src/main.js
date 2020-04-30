import Vue from 'vue';
// TODO: add only the used modules (eg. import { AlertPlugin } from 'bootstrap-vue')
// see: https://bootstrap-vue.js.org/docs#component-groups-and-directives-as-vue-plugins
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue';

import App from './App.vue';
import router from './router';
import Store from './common/store';
import { Auth } from './common/api.service';
import { date } from './common/filter';

import './styles/index.scss';

// Install BootstrapVue
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(BootstrapVueIcons);

// Auth Init (check localstorage and fulfill Store.user)
Auth.init();

Vue.config.productionTip = false;

Vue.filter('date', date);

new Vue({
  router,
  data: {
    store: Store,
  },
  render: (h) => h(App),
}).$mount('#app');
