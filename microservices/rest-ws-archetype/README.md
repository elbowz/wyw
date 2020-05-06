## Maven archetype for REST-based web service.

### Archetype generation.

```bash 
cd microservices/users/
mvn archetype:create-from-project
```

The resulting archetype will be stored under `/target/generated-sources/archetype`.

Starting from this archetype some changes has been made in order to generalize better the resulting web service.
After this we run from archetype directory:
```bash
mvn install
mvn archetype:update-local-catalog
```

Now the archetype is ready to be used.

### How to generate a new REST web service?

```bash
cd microservices/
mvn archetype:generate -DarchetypeCatalog=local -DarchetypeGroupId=it.univaq.sose -DarchetypeArtifactId=rest-ws-archetype -DarchetypeVersion=1.0-SNAPSHOT
```

After the execution of the last command, you'll be asked for some parameters, an example input configuration is the following:

```
Define value for property 'groupId' it.univaq.sose: : 
Define value for property 'artifactId' soap-ws-archetype: : category
Define value for property 'version' 1.0-SNAPSHOT: : 
Define value for property 'package' it.univaq.sose: : 
Define value for property 'camelCaseArtifactId' Category: : 
```

The resulting microservice will be placed under `microservices\` folder (in this case `microservices\category`).

Now we have to add it in the `docker-compose.yml` and it should be up and running.