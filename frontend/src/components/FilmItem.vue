<template>
  <div>
    <b-row>
      <b-col sm="4">
        <b-img :src="'poster/' + film.poster | cdn" :alt="film.title" left rounded class="mr-2" fluid-grow></b-img>
      </b-col>
      <b-col sm="8">
        <h3>{{film.title}} <span class="font-weight-light">({{film.year}})</span></h3>
        <div v-if="film.ratings" class="mb-2">
          <span v-for="rating in film.ratings" :key="rating.source" class="mr-2">
            <span class="font-weight-light">{{rating.source}}: </span>
            <span class="font-weight-bold text-info">{{rating.value}}</span>
          </span>
        </div>
        <a :href="'https://www.imdb.com/title/' + film.imdbID"><small><font-awesome-icon :icon="['fab', 'imdb']" />
          imdb.com</small></a>
        <div class="mt-2">
          <span class="font-weight-light">Language: </span>
          <span class="font-weight-bold text-capitalize">{{film.language}}</span>
        </div>
        <div>
          <span class="font-weight-light">Production: </span>
          <span class="font-weight-bold text-capitalize">{{film.production}}</span>
        </div>
        <div v-if="film.people" class="mt-2">
          <span class="font-weight-light">Cast: </span>
          <div v-for="person in film.people" :key="(person.person.id+person.role)">
            {{person.person.firstName}} {{person.person.lastName}}
            <small class="text-capitalize">({{person.role}})
            </small>
          </div>
        </div>
        <div class="text-capitalize mt-3">
          {{film.plot}}
        </div>
        <b-button v-if="$root.store.user.id" @click="watched"
                  variant="outline-primary" aria-label="add to watched" class="mt-3">
          <b-icon icon="eye-fill" aria-label="watched"></b-icon>
          Watched
        </b-button>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { ApiService } from '../common/api.service';

export default {
  name: 'FilmItem',
  props: {
    film: {},
  },
  methods: {
    watched() {
      const body = {
        userId: this.$root.store.user.id,
        filmId: this.film.imdbID,
      };

      ApiService.post('/watchedservice/watched', {}, body)
        .then(() => {
          this.$bvToast.toast(`${this.film.title} added in your watched list`,
            { title: 'Info' });
        })
        .catch((error) => {
          // TODO: should be manage different error
          this.$bvToast.toast(`${this.film.title} is already in your watched list`,
            { title: 'Error', variant: 'danger', noAutoHide: true });
        });
    },
  },
};
</script>
