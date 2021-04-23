# Getting started with Kubernetes

To develop or debug locally with Kubernetes you can use [Telepresence](https://www.telepresence.io/) or
[Telepresence 2](https://www.getambassador.io/docs/telepresence/).

## Before 

Move to the project home directory and build all images used for this hands-on demo.

```shell
$ cd $PROJECT_HOME && ./build-images.sh
```

and then deploy the demo app as

```shell
$ cd deploy/k8s && kubectl apply -f ./
```

**NOTE**: It's assumed you have alredy use the right k8s context and can deploy workload
to the `tlotr` namespace.

**NOTE**: It's required an Ingress Controller installed in your cluster to publish frontend
service.

## Scenario #1

Let's explore the cluster environment using telepresence.

```shell
$ telepresence --namespace tlotr --run-shell
```

```shell
bash-3.2$ nslookup tlotr-back
bash-3.2$ nslookup tlotr-front
bash-3.2$ nslookup store-0.tlotr-db.tlotr-app.svc.cluster.local
bash-3.2$ curl -v http://tlotr-back:8080/characters
```

Send `^D` to finish the current session.

## Scenario #2

Let's swap out the frontend application in the cluster.

```shell
$ TELEPRESENCE_USE_DEPLOYMENT=1 telepresence --namespace tlotr-app --expose 4200:80 --swap-deployment tlotr-front
```

Now you are able to start a local frontend service using preferable tools. In this
demo, we are going to use [webpack dev server](https://angular.io/guide/build).

```shell
$ cd $PROJECT_HOME/tlotr-front
$ ng install -g @angular/cli
$ ng serve --open --configuration=tele
```

**NOTE**: Using `tele` configuration overrides local proxy settings as to use 'http://tlotr:8080' as a backend.

Using your favorite dev tools (like IDE), debug and edit the Angular project
and see the results via public ingress for your cluster.

Send `^D` to finish the current session.

## Scenario #3

Let's swap out the backend application in the cluster now.

```shell
$ TELEPRESENCE_USE_DEPLOYMENT=1 telepresence --namespace tlotr-app --swap-deployment tlotr-back
```

Now you are able to start a local backend service using preferable tools. In this demo, we are
going to use [spring-boot plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/).

```shell
$ cd $PROJECT_HOME/tlotr-back
$ ./mvnw spring-boot:run -Dspring-boot.run.profiles=demo,tele
```

**NOTE**: Using `tele` spring boot profile overrides DB connect as to use `store-0.tlotr-db.tlotr-app.svc.cluster.local`.

Using your favorite dev tools (like IDE), debug and edit the Spring Boot project.

Send `^D` to finish the current session.

## After

Clean up the environment after work completion.

```shell
$ kubectl delete -f ./
```