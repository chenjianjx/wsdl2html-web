# wsdl2html-web
The web frontend to run wsdl2html

## How to run

### Run as a plain webapp
It's a standard servlet application.  Just run it with any servlet container tomcat (>= 8) etc. 

### Run using docker-compose
Use this file:  [The offical docker-compose.yml in the repo wsdl2html](https://github.com/chenjianjx/wsdl2html/blob/master/docker-compose.yml) 

* Note: dockerhost is optional, but without it wsdl2html-web inside the container won't be able to reach wsdl on the host, i.e. http://localhost...?wsdl
* Note2:  [docker-compose.yml](docker-compose.yml) in this repo is for the developer of this repo. You don't need to touch it unless you want to contribute. 

### Run as docker container
Pull the image https://hub.docker.com/repository/docker/chenjianjx/wsdl2html-web and run it
