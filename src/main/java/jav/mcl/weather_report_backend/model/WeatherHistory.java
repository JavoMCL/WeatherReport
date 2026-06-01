package jav.mcl.weather_report_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "weather_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherHistory {

    @Id
    private String id;

    private String city;

    private Double temperature;

    private Double humidity;

    private String condition;

    private LocalDateTime searchedAt;
}
