package jav.mcl.weather_report_backend.service;

import jav.mcl.weather_report_backend.client.WeatherClient;
import jav.mcl.weather_report_backend.dto.WeatherResponseDTO;
import jav.mcl.weather_report_backend.model.WeatherHistory;
import jav.mcl.weather_report_backend.repository.WeatherHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient client;
    private final WeatherHistoryRepository repository;

    @SuppressWarnings("unchecked")
    public WeatherResponseDTO getCurrentWeather(String city) {

        Map<String, Object> response = client.getCurrentWeather(city);

        Map<String, Object> main = (Map<String, Object>) response.get("main");
        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
        Map<String, Object> weather = weatherList.get(0);

        WeatherResponseDTO dto = WeatherResponseDTO.builder()
                .city((String) response.get("name"))
                .temperature(((Number) main.get("temp")).doubleValue())
                .humidity(((Number) main.get("humidity")).doubleValue())
                .condition((String) weather.get("description"))
                .build();

        WeatherHistory history = WeatherHistory.builder()
                .city(dto.getCity())
                .temperature(dto.getTemperature())
                .humidity(dto.getHumidity())
                .condition(dto.getCondition())
                .searchedAt(LocalDateTime.now())
                .build();

        repository.save(history);

        return dto;
    }
}
