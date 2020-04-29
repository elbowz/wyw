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
import FilmList from '../components/FilmList.vue';

export default {
  name: 'Watched',
  components: { FilmList },
  props: {
    id: Number,
  },
  data() {
    return {
      loading: false,
      films: [],
      error: null,
    };
  },
  created() {
    // fetch the data when the view is created
    this.fetchWatched();
  },
  watch: {
    // call again the method if the route changes
    $route: 'fetchWatched',
  },
  methods: {
    fetchWatched() {
      this.error = null;
      this.films = [];
      this.loading = true;
      // TODO: implement query?
      // console.log(this.$route.query)

      const id = this.id || this.$root.store.user.id;

      ApiService.get('/watchedservice/watched/' + id)
        .then((watched) => {
          this.loading = false;

          this.films = watched.map((row) => row.film);
        })
        .catch((error) => {
          this.error = error.toString();
        });
    },
  },
};
</script>
