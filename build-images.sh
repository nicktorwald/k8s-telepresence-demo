#!/usr/bin/env bash

dir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
docker image build \
    -t nicktorwald/tlotr-back:0.0.1 \
    -f $dir/deploy/images/tlotr-back/Dockerfile \
    --build-arg BACK_ROOT=tlotr-back $dir
docker image build \
    -t nicktorwald/tlotr-front:0.0.1 \
    -f $dir/deploy/images/tlotr-front/Dockerfile \
    --build-arg FRONT_ROOT=tlotr-front \
    --build-arg BUILD_ROOT=deploy/images/tlotr-front $dir
