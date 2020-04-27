import Vue from 'vue';
// TODO: add only the used modules (eg. import { AlertPlugin } from 'bootstrap-vue')
// see: https://bootstrap-vue.js.org/docs#component-groups-and-directives-as-vue-plugins
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue';

import App from './App.vue';
import router from './router';

import './styles/index.scss';

// Install BootstrapVue
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(BootstrapVueIcons);

Vue.config.productionTip = false;

new Vue({
  router,
  render: (h) => h(App),
}).$mount('#app');
