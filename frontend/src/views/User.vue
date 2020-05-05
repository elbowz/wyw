<template>
  <div class="film">
    <div v-if="loading" class="loading">Loading...</div>
    <div v-else>
      <span>{{ user.firstName }}</span>
      -
      <router-link :to="{ name: 'watched', params: { id: user.id }}">watched</router-link>
    </div>

    <div v-if="error" class="error">
      {{ error }}
    </div>
  </div>
</template>

<script>
import { ApiService, Auth } from '../common/api.service';

export default {
  name: 'User',
  props: {
    id: Number,
  },
  data() {
    return {
      loading: false,
      user: {},
      error: null,
    };
  },
  created() {
    // fetch the data when the view is created
    this.fetchUser();
  },
  methods: {
    fetchUser() {
      this.loading = true;
      this.error = null;
      this.user = [];

      const id = this.id || Auth.isLogged();

      ApiService.get('/userservice/user/' + id)
        .then((user) => {
          this.loading = false;
          this.user = user;
        })
        .catch((error) => {
          this.error = error.toString();
        });
    },
  },
};
</script>
