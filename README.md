# be-chestday

Backend of a project focused in gym stuff

## Before running

- Clone the project

```bash
  git clone https://github.com/arthur-cabral/be-chestday
```

- Update dependencies in pom.xml, if needed

- Create a docker container with the command below

```bash
  docker run --name my-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql:latest
```

- Enter in container 

```bash
  docker exec -it my-mysql mysql -uroot -pmy-secret-pw
```

- Create a database in mysql

```bash
  CREATE DATABASE db-be-chestday;
```
## Setting environment variables

In your application.properties, use this model

```bash
spring.application.name=bechestday

spring.datasource.url=jdbc:mysql://localhost:3306/db-be-chestday
spring.datasource.username=root
spring.datasource.password=my-secret-pw
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
    
