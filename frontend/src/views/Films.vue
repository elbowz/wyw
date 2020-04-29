<template>
  <div>
    <div v-if="loading" class="loading">Loading...</div>
    <FilmList v-else :films="films"/>

    <div v-if="error" class="error">
      {{ error }}
    </div>
  </div>
</template>

<script>
import ApiService from '../common/api.service';
import FilmList from "../components/FilmList.vue";

export default {
  name: 'Films',
  components: { FilmList },
  data() {
    return {
      loading: false,
      films: [],
      error: null,
    };
  },
  created() {
    // fetch the data when the view is created
    this.fetchFilms();
  },
  watch: {
    // call again the method if the route changes
    $route: 'fetchFilms',
  },
  methods: {
    fetchFilms() {
      this.error = null;
      this.films = [];
      this.loading = true;
      // TODO: implement query?
      //console.log(this.$route.query)

      ApiService.get('/filmservice/film')
        .then((films) => {
          this.loading = false;
          this.films = films;
        })
        .catch((error) => {
          this.error = error.toString();
        });
    },
  },
};
</script>
