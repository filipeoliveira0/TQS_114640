package homework.restaurant.Models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    

    private MenuType type;
    
    private BigDecimal price;
    
    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;





}