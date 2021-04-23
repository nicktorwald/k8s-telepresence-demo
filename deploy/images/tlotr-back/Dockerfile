### Stage 1. Build
FROM maven:3.6.3-openjdk-11 AS java-builder

ENV MAVEN_OPTS -XX:+TieredCompilation -XX:TieredStopAtLevel=1
ARG BACK_ROOT

WORKDIR /source
COPY ${BACK_ROOT}/pom.xml .
RUN mvn --threads 1C --errors --batch-mode dependency:resolve-plugins dependency:go-offline
COPY ${BACK_ROOT}/src ./src
RUN mvn --threads 1C --errors --batch-mode package
RUN mkdir target/_output \
    && cd target/_output \
    && mv ../*.jar ./app.jar \
    && java -Djarmode=layertools -jar app.jar extract


### Stage 1. Run
FROM openjdk:11-jre

LABEL maintainer="nicktorwald"

ENV APP_ROOT /opt/tlotr-back

RUN groupadd --gid 999 --system bilbo \
    && useradd --uid 999 --system --gid bilbo bilbo \
    && mkdir --parents ${APP_ROOT} \
    && chown --recursive bilbo:bilbo ${APP_ROOT}

WORKDIR ${APP_ROOT}
COPY --from=java-builder /source/target/_output/dependencies/ ./
COPY --from=java-builder /source/target/_output/spring-boot-loader/ ./
COPY --from=java-builder /source/target/_output/snapshot-dependencies/ ./
COPY --from=java-builder /source/target/_output/application/ ./

EXPOSE 8080/tcp

USER bilbo
ENTRYPOINT ["java", "-Xms512m", "-Xmx512m", "org.springframework.boot.loader.JarLauncher"]
CMD ["--spring.profiles.active=demo"]

