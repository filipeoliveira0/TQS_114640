package homework.restaurant.Services;

import homework.restaurant.Models.CacheStatistics;
import homework.restaurant.Models.WeatherCache;
import homework.restaurant.Repositories.CacheStatisticsRepository;
import homework.restaurant.Repositories.WeatherCacheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {
    private final WeatherCacheRepository weatherCacheRepository;
    private final CacheStatisticsRepository cacheStatisticsRepository;
    private final RestTemplate restTemplate;
    
    @Value("${weather.api.url}")
    private String weatherApiUrl;
    
    @Value("${weather.api.key}")
    private String weatherApiKey;
    
    @Value("${weather.cache.ttl.hours}")
    private int cacheTtlHours;
    
    public WeatherCache getWeatherForecast(LocalDate date, String location) {
        log.info("Getting weather forecast for {} on {}", location, date);
        
        // Get or create cache statistics
        CacheStatistics stats = cacheStatisticsRepository.findAll().stream()
                .findFirst()
                .orElse(new CacheStatistics(null, 0, 0, 0));
        
        stats.setTotalRequests(stats.getTotalRequests() + 1);
        
        // Try to get from cache
        Optional<WeatherCache> cachedWeather = weatherCacheRepository.findByDateAndLocation(date, location);
        
        if (cachedWeather.isPresent()) {
            WeatherCache weather = cachedWeather.get();
            
            // Check if the cache is still valid
            if (weather.getFetchedAt().plusHours(cacheTtlHours).isAfter(LocalDateTime.now())) {
                log.info("Cache hit for {} on {}", location, date);
                stats.setCacheHits(stats.getCacheHits() + 1);
                cacheStatisticsRepository.save(stats);
                return weather;
            }
            
            log.info("Cache expired for {} on {}", location, date);
        }
        
        // Cache miss or expired, fetch from API
        stats.setCacheMisses(stats.getCacheMisses() + 1);
        cacheStatisticsRepository.save(stats);
        
        WeatherCache weatherData = fetchWeatherFromApi(date, location);
        weatherData.setFetchedAt(LocalDateTime.now());
        
        return weatherCacheRepository.save(weatherData);
    }
    
    private WeatherCache fetchWeatherFromApi(LocalDate date, String location) {
        log.info("Fetching weather from API for {} on {}", location, date);
        
        // This is a simplified implementation. In a real application, you'd need to:
        // 1. Properly format the API request
        // 2. Handle API errors and rate limits
        // 3. Parse the API response into your model
        
        String url = weatherApiUrl + "?location=" + location + "&date=" + date + "&apiKey=" + weatherApiKey;
        
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            
            // Mock parsing response - in real app, parse JSON response
            WeatherCache weather = new WeatherCache();
            weather.setDate(date);
            weather.setLocation(location);
            weather.setForecast("Sunny"); // Parse from response
            weather.setTemperature("22°C"); // Parse from response
            
            return weather;
        } catch (Exception e) {
            log.error("Error fetching weather: {}", e.getMessage());
            
            // Fallback to default data
            WeatherCache weather = new WeatherCache();
            weather.setDate(date);
            weather.setLocation(location);
            weather.setForecast("No data available");
            weather.setTemperature("--");
            
            return weather;
        }
    }
    
    public CacheStatistics getCacheStatistics() {
        return cacheStatisticsRepository.findAll().stream()
                .findFirst()
                .orElse(new CacheStatistics(null, 0, 0, 0));
    }
}