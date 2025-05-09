package homework.restaurant.Services;

import homework.restaurant.Models.MealPlan;
import homework.restaurant.Models.Restaurant;
import homework.restaurant.Repositories.MealPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealPlanService {
    private final MealPlanRepository mealPlanRepository;
    
    public List<MealPlan> getUpcomingMealPlans(Restaurant restaurant) {
        return mealPlanRepository.findByRestaurantAndDateGreaterThanEqual(restaurant, LocalDate.now());
    }
    
    public List<MealPlan> getAllUpcomingMealPlans() {
        return mealPlanRepository.findByDateGreaterThanEqual(LocalDate.now());
    }
    
    public Optional<MealPlan> getMealPlanById(Long id) {
        return mealPlanRepository.findById(id);
    }
    
    public MealPlan saveMealPlan(MealPlan mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }
    public MealPlan findById(Long id) {
        return mealPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meal plan not found"));
    }
}
