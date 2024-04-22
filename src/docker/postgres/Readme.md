## Create instance of postgres

### background
docker run -d --name  Postgres_16\
-e POSTGRES_PASSWORD=mysecretpassword\
-e PGDATA=/var/lib/postgresql/data/pgdata\
-v ./src/docker/postgres/data:/var/lib/postgresql/data\
-p 5433:5432 postgres

### mode interactive
docker run -it --name postgres_16 \
-e POSTGRES_PASSWORD=mysecretpassword \
-e PGDATA=/var/lib/postgresql/data/pgdata \
-v ./src/docker/postgres/data:/var/lib/postgresql/data \
-p 5433:5432 postgres:16
## view logs
docker logs Postgres_16

## Stop Container
docker stop Postgres_16

## start Container
docker start Postgres_16

## Delete container
docker rm Postgres_16

Note:Pour supprimer un container il faut l'arreter sinon on peut utiliser la commande 
docker rm -f Postgres_16 pour forcer la suppression d'un container demarer

## start container using docker compose
docker compose -f src/docker/postgres/docker-compose.yml up
