package homework.restaurant.Models;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String location;
    private int capacity;
    
    @OneToMany(mappedBy = "restaurant")
    @JsonIgnore
    private List<MealPlan> mealPlans;
   

}
    

