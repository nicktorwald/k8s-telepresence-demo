# Getting started with Docker Compose (DC)

To develop or debug locally you can run the TLOTR app using a few options.

| profile name  | frontend  | backend         | db        |
| ------------- | --------- | --------------- | --------- |
|               | local     | local           | container |
| for-front-dev | local     | container       | container |
| for-back-dev  | container | *proxied local* | conrainer |
| for-all       | container | container       | container |

## Before 

Move to the project home directory and build all images used for this hands-on demo.

```shell
$ cd $PROJECT_HOME && ./build-images.sh
```

and then

```shell
$ cd deploy/docker-compose
```

## Scenario #1

If you are a frontend developer run DC with the `for-front-dev` profile:

```shell
$ docker-compose -p tlotr --profile for-front-dev up`
```

It does not run a frontend part which is up to you. Now you are able to
start a local frontend service using preferable tools. In this demo, we are
going to use [webpack dev server](https://angular.io/guide/build).

```shell
$ cd $PROJECT_HOME/tlotr-front
$ ng install -g @angular/cli
$ ng serve --open
```

**NOTE**: it's already configured to use 'http://localhost:8080' as a backend.

Using your favorite dev tools (like IDE), debug and edit the Angular project
and see the results via http://localhost:4200.

Clean up the environment after work completion.

```shell
$ docker-compose -p tlotr --profile for-front-dev down -v
```

## Scenario #2

If you are a backend developer run DC with the `for-back-dev` profile:

```shell
docker-compose -p tlotr --profile for-back-dev`
```

It does not run a backend part which is up to you. Now you are able to
start a local backend service using preferable tools. In this demo, we are
going to use [spring-boot plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/).

```shell
$ cd $PROJECT_HOME/tlotr-back
$ ./mvnw spring-boot:run -Dspring-boot.run.profiles=demo
```

**NOTE**: This profile runs a pretender backend service as a container that proxies
container traffic from internal 8080 port on host 8080 one.

Using your favorite dev tools (like IDE), debug and edit the Spring Boot project.

Clean up the environment after work completion.

```shell
$ docker-compose -p tlotr --profile for-back-dev down -v
```

## Scenario #3

If you are someone else (i.e. a QA engineer or a PO), who wants to start all components,
run DC with the `for-all` profile:

```shell
$ docker-compose -p tlotr --profile for-all
```

It runs all app components. You are able now to serve a local app via http://localhost:4080

Clean up the environment after work completion.

```shell
$ docker-compose -p tlotr --profile for-all down -v
```

## Scenario #4

If you are a full-fledged developer run a profile-less setup as follows:

```shell
$ docker-compose -p tlotr 
```

It runs only core components (the database, in this case). Now you are able to
start local backend and frontend services.

Clean up the environment after work completion.

```shell
$ docker-compose -p tlotr down -v
```
