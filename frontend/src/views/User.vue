<template>
  <div class="film">
    <div v-if="loading" class="loading">Loading...</div>
    <div v-else>
      <span>{{ user.firstName }}</span>
    </div>

    <div v-if="error" class="error">
      {{ error }}
    </div>
  </div>
</template>

<script>
import ApiService from '../common/api.service';

export default {
  name: 'User',
  props: {
    id: String,
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
      this.error = null;
      this.user = [];
      this.loading = true;

      // TODO: swap 1 with the local storage
      const id = this.id || this.$root.store.user.id;

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
