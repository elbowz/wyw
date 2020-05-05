<template>
  <div>
    <!-- Alternative to the <b-row
    <b-card-group v-if="films.length" class="card-deck-film" deck>
      <b-card v-for="film in films" :key="film.imdbID"
              class="mb-2 hvr-shadow"
              no-body
      >
        <b-card-img :src="film.poster" :alt="film.title" top></b-card-img>
        <b-card-body class="p-0" style="min-height: 0;"></b-card-body>
        <template v-slot:footer>
          <b-card-title class="h6">{{film.title}}</b-card-title>
          <b-card-sub-title>{{film.year}}</b-card-sub-title>
        </template>
      </b-card>
      keep reduced dimension of card when there are less of 4 cards
      <b-card class="invisible"></b-card>
      <b-card class="invisible"></b-card>
      <b-card class="invisible"></b-card>
    </b-card-group> -->

    <b-row v-if="films.length"
           cols="1" cols-sm="2" cols-md="3" cols-lg="4" cols-xl="4"
           class="d-flex flex-wrap justify-content-start align-items-stretch">
      <div v-for="film in films" :key="film.imdbID" class="p-2">
        <FilmItemCard :film="film"/>
      </div>
    </b-row>

    <div v-if="films.length">
      <div
        v-for="film in films"
        :key="film.imdbID"
      >
        <router-link :to="{ name: 'film', params: { id: film.imdbID }}">{{ film.title }}</router-link>
        <span v-if="film.createdAt"> {{film.createdAt | date}}</span>
      </div>
    </div>
    <div v-else>
      <h2>No films</h2>
    </div>
  </div>
</template>

<script>
import FilmItemCard from './FilmItemCard.vue';

export default {
  name: 'FilmList',
  components: { FilmItemCard },
  props: {
    films: Array,
  },
};
</script>

<style lang="scss" scoped>
.card-deck-film {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  grid-gap: .5rem;
}
</style>
