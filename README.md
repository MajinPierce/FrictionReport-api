# Friction Report API

This is the backend API for [Friction Report](https://friction.report). I was jealous of [Sendex](sendex.report), and wanted to make my own climbing weather report for areas a bit closer to home.

Keep in mind this is partially just for myself (and maybe some friends) to use, and partially to learn/practice.

This application uses OpenWeatherMap's OneCall API for the weather data.

Friction Report uses Java 17.0, Spring Boot 3.1, and Maven 3.8.

# Front End

The code for the Friction Report frontend is built using Angular and is available [here](https://github.com/MajinPierce/FrictionReport-angular).

# Install and Run

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
mvn clean install -DskipTests
```
Once all classes are generated, you can run the application with maven or via the application context.
```
mvn spring-boot:run
```

## Docker

Alternatively, you can run the application with docker. The environment variables for the docker compose file can be set in a .env file.

Once the environment variables are set, build and run the container.
```
mvn clean install -DskipTests; docker compose build
```
```
docker compose up
```

# Feedback / Contributions

I welcome all feedback or code improvements. I do lots wrong and am always looking to learn.

The [issue tracker](https://github.com/MajinPierce/FrictionReport-api/issues) is the preferred channel for bug reports, features requests, and submitting pull requests.

# Future Plans / Wishful Thinking

* Total precipitation over the last x day/hours so that you can better judge rock conditions
  * This would cost a bunch of money since it uses a different OpenWeatherMap api endpoint
  * Could also manually calculate this via a cheaper api endpoint but who knows if the computation/hosting costs would outweigh their dataset
