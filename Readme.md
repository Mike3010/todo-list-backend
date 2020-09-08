# Readme

## Prepare docker DB

get postgres image
```docker pull postgres```

mk dir for persistent content
```mkdir docker\volumes\postgres```

## Run database

```docker run --rm --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres```

## Create app as container

```./mvwn install````

```docker build -t todo-backend .```

## Run docker container

```docker run -p 8080:8080 -d todo-backend```