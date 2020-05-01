<template>
  <div>
    <input v-model="query" v-on:keyup.enter="search"><button @click="search">search</button>
    <div v-if="loading" class="loading">Loading...</div>
    <FilmList v-else :films="films"/>

    <div v-if="error" class="error">
      {{ error }}
    </div>
  </div>
</template>

<script>
import ApiService from '../common/api.service';
import FilmList from '../components/FilmList.vue';

export default {
  name: 'Films',
  components: { FilmList },
  data() {
    return {
      loading: false,
      error: null,
      films: [],
      query: '',
    };
  },
  created() {
    // fetch the data when the view is created
    this.fetchFilms();
  },
  watch: {
    // call again the method if the route changes
    '$route.query.query': 'fetchFilms',
  },
  methods: {
    fetchFilms() {
      this.loading = true;
      this.error = null;
      this.query = this.$route.query.query;
      this.films = [];

      ApiService.get(`/filmservice/film${this.query ? '?query=' + this.query : ''}`)
        .then((films) => {
          this.loading = false;
          this.films = films;
        })
        .catch((error) => {
          this.error = error.toString();
        });
    },
    search() {
      this.$router.push({ query: { query: this.query } });
    },
  },
};
</script>
