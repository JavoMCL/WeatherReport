package jav.mcl.weather_report_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherResponseDTO {

    private String city;
    private Double temperature;
    private Double humidity;
    private String condition;
}