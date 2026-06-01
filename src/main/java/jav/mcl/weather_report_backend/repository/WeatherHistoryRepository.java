package jav.mcl.weather_report_backend.repository;

import jav.mcl.weather_report_backend.model.WeatherHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WeatherHistoryRepository
        extends MongoRepository<WeatherHistory, String> {

    List<WeatherHistory> findByCityIgnoreCase(String city);
}
