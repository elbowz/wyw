<template>
  <div>
    <div v-if="loading" class="loading">Loading...</div>
    <UserList v-else :users="users"/>

    <div v-if="error" class="error">
      {{ error }}
    </div>
  </div>
</template>

<script>
import ApiService from '../common/api.service';
import UserList from '../components/UserList.vue';

export default {
  name: 'Users',
  components: { UserList },
  data() {
    return {
      loading: false,
      users: [],
      error: null,
    };
  },
  created() {
    // fetch the data when the view is created
    this.fetchUsers();
  },
  watch: {
    // call again the method if the route changes
    $route: 'fetchUsers',
  },
  methods: {
    fetchUsers() {
      this.error = null;
      this.users = [];
      this.loading = true;
      // TODO: implement query?
      //console.log(this.$route.query)

      ApiService.get('/userservice/user')
        .then((films) => {
          this.loading = false;
          this.users = films;
        })
        .catch((error) => {
          this.error = error.toString();
        });
    },
  },
};
</script>
