<template>
  <b-jumbotron bg-variant="white" class="text-center" fluid>
    <template v-slot:header>
      <div class="d-flex justify-content-center"></div>
      <div class="logo-wyw mx-auto"></div>
      <h1><strong>W</strong>hat<strong>Y</strong>ou<strong>W</strong>atched</h1>
    </template>

    <template v-slot:lead>
      Keep track what you and other people watching
    </template>

    <hr class="my-4">

    <p class="font-weight-light">
      You are able to find new films and tv series by exploring users timelines and rating by the most famous internet aggregator
    </p>

    <b-button variant="outline-dark" :to="{ name: 'login' }">Login to Start</b-button>
  </b-jumbotron>
</template>

<script>
export default {
  name: 'Home',
};
</script>
