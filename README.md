# SOSE 2019/20

Temporary skeleton for a microservice architecture, composed by:
* **Tomcat:** http://localhost:port/sose/
* **MariaDB:** accessible only between container (no external port)  
    user: sose  
    pwd: sose  
    db: sosedb
* **Adminer:** http://localhost:5000/

> **note:** see `docker-compose.yml` for more information

## Typically development flow

### Start containers
 
* Normal:  
    `docker-compose up (-d)`
* Development (eg. JPDA):  
    `docker-compose -f docker-compose.yml -f docker-compose.dev.yml up`

### Build and Deploy:

* Whole project:   
    `mvn package` from the project root
* Single microservices:  
    * `mvn -pl microservices/person package` or 
    * `cd microservices/<name> && mvn package`
    
### Container scaling
 * Scale on start:  
    `docker-compose up --scale person-ws=3`
 * Scale after start:  
    `docker-compose up`   
    `docker-compose scale person-ws=3`

> **note**: tomacat auto-reload the new war...if not restart it  
> **note:** add needed dependencies to the root `pom.xml` or the children ones

## Development Guidelines

* [Commit Message Guidelines](https://gist.github.com/robertpainsi/b632364184e70900af4ab688decf6f53)
