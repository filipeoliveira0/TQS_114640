package homework.restaurant.Controllers;

import homework.restaurant.Models.CacheStatistics;
import homework.restaurant.Models.WeatherCache;
import homework.restaurant.Services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;
    
    @GetMapping
    public WeatherCache getWeatherForecast(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String location) {
        return weatherService.getWeatherForecast(date, location);
    }
    
    @GetMapping("/cache-stats")
    public CacheStatistics getCacheStatistics() {
        return weatherService.getCacheStatistics();
    }
}
