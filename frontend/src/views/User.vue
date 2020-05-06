<template>
  <div>
    <b-row align-v="center">
      <b-col>
        <b-avatar :text="avatarText" size="lg" variant="primary"></b-avatar>
      </b-col>
      <b-col class="text-center">
        <span class="h3">{{ user.firstName }} {{ user.lastName }}</span>
        <div><a :href="`mailto:${user.email}`" @click.stop target="_blank">{{ user.email }}</a></div>
        <div class="text-muted font-weight-light">since {{ user.createdAt | date }}</div>
      </b-col>
      <b-col class="text-right">
        <b-button :to="{ name: 'watched', params: { id: user.id }}"
                  variant="outline-primary" aria-label="watched"
        >
          <b-icon icon="eye-fill" aria-label="watched"></b-icon>
          Watched List
        </b-button>
      </b-col>
    </b-row>

    <b-alert v-if="this.$root.store.user.id == user.id" class="mt-4" show fade dismissible>
      This is you personal profile, more information and action wil be added soon :)
    </b-alert>

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
    id: Number | String,
  },
  data() {
    return {
      user: {},
      error: null,
    };
  },
  created() {
    // fetch the data when the view is created
    this.fetchUser();
  },
  watch: {
    // call again the method if the route changes
    '$route.params': 'fetchUser',
  },
  methods: {
    fetchUser() {
      this.error = null;

      const id = this.id || Auth.isLogged();

      ApiService.get('/userservice/user/' + id)
        .then((user) => {
          this.user = user;
        })
        .catch((error) => {
          this.error = error.toString();
        });
    },
  },
  computed: {
    avatarText() {
      return this.user.id ? this.user.firstName.charAt(0) + this.user.lastName.charAt(0) : '';
    },
  },
};
</script>
