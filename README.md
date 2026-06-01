# WeatherReport - Backend

A small Spring Boot backend service to fetch current weather data, save search history to MongoDB, and expose a REST endpoint. This README is intentionally simple — perfect if you're just getting started.

## Tech stack
- Java 21
- Spring Boot (webmvc, data-mongodb)
- Maven (wrapper included: `mvnw`, `mvnw.cmd`)
- MongoDB
- Lombok (for boilerplate reduction)

Key files:
- `pom.xml` - Maven configuration and dependencies.
- `src/main/resources/application.properties` - application configuration (API key, MongoDB URI, server port).
- `src/main/java/.../controller/WeatherController.java` - REST controller exposing the endpoint.
- `src/main/java/.../service/WeatherService.java` - Service that calls the client and saves history.
- `src/main/java/.../client/WeatherClient.java` - HTTP client that calls the external weather API.

## Requirements
- Java 21 JDK installed
- Maven (optional if you use the included wrapper)
- MongoDB running (default URI used: `mongodb://localhost:27017/weatherdb`)
- An API key for OpenWeatherMap (or compatible API)

## Configuration
The project reads the following properties from `src/main/resources/application.properties`:
- `weather.api.key` — the API key used by `WeatherClient`.
- `weather.api.base-url` — base URL for the weather API (defaults to OpenWeatherMap).
- `spring.mongodb.uri` — MongoDB connection URI.

You can provide the API key using an environment variable named `API_KEY` (the `application.properties` uses `${API_KEY}` by default), or you can edit `application.properties` directly.

To set the environment variable in Windows PowerShell (current session):
```powershell
$env:API_KEY = "your_openweather_api_key_here"


