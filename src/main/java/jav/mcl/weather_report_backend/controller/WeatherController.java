package jav.mcl.weather_report_backend.controller;

import jav.mcl.weather_report_backend.dto.WeatherResponseDTO;
import jav.mcl.weather_report_backend.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/current/{city}")
    public WeatherResponseDTO getCurrentWeather(
            @PathVariable String city) {

        return weatherService.getCurrentWeather(city);
    }
}
