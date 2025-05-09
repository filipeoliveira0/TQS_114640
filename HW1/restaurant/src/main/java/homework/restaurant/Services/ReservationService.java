package homework.restaurant.Services;

import homework.restaurant.Models.*;
import homework.restaurant.Repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation createReservation(Reservation reservation) {
        // Validate required fields
        if (reservation.getUserEmail() == null || reservation.getUserEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (reservation.getRestaurant() == null || reservation.getRestaurant().getId() == null) {
            throw new IllegalArgumentException("Restaurant must be specified");
        }
        if (reservation.getMealPlan() == null || reservation.getMealPlan().getId() == null) {
            throw new IllegalArgumentException("Meal plan must be selected");
        }
        if (reservation.getSelectedMenuItem() == null || reservation.getSelectedMenuItem().getId() == null) {
            throw new IllegalArgumentException("Menu item must be selected");
        }

        // Set additional reservation details
        reservation.setReservationCode(generateReservationCode());
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setStatus(ReservationStatus.ACTIVE);

        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> getReservationByCode(String code) {
        return reservationRepository.findByReservationCode(code);
    }

    @Transactional
    public Reservation updateReservationStatus(String code, ReservationStatus status) {
        Reservation reservation = reservationRepository.findByReservationCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        
        reservation.setStatus(status);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByRestaurant(Long restaurantId) {
        return reservationRepository.findByRestaurantId(restaurantId);
    }

    private String generateReservationCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}