<template>
  <transition-group v-if="films.length" name="list-complete" tag="div">
    <b-card v-for="film in films" :key="film.watchedId"
            @click="$router.push({ name: 'film', params: { id: film.imdbID }})"
            class="cursor-pointer hvr-shadow mb-2 list-complete-item w-100"
    >
      <b-row>
        <b-col md="3" class="font-weight-bold">
          <span v-if="film.title">{{ film.title }}</span>
          <span v-else>{{ film.imdbID }}</span>
        </b-col>
        <b-col md="2" class="text-md-center">
          <span v-if="film">{{ film.year }}</span>
        </b-col>
        <b-col md="4" v-if="film">{{ film.plot | truncate(40) }}</b-col>
        <b-col md="3" class="text-md-right">
          {{ film.createdAt | date }}
          <b-button v-if="$root.store.user.id === user.id" @click.stop="deleteWatched(film.watchedId)"
                    size="sm" variant="outline-danger"
                    aria-label="delete" class="ml-4">
            <b-icon icon="x-circle" aria-label="delete"></b-icon>
          </b-button>
        </b-col>
      </b-row>
    </b-card>
  </transition-group>
  <div v-else-if="!$root.store.loading">
    <b-card class="hvr-shadow text-center">
      <span class="h3">No Films</span>
    </b-card>
  </div>
</template>

<script>
import { ApiService } from '../common/api.service';

export default {
  name: 'FilmListWatched',
  props: {
    films: {},
    user: {},
  },
  methods: {
    deleteWatched(watchedId) {
      const filmIndex = _.findIndex(this.films, (film) => film.watchedId === watchedId);

      ApiService.delete(`/watchedservice/watched/${watchedId}`)
        .then(() => {
          this.films.splice(filmIndex, 1);
        })
        .catch((error) => {
          console.log(error);
          // TODO: should be manage different error
          this.$bvToast.toast(`${this.films[filmIndex].title} cannot be deleted`,
            { title: 'Error', variant: 'danger', noAutoHide: true });
        });
    },
  },
};
</script>

<style scoped lang="scss">
.list-complete-item {
  display: inline-block;
  transition: all 1s;
}

.list-complete-enter, .list-complete-leave-to {
  transform: translateY(30px);
  opacity: 0;
}

.list-complete-move {
  transition: transform .6s;
}

.list-complete-leave-active {
  position: absolute;
}
</style>
