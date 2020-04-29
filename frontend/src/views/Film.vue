<template>
  <div class="film">
    <div v-if="loading" class="loading">Loading...</div>
    <FilmItem v-else :film="film"/>

    <div v-if="error" class="error">
      {{ error }}
    </div>
  </div>
</template>

<script>
import ApiService from '../common/api.service';
import FilmItem from '../components/FilmItem.vue';

export default {
  name: 'Film',
  components: { FilmItem },
  props: {
    id: String,
  },
  data() {
    return {
      loading: false,
      film: {},
      error: null,
    };
  },
  created() {
    // fetch the data when the view is created
    this.fetchFilm();
  },
  methods: {
    fetchFilm() {
      this.error = null;
      this.films = [];
      this.loading = true;

      ApiService.get('/filmservice/film/' + this.id)
        .then((film) => {
          this.loading = false;
          this.film = film;
        })
        .catch((error) => {
          this.error = error.toString();
        });
    },
  },
};
</script>
