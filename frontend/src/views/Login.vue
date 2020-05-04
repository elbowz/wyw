<template>
  <div>
    <form @submit.prevent="login">
      <h1>Sign in</h1>
      <label>email</label>
      <input required v-model="email" type="text" placeholder="email@domain.com"/>
      <label>Password</label>
      <input required v-model="password" type="password" placeholder="Password"/>
      <button type="submit">Login</button>
    </form>
    <div v-if="error" class="error">
      {{ error }}
    </div>
  </div>
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
        .catch((error) => {
          this.error = 'username or password are wrong!';
        });
    },
  },
};
</script>
