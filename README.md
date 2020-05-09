# SOSE 2019/20

![WYW](./frontend/src/assets/logo-landscape.png)

Temporary skeleton for a microservice architecture, composed by:
* **Tomcat:** http://localhost:port/sose/
* **MariaDB:** accessible only between container (no external port)  
    user: sose  
    pwd: sose  
    db: sosedb
* **Adminer:** http://localhost:5000/

> **note:** see `docker-compose.yml` for more information
>
> **note:** Tomcat port change between `4000-4100`, use `docker ps` to spot it 

## Typically development flow

### Start containers

 A key for [OMDB API](http://www.omdbapi.com/apikey.aspx) is required if you want to obtain film ratings.
 Before running, export the key with:
 
 `export OMDB_API_KEY="MY_KEY"`
 
* Normal:  
    `docker-compose up (-d)`
* Development (eg. JPDA):  
    `docker-compose -f docker-compose.yml -f docker-compose.dev.yml up`

### Build and Deploy

* Whole project:   
    `mvn (clean) package` from the project root
* Single microservices:  
    * `mvn -pl microservices/person package` or 
    * `cd microservices/<name> && mvn package`

> **note**: tomacat auto-reload the new war after a `mvn package`...if doesn't happen restart it (`docker-compose restart <service-name>`)  
    
### Container scaling

 * Scale on start:  
    `docker-compose up --scale person-ws=3`
 * Scale after start:  
    `docker-compose up`   
    `docker-compose scale person-ws=3`

### Frontend

 * Deployment   
    `cd frontend && npm run serve`
 * Deploy for distribution (*localhost:3000*)   
    `cd frontend && npm run build`
    
### Utils
 
 * Archetype (generate a new REST web service) ([readme.md](microservices/rest-ws-archetype/README.md))
 * Script for init the wyw DB scraping the IMDB website. ([readme.md](utils/imdb2sql/README.md))  
    `utils/imdb2sql.py`  

## Notes

Add needed dependencies to the root `pom.xml` or the children ones.
Create needed services/networks to the `docker-compose.yml`.

## Development Guidelines

* [Commit Message Guidelines](https://gist.github.com/robertpainsi/b632364184e70900af4ab688decf6f53)
