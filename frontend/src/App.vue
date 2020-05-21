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
    <TheSidebar :requests="$root.store.requestHistory"/>
  </div>
</template>

<script>
// @ is an alias to /src
import TheHeader from './components/TheHeader.vue';
import TheFooter from './components/TheFooter.vue';
import TheSidebar from './components/TheSidebar.vue';

export default {
  components: {
    TheSidebar,
    TheFooter,
    TheHeader,
  },
};
</script>

<style lang="scss">
main.container {
  margin-top: 80px;
  margin-bottom: 80px;
}
</style>
