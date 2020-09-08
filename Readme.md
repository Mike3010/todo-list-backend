# Readme

## Prepare docker DB

get postgres image
```docker pull postgres```

mk dir for persistent content
```mkdir docker\volumes\postgres```

## Run database

```docker run --rm --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v c:/users/mike/docker/volumes/postgres:/var/lib/postgresql/data postgres```