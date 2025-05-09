package homework.restaurant.Controllers;

import homework.restaurant.Models.MealPlan;
import homework.restaurant.Models.Restaurant;
import homework.restaurant.Models.WeatherCache;
import homework.restaurant.Services.MealPlanService;
import homework.restaurant.Services.RestaurantService;
import homework.restaurant.Services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mealplans")
@RequiredArgsConstructor
public class MealPlanController {
    private final MealPlanService mealPlanService;
    private final RestaurantService restaurantService;
    private final WeatherService weatherService;
    
    @GetMapping
    public List<MealPlan> getAllUpcomingMealPlans() {
        List<MealPlan> mealPlans = mealPlanService.getAllUpcomingMealPlans();
        
        // Fetch weather for each meal plan
        mealPlans.forEach(mealPlan -> {
            String location = mealPlan.getRestaurant().getLocation();
            WeatherCache weatherCache = weatherService.getWeatherForecast(mealPlan.getDate(), location);
            // You could set weather info on the meal plan or return a combined DTO
        });
        
        return mealPlans;
    }
    
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MealPlan>> getMealPlansByRestaurant(@PathVariable Long restaurantId) {
        return restaurantService.getRestaurantById(restaurantId)
                .map(restaurant -> {
                    List<MealPlan> mealPlans = mealPlanService.getUpcomingMealPlans(restaurant);
                    
                    // Fetch weather for each meal plan
                    mealPlans.forEach(mealPlan -> {
                        String location = restaurant.getLocation();
                        WeatherCache weatherCache = weatherService.getWeatherForecast(mealPlan.getDate(), location);
                        // You could set weather info on the meal plan or return a combined DTO
                    });
                    
                    return ResponseEntity.ok(mealPlans);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MealPlan> getMealPlanById(@PathVariable Long id) {
        return mealPlanService.getMealPlanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public MealPlan createMealPlan(@RequestBody MealPlan mealPlan) {
        return mealPlanService.saveMealPlan(mealPlan);
    }
}
