import Vue from 'vue';
import VueRouter from 'vue-router';

import { Auth } from '../common/api.service';
import Home from '../views/Home.vue';
import Film from '../views/Film.vue';
import Films from '../views/Films.vue';
import User from '../views/User.vue';
import Login from '../views/Login.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
  },
  { path: '/', name: 'home', component: Home },
  { path: '/login', name: 'login', component: Login },
  { path: '/user', name: 'users', component: Home },
  { path: '/user/profile', name: 'profile', component: User },
  { path: '/user/:id', name: 'user', component: User, props: true },
  { path: '/user/:id/watched', name: 'watched', component: Home },
  { path: '/film', name: 'films', component: Films },
  { path: '/film/:id', name: 'film', component: Film, props: true },
  { path: '*', name: '404', component: Home },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

const authRequired = ['profile'];

router.beforeEach((to, from, next) => {
  if (authRequired.includes(to.name) && !Auth.isLogged()) next({ name: 'login', query: { redirect: to.path } });
  else if (to.name === 'login' && Auth.isLogged()) next({ name: 'home' });
  else next();
});


export default router;
