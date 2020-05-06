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
            <b-form-input v-model="filterQuery"
                          placeholder="Filter" aria-label="Filter"
                          aria-describedby="input-filter-help"
            ></b-form-input>
            <b-input-group-append>
              <b-button type="submit">
                <b-icon icon="search" aria-label="Filter"></b-icon>
              </b-button>
            </b-input-group-append>
          </b-input-group>
          <b-form-text id="input-filter-help">enable for reactive filtering</b-form-text>
        </form>
      </b-col>
    </b-row>

    <FilmListWatched :films="filteredFilm" :user="user"/>

    <div v-if="error" class="error">
      {{ error }}
    </div>
  </div>
</template>

<script>
import { ApiService, Auth } from '../common/api.service';
import FilmListWatched from '../components/FilmListWatched';

export default {
  name: 'Watched',
  components: { FilmListWatched },
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
    '$route.params': 'fetchWatched',
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
          this.films = watched.map((row) => ({ ...row.film, ...{ createdAt: row.createdAt, watchedId: row.id } }));
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
