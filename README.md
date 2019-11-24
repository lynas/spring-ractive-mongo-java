# Getting Started

### DB Setup
```
sudo docker run -d -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=password -e MONGODB_USERNAME=root 
    \ -e MONGODB_PASSWORD=password -p 27017:27017 mongo
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