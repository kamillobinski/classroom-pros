# Classroom pros

Main purpose of this project is to let you create, edit and manage lesson plans that are easily accessible by others.

![cp_preview](https://github.com/kamillobinski/classroom-pros/blob/master/cp_preview.png?raw=true)

## Installation

It is possible to import project as a maven application to your favorite IDE.

```bash
# Clone this repository
$ git clone https://github.com/kamillobinski/classroom-pros
```
## Usage

Quick info on how to run a Spring Boot application.

1. Build using maven goal (or by using maven wrapper): ```mvn clean package``` and execute the resulting artifact ```java - jar demo-0.0.1-SNAPSHOT.jar```
2. On Unix/Linux based systems: ```mvn clean package``` then run the resulting jar as any other executable ```./demo-0.0.1-SNAPSHOT.jar```

Access the deployed web application at: http://localhost:8081/

**Note**: this projects by default uses ClearDB MySQL on [Heroku](https://heroku.com) platform. You can change it in the ```application.yml``` file.

```yaml
spring:
  datasource:
    url: jdbc:mysql://{hoster_domain}:{port}/{database}
    username: {database username}
    password: {database password}
    hikari:
      minimumIdle: 1
      maximumPoolSize: 8
  jpa:
    hibernate:
      ddl-auto: update

server:
  port: 8081
```

## Key Features

Will be described soon.

## Contributors


<div>
    <a href="https://github.com/kamillobinski/classroom-pros/graphs/contributors">
      <img src="https://contributors-img.web.app/image?repo=kamillobinski/classroom-pros" />
    </a>
</div>

## License

Distributed under the MIT License. See ```LICENSE``` for more information.

