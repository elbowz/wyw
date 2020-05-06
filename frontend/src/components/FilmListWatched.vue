<template>
  <div v-if="films.length">
    <b-card v-for="film in films" :key="film.imdbID"
            @click="$router.push({ name: 'film', params: { id: film.imdbID }})"
            class="cursor-pointer hvr-shadow mb-2"
    >
      <b-row>
        <b-col md="3" class="font-weight-bold">
          <span v-if="film">{{ film.title }}</span>
          <span v-else>{{ film.imdbID }}</span>
        </b-col>
        <b-col md="2" class="text-md-center">
          <span v-if="film">{{ film.year }}</span>
        </b-col>
        <b-col md="4" v-if="film">{{ film.plot | truncate(40) }}</b-col>
        <b-col md="3" class="text-md-right">{{ film.createdAt | date }}</b-col>
      </b-row>
    </b-card>
  </div>
  <div v-else-if="!$root.store.loading">
    <h2>No films</h2>
  </div>
</template>

<script>
export default {
  name: 'FilmListWatched',
  props: {
    films: {},
  },
};
</script>
