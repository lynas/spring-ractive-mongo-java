# Getting Started

### How to create mongodb with docker

```
sudo docker run -d -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=password -p 27017:27017 mongo
```

### Create db inside docker mongo container
```
sudo docker exec -i -t containerId /bin/bash
mongo -u root -p
use tempdb
```

### application.yml

```
spring:
  data:
    mongodb:
      host: 192.168.226.128
      port: 27017
      database: admin
      username: root
      password: password

```