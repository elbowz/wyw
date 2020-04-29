<template>
  <div v-if="films.length">
    <div
      v-for="film in films"
      :key="film.imdbID"
    >
      <router-link :to="{ name: 'film', params: { id: film.imdbID }}">{{ film.title }}</router-link>
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
