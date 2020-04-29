<template>
  <div id="nav">
    <b-icon icon="circle-fill" animation="throb" font-scale="4"></b-icon>
    |
    <router-link to="/">Home</router-link>
    |
    <router-link to="/about">About</router-link>
    |
    <router-link :to="{ name: 'users' }">Users</router-link>
    |
    <router-link :to="{ name: 'profile' }">Profile</router-link>
    |
    <router-link :to="{ name: 'mywatched' }">Watched</router-link>
    |
    <router-link :to="{ name: 'films' }">Films</router-link>
    |
    <span v-if="user">{{ user }} <a href="" @click.prevent="logout">Logout</a></span>
    <router-link v-else :to="{ name: 'login' }">Login</router-link>
  </div>
</template>

<script>
import { Auth } from '../common/api.service';

export default {
  name: 'TheHeader',
  methods: {
    logout() {
      Auth.logout();
      this.$router.push({ path: '/' });
    },
  },
  computed: {
    user() { return this.$root.store.user && this.$root.store.user.firstName; },
  },
};
</script>
