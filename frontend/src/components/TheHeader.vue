<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="warning">
      <b-container fluid="lg">

        <b-navbar-brand :to="{ name: 'home' }">
          <b-icon icon="film" class="mr-2"></b-icon>
          <strong>W</strong>hat<strong>Y</strong>ou<strong>W</strong>atched
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

          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <b-nav-form class="mr-lg-3">
              <b-form-input size="sm" class="mr-sm-2" placeholder="Film search"></b-form-input>
              <b-button size="sm" class="my-2 my-sm-0" type="submit" variant="outline-dark">
                <b-icon icon="search" aria-label="Search"></b-icon>
              </b-button>
            </b-nav-form>

            <b-nav-item-dropdown v-if="user" right>
              <template v-slot:button-content>
                <b-avatar :text="avatarText" size="sm" variant="primary"></b-avatar> {{ user }}
              </template>
              <b-dropdown-item :to="{ name: 'profile' }">Profile</b-dropdown-item>
              <b-dropdown-item href="#" @click.prevent="logout">Sign Out</b-dropdown-item>
            </b-nav-item-dropdown>
            <b-nav-item v-else :to="{ name: 'login' }">
              <b-avatar icon="person-fill" size="sm" variant="primary"></b-avatar> Login
            </b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-container>
    </b-navbar>
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
    avatarText() { return this.$root.store.user && this.$root.store.user.firstName.charAt(0) + this.$root.store.user.lastName.charAt(0); },
  },
};
</script>
