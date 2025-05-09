package homework.restaurant;
import homework.restaurant.Models.*;
import homework.restaurant.Repositories.*;
import homework.restaurant.Services.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;




@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepo;
    
    @Mock
    private MealPlanRepository mealPlanRepo;
    
    @InjectMocks
    private ReservationService reservationService;

    @Test
    void createReservation_ShouldSuccess() {
        Reservation reservation = new Reservation();
        reservation.setUserEmail("test@example.com");
        reservation.setRestaurant(new Restaurant(1L, "Test", "Loc", 100, null));
        reservation.setMealPlan(new MealPlan(1L, LocalDate.now(), null, null, 50));
        reservation.setSelectedMenuItem(new MenuItem(1L, "Item", "Desc", 
            MenuType.MAIN_COURSE, BigDecimal.TEN, null));
        
        when(reservationRepo.save(any())).thenReturn(reservation);

        Reservation result = reservationService.createReservation(reservation);

        assertNotNull(result.getReservationCode());
        assertEquals(ReservationStatus.ACTIVE, result.getStatus());
        verify(reservationRepo).save(reservation);
    }

    @Test
    void createReservation_ShouldThrowWhenNoEmail() {
        Reservation reservation = new Reservation(); 
        assertThrows(IllegalArgumentException.class, 
            () -> reservationService.createReservation(reservation));
    }
}
