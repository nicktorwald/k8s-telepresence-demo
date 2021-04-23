### Stage 1. Build
FROM node:15.4.0-alpine AS angular-build

ARG FRONT_ROOT

WORKDIR /source
COPY ${FRONT_ROOT}/package.json ${FRONT_ROOT}/package-lock.json ./
RUN npm install
COPY ${FRONT_ROOT}/. ./
RUN npm run build -- --prod


### Stage 1. Run
FROM nginx:1.19.10

ARG BUILD_ROOT

LABEL maintainer="nicktorwald"

COPY ${BUILD_ROOT}/*.template /etc/nginx/templates/
COPY --from=angular-build /source/dist/tlotr-front /usr/share/nginx/html
