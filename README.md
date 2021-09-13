# Repository Summary
* Docker
* Microservices-architecture (Spring Boot - Java Framework)
* Microservices Communication using Feign client as REST client
* Docker staging and pom.xml profiles (dev, test, prod environments)
* Mysql-database
* Postgresql-database
* Spring Boot (back-end)
* Platform-Provided Service Discovery (Deployment infrastructure take care of service discovery)
* Service-discovery (Docker-DNS)
* API-gateway (NGINX)
* Using NGINX in the backend as a reverse proxy
* Using NGINX in the frontend as a web server for displaying the static content of the dist folder
* Shell Scripting
* 3 types of artifacts:
    * The first type of artifacts: **mvn base image** (mvn dependencies download acceleration) published on **Docker Hub**
    * The second type of artifacts: publish the **application as a runnable JAR file** on the **GitHub Packages repository**
    * The third type of artifacts: publishing the **application’s Docker image** on **Docker Hub**


# Nistagram Auth Microservice Instructions
**Start Docker Daemon:** If you're using Docker for Windows, Then simply start the desktop app installed in:
```shell
C:\Program Files\Docker\Docker\Docker Desktop.exe
```
Position yourself using **Git Bash** in the folder where `docker-compose.yml` file is:
```
cd DOCKER-COMPOSE_FILE_PATH
```
Mapping environment variables from the environment file to the docker-compose.yml file (Check how your docker-compose.yml will finally look like, after environment variables substitution). Building container images can also be achieved using docker compose. Before running any docker compose command you should always check configuration using the following command:

Mapping environment variables from the **dev** environment file to the **docker-compose.yml** file
```shell
docker-compose --env-file config/.env.dev config
```
Mapping environment variables from the **test** environment file to the **docker-compose.test.yml** file
```shell
docker-compose --env-file config/.env.test config
```
Mapping environment variables from the **prod** environment file to the **docker-compose.prod.yml** file
```shell
docker-compose --env-file config/.env.prod config
```


# Artifacts
## The first type of artifacts: mvn base image (mvn dependencies download acceleration) published on Docker Hub
Build mvn base image
```shell
docker image build -f Dockerfile.base --build-arg STAGE=dev -t stevicdule/mvn-base-nistagram-auth:1.0.0-dev .
```
Push your mvn base image to Docker Hub
```shell
docker push stevicdule/mvn-base-nistagram-auth:1.0.0-dev
```
## The second type of artifacts: publish the application as a runnable JAR file on the GitHub Packages repository
## The third type of artifacts: publishing the application’s Docker image on Docker Hub
