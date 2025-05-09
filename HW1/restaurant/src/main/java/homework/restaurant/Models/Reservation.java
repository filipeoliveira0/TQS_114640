package homework.restaurant.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String reservationCode; 
    private LocalDate date;
    private LocalTime time;
    private ReservationStatus status; 
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "menu_item_id")
    private MenuItem selectedMenuItem; 

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;

    
    private String userEmail; 
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
   
}
