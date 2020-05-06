<template>
  <b-row class="text-center mb-3" style="padding-top: 80px;">
    <b-col md="4" class="mx-md-auto">
      <h1><b-icon icon="people-circle" aria-label="Login" variant="dark" font-scale="2"></b-icon></h1>
      <h3 class="mb-4">Sign in</h3>

      <form @submit.prevent="login">
        <div class="mb-2">
          <b-form-input v-model="email"
                        :state="errorState"
                        type="email" aria-describedby="input-email-help" placeholder="email@domain.com"
                        autofocus required></b-form-input>
          <!-- <b-form-text id="input-email-help">email address used for registration</b-form-text>-->
        </div>
        <div class="mb-3">
          <b-form-input v-model="password"
                        :state="errorState"
                        type="password"
                        aria-describedby="input-password-help input-password-feedback" placeholder="password"
                        required></b-form-input>
          <!--<b-form-text id="input-password-help">at least 6 characters</b-form-text>-->
          <b-form-invalid-feedback id="input-password-feedback">{{ error }}</b-form-invalid-feedback>
        </div>

        <b-button variant="outline-dark" type="submit" block>Login</b-button>
      </form>
    </b-col>
  </b-row>
</template>

<script>
import { Auth } from '../common/api.service';

export default {
  name: 'Login',
  data() {
    return {
      email: null,
      password: null,
      error: null,
    };
  },
  methods: {
    login() {
      const { email, password } = this;
      Auth.login(email, password)
        .then(() => {
          this.$router.push({ path: this.$route.query.redirect || '/user/profile' });
        })
        .catch(() => {
          this.error = 'username or password are wrong!';
        });
    },
  },
  computed: {
    errorState() {
      return this.error == null ? null : false;
    },
  },
};
</script>
