# Taskmanager Light

## Deploy App
> mvn clean package

## Build Image
> docker build -t taskmanager-light .

## Run App
> docker run -p 8080:8080 -e DATABASE_URL='jdbc:h2:mem:taskmanagerv2' -e DATABASE_USERNAME='sa' -e DATABASE_PWD='' -e API_SECRET='dqm50vnc!' taskmanager-light