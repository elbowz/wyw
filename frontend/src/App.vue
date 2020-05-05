<template>
  <div id="app">
    <header>
      <TheHeader/>
    </header>
    <main role="main" class="container">
      <b-container fluid="xl">
        <transition
          name="custom-classes-transition"
          enter-active-class="animated fadeIn faster"
          leave-active-class="animated fadeOut faster"
          mode="out-in"
        >
          <router-view/>
        </transition>
      </b-container>
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
main.container {
  margin-top: 80px;
}
</style>
