package homework.restaurant.Repositories;



import homework.restaurant.Models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Basic CRUD operations are inherited from JpaRepository
}
