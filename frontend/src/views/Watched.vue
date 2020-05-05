<template>
  <div>
    <div class="h3 clearfix mb-4">
      <span class="float-left">Watched films</span>
      <span class="font-weight-light float-right">
        <span v-if="user">{{ user.firstName }} {{ user.lastName }}</span>
        <span v-else>{{ user.id }}</span>
      </span>
    </div>

    <b-row class="mb-3">
      <b-col md="4" class="ml-md-auto">
        <form @submit.prevent="filter">
          <b-input-group>
            <b-input-group-prepend is-text>
              <b-form-checkbox v-model="switchFilter" class="mr-n2" switch>
                <span class="sr-only">Switch for enable filter</span>
              </b-form-checkbox>
            </b-input-group-prepend>
            <b-form-input v-model="filterQuery" placeholder="Filter" aria-label="Filter"></b-form-input>
            <b-input-group-append>
              <b-button type="submit">
                <b-icon icon="search" aria-label="Filter"></b-icon>
              </b-button>
            </b-input-group-append>
          </b-input-group>
        </form>
      </b-col>
    </b-row>

    <div v-if="filteredFilm.length">
      <b-card v-for="film in filteredFilm" :key="film.imdbID"
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

    <div v-if="error" class="error">
      {{ error }}
    </div>
  </div>
</template>

<script>
import { ApiService, Auth } from '../common/api.service';

export default {
  name: 'Watched',
  props: {
    id: Number,
  },
  data() {
    return {
      films: [],
      filteredFilm: [],
      user: {},
      filterQuery: '',
      switchFilter: false,
      error: null,
    };
  },
  created() {
    // fetch the data when the view is created
    this.fetchWatched();
  },
  watch: {
    switchFilter() {
      this.filter();
    },
    filterQuery() {
      if (this.switchFilter) this.filter();
    },
  },
  methods: {
    fetchWatched() {
      this.error = null;
      this.films = [];
      this.user = {};

      const id = this.id || Auth.isLogged();

      // Call for get information also when user have no watched film
      ApiService.get('/userservice/user/' + id)
        .then((user) => {
          this.user = user;
        })
        .catch((error) => {
          this.error = error.toString();
        });

      ApiService.get('/watchedservice/watched/' + id, { deep: 1 })
        .then((watched) => {
          this.films = watched.map((row) => ({ ...row.film, ...{ createdAt: row.createdAt } }));
          this.filter();
        })
        .catch((error) => {
          this.error = error.toString();
        });
    },
    filter() {
      this.filteredFilm = this.films.filter(
        (film) => film.title.toLowerCase().includes(this.filterQuery.toLowerCase()));
    },
  },
};
</script>
