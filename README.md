# SOSE 2019/20

Temporary skeleton for a microservice architecture, composed by:
* **Tomcat:** http://localhost:4000/sose/
* **MariaDB:** accessible only between container (no external port)  
    user: sose  
    pwd: sose  
    db: sosedb
* **Adminer:** http://localhost:5000/

> **note:** see `docker-compose.yml` for more information

## Typically development flow

* Start containers: `docker-compose up (-d)`
* Build and deploy: `mvn package`

> **note:** add needed dependencies to `pom.xml` or other change

## Development Guidelines

* [Commit Message Guidelines](https://gist.github.com/robertpainsi/b632364184e70900af4ab688decf6f53)