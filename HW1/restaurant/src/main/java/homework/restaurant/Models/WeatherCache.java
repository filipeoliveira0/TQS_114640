package homework.restaurant.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherCache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate date;
    private String location;
    private String forecast;
    private String temperature;
    
    @Column(nullable = false)
    private LocalDateTime fetchedAt;
    
    // TTL could be handled in service logic
    
    // constructors, getters, setters
}
