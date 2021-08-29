# Prerequisites
- gradle
- docker

# How to run locally
```
./gradlew build
docker build --build-arg JAR_FILE=build/libs/\*.jar -t payeye/rest-app-docker .
docker run -p 8080:8080 payeye/rest-app-docker
```