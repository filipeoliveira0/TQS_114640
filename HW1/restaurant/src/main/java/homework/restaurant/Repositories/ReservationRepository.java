package homework.restaurant.Repositories;

import homework.restaurant.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByReservationCode(String reservationCode);
    List<Reservation> findByRestaurantId(Long restaurantId);
    List<Reservation> findBySelectedMenuItemId(Long selectedMenuItemId);
    boolean existsBySelectedMenuItemId(Long menuItemId);
}