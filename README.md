# Friction Report API [![AWS ECS Deployment](https://github.com/MajinPierce/FrictionReport-api/actions/workflows/aws.yml/badge.svg)](https://github.com/MajinPierce/FrictionReport-api/actions/workflows/aws.yml)

This is the backend API for [Friction Report](https://friction.report). It is currently running at [https://api.friction.report](https://api.friction.report) I was jealous of [Sendex](https://sendex.report), and wanted to make my own climbing weather report for areas a bit closer to home.

Keep in mind this is partially just for myself (and maybe some friends) to use, and partially to learn/practice.

This application uses OpenWeatherMap's OneCall API for the weather data.

Friction Report uses Java 17.0, Spring Boot 3.2, and Maven 3.8.

# Front End

The code for the Friction Report frontend is built using Angular and is available [here](https://github.com/MajinPierce/FrictionReport-angular).

# Install and Run

For both the maven and docker runtimes, if you intend to use environment variables stored in AWS secrets manager, you will have to install aws cli and set up your profile locally. Then you can run the application in dev or prod modes and it will pull down the envrionment variables. The docker-compose.override.yml is configuration that allows a local container to use an aws cli profile.

## Maven

There are a few environment variable that need to be set:
  * OPEN_WEATHER_API_KEY
    * This is the API key provided by OpenWeatherMap
      * Needs an account and a payment method in case you go over your limit (which you can set a limit to)
  * H2_USERNAME
    * H2 database username
  * H2_PASSWORD
    * H2 database password
  * SPRING_PROFILES_ACTIVE
    * Which spring profile is active, prod or dev

I typically just set these via my environment variables in IntelliJ.

Once the environment variables are set, you must run mvn install to generate the mapstruct dao -> dto mapping classes.
```
mvn clean install
```
Once all classes are generated, you can run the application with maven or via the application context.
```
mvn spring-boot:run
```

## Docker

Alternatively, you can run the application with docker. The environment variables for the docker compose file can be set in a .env file.

Once the environment variables are set, build and run the container.
```
docker-compose up
```
*Note: If you are running your docker container on an m-series chipset mac, you will need to change the maven and java build profiles in the dockerfile from alpine to non alpine

# Feedback / Contributions

I welcome all feedback or code improvements. I do lots wrong and am always looking to learn.

The [issue tracker](https://github.com/MajinPierce/FrictionReport-api/issues) is the preferred channel for bug reports, features requests, and submitting pull requests.

# Future Plans / Wishful Thinking

* Total precipitation over the last x day/hours so that you can better judge rock conditions
* Come up with a better formula for the sendex that explicitly takes into account precipitation
  * Would help establish what a good score actually is other than a vague ~100 is good
* Potentially move sendex calculations to the backend 
