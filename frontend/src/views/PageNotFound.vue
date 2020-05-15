<template>
  <div class="text-center pt-5">
    <div class="display-1">404 <b-icon icon="exclamation-triangle" aria-label="warning" class="animated headShake"></b-icon></div>
    <p class="lead text-uppercase">
      The page <span class="text-muted">{{this.$route.path}}</span> you were looking for doesn't exist.
    </p>
    <b-button variant="outline-dark" :to="{ name: 'home' }">Back to Home</b-button>
  </div>
</template>

<style lang="scss" scoped>
.display-1 {
  font-size: 10rem;
}
</style>
