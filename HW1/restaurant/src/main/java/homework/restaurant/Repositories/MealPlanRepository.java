package homework.restaurant.Repositories;

import homework.restaurant.Models.MealPlan;
import homework.restaurant.Models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    List<MealPlan> findByRestaurantAndDateGreaterThanEqual(Restaurant restaurant, LocalDate date);
    List<MealPlan> findByDateGreaterThanEqual(LocalDate date);
}
    