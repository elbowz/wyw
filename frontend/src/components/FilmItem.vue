<template>
  <div>
    <b-row>
      <b-col sm="4">
        <b-img :src="film.poster" :alt="film.title" left rounded class="mr-2" fluid-grow></b-img>
      </b-col>
      <b-col sm="8">
        <h3>{{film.title}} <span class="font-weight-light">({{film.year}})</span></h3>
        <div v-if="film.ratings" class="mb-2">
          <span v-for="rating in film.ratings" :key="rating.source" class="mr-2">
            <span class="font-weight-light">{{rating.source}}: </span>
            <span class="font-weight-bold text-info">{{rating.value}}</span>
          </span>
        </div>
        <a :href="'https://www.imdb.com/title/' + film.imdbID"><small><i class="fab fa-imdb"></i> imdb.com</small></a>
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
          <div v-for="person in film.people" :key="person.person.id">
            {{person.person.firstName}} {{person.person.lastName}} <small class="text-capitalize">({{person.role}})
          </small>
          </div>
        </div>
        <div class="text-capitalize mt-3">
          {{film.plot}}
        </div>
        <b-button @click="" variant="outline-primary" class="mt-3">
          <b-icon icon="eye-fill" aria-label="watched"></b-icon> Watched
        </b-button>
      </b-col>
    </b-row>
  </div>
  <!--
  {"imdbID":"tt3896198","title":"Guardians of the Galaxy Vol. 2","year":2017,"plot":"The Guardians struggle to keep together as a team while dealing with their personal family issues, notably Star-Lord's encounter with his father the ambitious celestial being Ego.","language":"english","production":"Walt Disney Pictures","poster":"https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0YmUtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg","people":[{"role":"Director","person":{"id":1,"firstName":"James","lastName":"Gunn"}},{"role":"Writer","person":{"id":2,"firstName":"Dan","lastName":"Abnett"}}]}
  -->
</template>

<script>
export default {
  name: 'FilmItem',
  props: {
    film: {},
  },
};
</script>
