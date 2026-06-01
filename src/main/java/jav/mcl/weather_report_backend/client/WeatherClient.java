package jav.mcl.weather_report_backend.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class WeatherClient {

    private final RestClient restClient;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.base-url}")
    private String baseUrl;

    public WeatherClient() {
        this.restClient = RestClient.create();
    }

    public Map<String, Object> getCurrentWeather(String city) {

        String url = baseUrl + "/weather?q=" + city
                + "&appid=" + apiKey
                + "&units=metric";

        return restClient.get()
                .uri(url)
                .retrieve()
                .body(Map.class);
    }
}