<template>
  <div id="app">
    <header>
      <TheHeader/>
    </header>
    <main role="main" class="container">
      <transition
        name="custom-classes-transition"
        enter-active-class="animated fadeIn faster"
        leave-active-class="animated fadeOut faster"
        mode="out-in"
      >
        <b-container fluid="xl">
          <router-view/>
        </b-container>
      </transition>
    </main>
    <footer>
      <TheFooter/>
    </footer>
  </div>
</template>

<script>
// @ is an alias to /src
import TheHeader from './components/TheHeader.vue';
import TheFooter from './components/TheFooter.vue';

export default {
  components: {
    TheFooter,
    TheHeader,
  },
};
</script>

<style lang="scss">
.container {
  padding: 60px 15px 0;
}
</style>
