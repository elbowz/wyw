<template>
  <div class="film">
    <FilmItem v-if="film.imdbID" :film="film"/>

    <div v-if="error">
      <b-alert show="6" variant="warning" dismissible fade>
        {{ error }}
      </b-alert>
    </div>
  </div>
</template>

<script>
import { ApiService } from '../common/api.service';
import FilmItem from '../components/FilmItem.vue';

export default {
  name: 'Film',
  components: { FilmItem },
  props: {
    id: String,
  },
  data() {
    return {
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

      ApiService.get(`/filmservice/film/${this.id}`, { deep: 1 })
        .then((film) => {
          this.film = film;
        })
        .catch((error) => {
          this.error = error.toString();
        });
    },
  },
};
</script>
