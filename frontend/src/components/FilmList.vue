<template>
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
</template>

<script>
export default {
  name: 'FilmList',
  props: {
    films: Array,
  },
};
</script>
