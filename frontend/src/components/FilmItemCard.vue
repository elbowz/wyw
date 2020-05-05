<template>
  <b-card
    @click="$router.push({ name: 'film', params: { id: film.imdbID }})"
    footer-class="bg-white"
    no-body
    class="card-film h-100 cursor-pointer hvr-shadow"
  >
    <div class="position-relative">
      <b-button @click.stop="" variant="light" class="btn-watched hvr-icon-bounce">
        <b-icon icon="eye-fill" aria-label="watched" class="hvr-icon"></b-icon>
      </b-button>
      <b-card-img :src="film.poster" :alt="film.title" class="poster" top></b-card-img>
    </div>

    <b-card-body class="card-body"></b-card-body>
    <template v-slot:footer>
      <b-card-title class="h6">{{film.title}}</b-card-title>
      <b-card-sub-title>{{film.year}}</b-card-sub-title>
    </template>
  </b-card>
</template>

<script>
export default {
  name: 'FilmItemCard',
  props: {
    film: {},
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
    opacity: 0.6;
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
