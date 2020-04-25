This webservice, by default, uses film-ws to obtain film information. When film-ws is unreachable it tries to use OMDB api.
If network is down then an empty result is returned.

### Check if fallback for film-ws is working.

Kill the film-ws service with: `docker-compose kill film-ws`. Now a call to `watched/:id` will use OMDB for retrieving the film information.
