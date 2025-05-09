package homework.restaurant.Repositories;

import homework.restaurant.Models.MenuItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByMealPlanId(Long mealPlanId);
}
