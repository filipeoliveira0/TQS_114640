package homework.restaurant.Models;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.time.LocalDate;
import homework.restaurant.Models.MenuItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    
    @OneToMany(mappedBy = "mealPlan")
    @JsonIgnore
    private List<MenuItem> menuItems;
    
    private int availableSlots;
    
}