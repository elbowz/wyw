<template>
  <div>
    <div v-if="error">
      <b-alert show="6" variant="warning" dismissible fade>
        {{ error }}
      </b-alert>
    </div>

    <h3>Users</h3>

    <div v-if="users.length">
      <b-card v-for="user in users" :key="user.id"
              @click="$router.push({ name: 'user', params: { id: user.id }})"
              class="cursor-pointer hvr-shadow mb-2 w-100"
      >
        <b-row>
          <b-col md="6" class="font-weight-bold">
            <span>{{ user.firstName }} {{ user.lastName }}</span>
          </b-col>
          <b-col md="6" class="text-md-right">
            <a :href="`mailto:${user.email}`" @click.stop target="_blank">{{ user.email }}</a>
            <b-icon icon="envelope-open" aria-label="email" class="ml-1"></b-icon>
          </b-col>
        </b-row>
      </b-card>
    </div>
    <div v-else-if="!$root.store.loading">
      <b-card class="hvr-shadow text-center">
        <span class="h3">No Users</span>
      </b-card>
    </div>
  </div>
</template>

<script>
import { ApiService } from '../common/api.service';

export default {
  name: 'Users',
  data() {
    return {
      users: [],
      error: null,
    };
  },
  created() {
    // fetch the data when the view is created
    this.fetchUsers();
  },
  methods: {
    fetchUsers() {
      this.error = null;
      this.users = [];

      ApiService.get('/userservice/user')
        .then((users) => {
          this.users = users;
        })
        .catch((error) => {
          this.error = error.toString();
        });
    },
  },
};
</script>
