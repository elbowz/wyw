<template>
  <b-navbar toggleable="lg" type="light" fixed="top" class="navbar">
    <b-container fluid="xl">
      <b-navbar-brand :to="{ name: 'home' }" class="logo-wyw small" :class="{ 'animated infinite heartBeat': loading }">
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item :to="{ name: 'home' }">Home</b-nav-item>
          <b-nav-item :to="{ name: 'profile' }">Profile</b-nav-item>
          <b-nav-item :to="{ name: 'users' }">Users</b-nav-item>
          <b-nav-item :to="{ name: 'films' }">Films</b-nav-item>
          <b-nav-item :to="{ name: 'mywatched' }">Watched</b-nav-item>
          <b-nav-item :to="{ name: 'About' }">About</b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav v-if="loading" class="font-weight-light mx-auto">
          loading...
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-form @submit.prevent="search" class="mr-lg-3">
            <b-form-input v-model="query" size="sm" class="mr-sm-2"
                          placeholder="Film search" autocomplete="off"></b-form-input>
            <b-button size="sm" class="my-2 my-sm-0" type="submit" variant="outline-dark">
              <b-icon icon="search" aria-label="Search"></b-icon>
            </b-button>
          </b-nav-form>

          <b-nav-item-dropdown v-if="user" right>
            <template v-slot:button-content>
              <b-avatar :text="avatarText" size="sm" variant="primary"></b-avatar>
              {{ user }}
            </template>
            <b-dropdown-item :to="{ name: 'profile' }">Profile</b-dropdown-item>
            <b-dropdown-item href="#" @click.prevent="logout">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
          <b-nav-item v-else :to="{ name: 'login' }">
            <b-avatar icon="person-fill" size="sm" variant="primary"></b-avatar>
            Login
          </b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-container>
  </b-navbar>
</template>

<script>
import { Auth } from '../common/api.service';

export default {
  name: 'TheHeader',
  data() {
    return {
      query: '',
    };
  },
  methods: {
    logout() {
      Auth.logout();
      this.$router.push({ path: '/' });
    },
    search() {
      this.$router.push({ name: 'films', query: { query: this.query } });
    },
  },
  computed: {
    user() {
      return this.$root.store.user && this.$root.store.user.firstName;
    },
    avatarText() {
      const user = this.$root.store.user;
      return user && user.firstName.charAt(0) + user.lastName.charAt(0);
    },
    loading() {
      return this.$root.store.loading;
    },
  },
};
</script>

<style scoped lang="scss">
@import '../styles/bootstrap-custom';

.navbar {
  transition: all .4s;
  background-color: rgba($light, .6);

  &:hover {
    background-color: rgba($warning, .9);
  }

  .brand-logo {
    background-image: url('../assets/logo-square-50x50.png');
    background-repeat: no-repeat;
    background-size: contain;
    padding: 0;
    height: 36px;
    width: 36px;
    transition: all .4s cubic-bezier(0.25, 1, 0.5, 1);

    &:hover {
      background-image: url('../assets/logo-square-colored-50x50.png');
    }
  }
}
</style>
