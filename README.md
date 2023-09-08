# mstemplate

This application was generated using JHipster 7.8.1, you can find documentation and help at [https://www.jhipster.tech](https://www.jhipster.tech).

This is a "microservice" application intended to be part of a microservice architecture, please refer to the [Doing microservices with JHipster][] page of the documentation for more information.

## How to use this template
1. Clone xm-ms-template to separate folder.
2. Start project in you local XM environment and check if it is working, run tests.
3. Remove `.git/` folder before project modification.
4. Add project to new git repository where it will evolve and live.
5. Find and replace `mstemplate` term everywhere in the projects.
6. Find end remove everything related to `ExampleEntityFirst` and `ExampleEntitySecond` (after checking how it is proposed to use)
7. Do you need to use database:
   - if YES - correct liquibase scripts in `resources/config/liquibase`
   - if NO - remove liquibase scripts, config `XmDatabaseConfiguration` and commons `xm-commons-migration-db`
8. Check all `com.icthh.xm.commons` libraries in `build.gradle` and remove redundant. try to keep minimum set of commons.

## Project Structure

`/src/*/java` structure follows default Java structure.
`/src/*/lep` structure follows LEP folder structure containing groovy files. Source folder is generated as symlink by XM development plugin.
`/src/main/docker` - Docker configurations for the application and services that the application depends on.

## XME features and use cases

Most of the XME features can be turned on using xm-commons dependencies.
**NOTE** In this template project all commons are included. You need to select only needed.

### Logger config

Logger is configured by adding `xm-commons-logging-web` dependency transitively from `xm-commons-ms-web`.

### Spring profiles supported by default:

- dev
- prod
- no-liquibase
- api-docs

### Multi tenant Database configuration

Database support multi tenancy by adding commons `xm-commons-migration-db`
Configuration files for Liquibase are in `resources/config/liquibase`.

**NOTE:** on the micoservice level you still need custom configuration for DB:
`com.icthh.xm.ms.mstemplate.config.XmDatabaseConfiguration`

Here you can decide if you need or do not need H2 and also provide base package for scan.

### Service discovery modes

1. Consul (default) - uses Consul as a service discovery mechanism.
2. External - allows to configure service IPs from external source. Can be configured using next section in `application.yml`:
```yaml
spring:
  cloud:
    discovery:
      client:
        simple:
          instances:
            config:
              - instanceId: config
                serviceId: config
                host: localhost
                port: 8084
    consul:
        enabled: false
```


### Docker build

[//]: # (# TODO it is planned to use Jib. Need to migrate our Dockerfile to gradle/docker.gradle)

### MS config

Microservice config connected using `xm-commons-config` usually transitively by some other commons.

### Security config

Security by default autoconfigured from `xm-commons-security` module.
You can extend `com.icthh.xm.commons.security.spring.config.SecurityConfiguration` and override behavior if need.

### Privileges

There are conventions for privilege keys:
- `<ENTITY_NAME>.GET_LIST` - get list of entities
- `<ENTITY_NAME>.GET_LIST.ITEM` - get single entity by ID
- `<ENTITY_NAME>.CREATE` - create entity
- `<ENTITY_NAME>.UPDATE` - update entity
- `<ENTITY_NAME>.PARTIAL_UPDATE` - partial update entity
- `<ENTITY_NAME>.DELETE` - delete entity

NOTE: for filtering lists you need to setup `com.icthh.xm.commons.permission.annotation.FindWithPermission` annotation on Service layer.
It supports criteria based filters and SpEL defined in permission.

### Client binding
HTTP client binding connected using `xm-commons-client-feign` which contains autoconfiguration for 
feign clients

To configure HTTP client automated authorization use next application configuration:
```yaml
spring:
    security:
        oauth2:
            client:
                provider:
                    uaa:
                        token-uri: http://localhost:9999/oauth/token
                registration:
                    uaa:
                        authorization-grant-type: client_credentials
                        client-id: internal
                        client-secret: internal
```
Where `token-uri` is a URI to UAA service, `client-id` and `client-secret` are credentials to receive
client token.

After configuration properties are set, use `@AuthorizedFeignClient` annotation on your client feign
interfaces to provide automatic authorization and simple declarative request configuration

### Service & DTO conventions

By design Services should never expose DB Entities outside. All communication with controllers should be using DTOs.
[Mapstruct](https://mapstruct.org/) is advised to use for mapping Entities to DTOs and vice versa.

Typical Service & DTO pattern:
```java
    @Override
    public ExampleEntityFirstDto save(ExampleEntityFirstDto exampleEntityFirstDto) {
        ExampleEntityFirst exampleEntityFirst = exampleEntityFirstMapper.toEntity(exampleEntityFirstDto);
        exampleEntityFirst = exampleEntityFirstRepository.save(exampleEntityFirst);
        return exampleEntityFirstMapper.toDto(exampleEntityFirst);
    }
```

### CI/CD

[//]: # (TODO: define travice/gitlab file)

### Actuator

#### Healthcheck

[//]: # (TODO: need to find helathckeck for Kafka)

#### Metrics
Metrics should be collected in Prometheus format using Micrometer.
You can access metric endpoint by `http://localhost:8081/management/prometheus`

#### Log management
Endpoint for logs management `http://localhost:8081/management/logs`

#### Introspections

Endpoint for thread dump: `http://localhost:8081/management/threaddump`

### Error handling

Commons `xm-commons-i18n` contains `com.icthh.xm.commons.i18n.error.web.ExceptionTranslator` class which is responsible for exception translation.

## Development

To start your application in the dev profile, run:

```
./gradlew
```

### Start microservice without ms-config

Some microservices can work without ms-config instance.
The only reason they may need config is for getting public key for token verification and tenant list json to resolve tenant.

To start your microservice without config you can use `configMode=FILE` which still points to real config repository
but does not require running ms-config instance.

There is example configuration in application.yaml:
```yaml
xm-config:
  enabled: true
  configMode: FILE
  configDirPath: /path/to/xm-config-repo
```
These settings activate:
 - com.icthh.xm.commons.config.client.repository.FileCommonConfigRepository - implementation of file based repo
 - com.icthh.xm.commons.security.oauth2.FileVerificationKeyClient - reads certificate from `${configDirPath}/config/public.cer`

**NOTE:**
1. Config files will be updated as you change content in the local repo thanks to `com.icthh.xm.commons.config.client.repository.file.FileUpdateWatcher`
2. WARNING: you need to be aware that file will have **raw unprocessed** content (so environment variables will not be rosolved)

### Doing API-First development using openapi-generator

[OpenAPI-Generator]() is configured for this application. You can generate API code from the `src/main/resources/swagger/api.yml` definition file by running:

```bash
./gradlew openApiGenerate
```

Then implements the generated delegate classes with `@Service` classes.

To edit the `api.yml` definition file, you can use a tool such as [Swagger-Editor](). Start a local instance of the swagger-editor using docker by running: `docker-compose -f src/main/docker/swagger-editor.yml up -d`. The editor will then be reachable at [http://localhost:7742](http://localhost:7742).

Refer to [Doing API-First development][] for more details.

## Building for production

### Manage application timezone

Timezone can be defined as environment variable `TZ` of docker container.
Usually it is configured in compose file as following:
```yaml
version: '3.8'
services:
  mstemplate-app:
    image: mstemplate
    environment:
      - XMX=512M
      - TZ=UTC
```

### Packaging as jar

To build the final jar and optimize the mstemplate application for production, run:

```
./gradlew -Pprod clean bootJar
```

To ensure everything worked, run:

```
java -jar build/libs/*.jar
```

Refer to [Using JHipster in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

```
./gradlew -Pprod -Pwar clean bootWar
```

## Testing

To launch your application's tests, run:

```
./gradlew test integrationTest jacocoTestReport
```

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

Note: we have turned off authentication in [src/main/docker/sonar.yml](src/main/docker/sonar.yml) for out of the box experience while trying out SonarQube, for real use cases turn it back on.

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the gradle plugin.

Then, run a Sonar analysis:

```
./gradlew -Pprod clean check jacocoTestReport sonarqube
```

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a postgresql database in a docker container, run:

```
docker-compose -f src/main/docker/postgresql.yml up -d
```

To stop it and remove the container, run:

```
docker-compose -f src/main/docker/postgresql.yml down
```

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

```
./gradlew bootJar -Pprod jibDockerBuild
```

Then run:

```
docker-compose -f src/main/docker/app.yml up -d
```

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[jhipster homepage and latest documentation]: https://www.jhipster.tech
[jhipster 7.8.1 archive]: https://www.jhipster.tech
[doing microservices with jhipster]: https://www.jhipster.tech/microservices-architecture/
[using jhipster in development]: https://www.jhipster.tech/development/
[service discovery and configuration with consul]: https://www.jhipster.tech/microservices-architecture/#consul
[using docker and docker-compose]: https://www.jhipster.tech/docker-compose
[using jhipster in production]: https://www.jhipster.tech/production/
[running tests page]: https://www.jhipster.tech/running-tests/
[code quality page]: https://www.jhipster.tech/code-quality/
[setting up continuous integration]: https://www.jhipster.tech/setting-up-ci/
[node.js]: https://nodejs.org/
[npm]: https://www.npmjs.com/
[openapi-generator]: https://openapi-generator.tech
[swagger-editor]: https://editor.swagger.io
[doing api-first development]: https://www.jhipster.tech/doing-api-first-development/
