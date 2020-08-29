#!/usr/bin/env bash

ver=$( echo '${project.version}' | mvn help:evaluate | grep -v '^[[]' )
echo "the docker image's tag will be the same as the pom's:  $ver"

docker build --no-cache -t wsdl2html-web:$ver .
docker image tag wsdl2html-web:$ver chenjianjx/wsdl2html-web:$ver