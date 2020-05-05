<template>
  <div>
    <h3>Films <small v-if="query" class="font-weight-light">with '{{query}}' in the title</small></h3>
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

      const params = this.query ? { query: this.query } : {};

      ApiService.get('/filmservice/film', params)
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
