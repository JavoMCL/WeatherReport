package jav.mcl.weather_report_backend.service;

import jav.mcl.weather_report_backend.client.WeatherClient;
import jav.mcl.weather_report_backend.dto.WeatherResponseDTO;
import jav.mcl.weather_report_backend.model.WeatherHistory;
import jav.mcl.weather_report_backend.repository.WeatherHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherClient client;

    @Mock
    private WeatherHistoryRepository repository;

    @InjectMocks
    private WeatherService service;

    @Test
    void getCurrentWeatherMapsApiResponseAndSavesHistory() {
        String city = "Encarnacion";

        Map<String, Object> apiResponse = Map.of(
                "name", "Encarnacion",
                "main", Map.of(
                        "temp", 24.7,
                        "humidity", 40
                ),
                "weather", List.of(
                        Map.of("description", "clear sky")
                )
        );

        when(client.getCurrentWeather(city)).thenReturn(apiResponse);

        WeatherResponseDTO result = service.getCurrentWeather(city);

        assertNotNull(result);
        assertEquals("Encarnacion", result.getCity());
        assertEquals(24.7, result.getTemperature());
        assertEquals(40.0, result.getHumidity());
        assertEquals("clear sky", result.getCondition());

        verify(client).getCurrentWeather(city);

        ArgumentCaptor<WeatherHistory> captor = ArgumentCaptor.forClass(WeatherHistory.class);
        verify(repository).save(captor.capture());

        WeatherHistory saved = captor.getValue();
        assertEquals("Encarnacion", saved.getCity());
        assertEquals(24.7, saved.getTemperature());
        assertEquals(40.0, saved.getHumidity());
        assertEquals("clear sky", saved.getCondition());
        assertNotNull(saved.getSearchedAt());
    }

    @Test
    void getCurrentWeatherSupportsDifferentNumberTypes() {
        String city = "Asuncion";

        Map<String, Object> apiResponse = Map.of(
                "name", "Asuncion",
                "main", Map.of(
                        "temp", 18,       // Integer
                        "humidity", 77.5  // Double
                ),
                "weather", List.of(
                        Map.of("description", "cloudy")
                )
        );

        when(client.getCurrentWeather(city)).thenReturn(apiResponse);

        WeatherResponseDTO result = service.getCurrentWeather(city);

        assertEquals("Asuncion", result.getCity());
        assertEquals(18.0, result.getTemperature());
        assertEquals(77.5, result.getHumidity());
        assertEquals("cloudy", result.getCondition());

        verify(client).getCurrentWeather(city);
        verify(repository).save(any(WeatherHistory.class));
    }
}