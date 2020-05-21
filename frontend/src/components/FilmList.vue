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
    </b-card-group>
    .card-deck-film {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    grid-gap: .5rem;
    } -->
    <!-- last line of class (row ... row-cols-xl-4) simulate the use of <b-row> not usable with transition-group -->
    <transition-group v-if="films.length" name="list-complete" tag="div"
                      cols="1" cols-sm="2" cols-md="3" cols-lg="4" cols-xl="4"
                      class="flex-wrap justify-content-start align-items-stretch
                      row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-4"
    >
      <div v-for="film in films" :key="film.imdbID" class="p-2 list-complete-item">
        <FilmItemCard :film="film"/>
      </div>
    </transition-group>
    <div v-else-if="!$root.store.loading">
      <b-card class="hvr-shadow text-center">
        <span class="h3">No Films</span>
      </b-card>
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
.list-complete-item {
  transition: all 1s;
}

.list-complete-enter, .list-complete-leave-to {
  transform: translateY(30px);
  opacity: 0;
}

.list-complete-move {
  transition: transform .6s;
}

.list-complete-leave-active {
  position: absolute;
}
</style>
