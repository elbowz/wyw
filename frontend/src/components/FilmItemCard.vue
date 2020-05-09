<template>
  <b-card
    @click="$router.push({ name: 'film', params: { id: film.imdbID }})"
    footer-class="bg-trasparent"
    no-body
    class="card-film h-100 cursor-pointer hvr-shadow"
  >
    <div class="position-relative">
      <b-button v-if="$root.store.user.id" @click.stop="watched" variant="light"
                class="btn-watched hvr-icon-bounce"
      >
        <b-icon icon="eye-fill" aria-label="watched" class="hvr-icon"></b-icon>
      </b-button>
      <b-card-img :src="film.poster | cdn" :alt="film.title" class="poster" top></b-card-img>
    </div>

    <b-card-body class="card-body"></b-card-body>
    <template v-slot:footer>
      <b-card-title class="h6">{{film.title}}</b-card-title>
      <b-card-sub-title>{{film.year}}</b-card-sub-title>
    </template>
  </b-card>
</template>

<script>
import { ApiService } from "../common/api.service";

export default {
  name: 'FilmItemCard',
  props: {
    film: {},
  },
  methods: {
    watched() {
      const body = {
        userId: this.$root.store.user.id,
        filmId: this.film.imdbID,
      };

      ApiService.post('/watchedservice/watched', {}, body)
        .then(() => {
          this.$bvToast.toast(`${this.film.title} added in your watched list`,
            { title: 'Info' });
        })
        .catch((error) => {
          // TODO: should be manage different error
          this.$bvToast.toast(`${this.film.title} is already in your watched list`,
            { title: 'Error', variant: 'danger', noAutoHide: true });
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.card-film {
  .card-body {
    min-height: 0;
    padding: 0;
  }

  .btn-watched {
    position: absolute;
    top: 0;
    right: 0;
    margin: 4px;
    opacity: 0.2;
    transition: all .4s cubic-bezier(0.25, 1, 0.5, 1);
    z-index: 1;

    &:hover {
      opacity: .9;
    }
  }

  .poster {
    transition: all .4s cubic-bezier(0.25, 1, 0.5, 1);
  }

  &:hover .poster {
    opacity: .8;
  }
}
</style>
