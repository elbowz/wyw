<template>
  <div>
    <span>{{ film.title }}</span>
  </div>
</template>

<script>
export default {
  name: 'FilmItem',
  props: {
    film: {},
  },
};
</script>
