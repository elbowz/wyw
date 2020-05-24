<template>
  <b-navbar toggleable="lg" type="dark" variant="light" fixed="bottom">
    <b-container fluid="xl">
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="mx-auto">
          <b-nav-text class="text-muted">
            <span>WhatYouWatched &copy; 2020</span>
            <span class="font-weight-light">
              - Written with passion, time and fight by {{creditRoulette[0]}} and {{creditRoulette[1]}}
            </span>
          </b-nav-text>
        </b-navbar-nav>
      </b-collapse>
    </b-container>
  </b-navbar>
</template>

<script>
export default {
  name: 'TheFooter',
  data() {
    return {
      credit: ['Emanuele Palombo', 'Luigi Cerone'],
    };
  },
  computed: {
    creditRoulette() {
      return _.shuffle(this.credit);
    },
  },
};
</script>
