package homework.restaurant.Controllers;

import homework.restaurant.Models.*;
import homework.restaurant.Services.ReservationService;
import homework.restaurant.Services.RestaurantService;
import homework.restaurant.Services.MealPlanService;
import homework.restaurant.Services.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final RestaurantService restaurantService;
    private final MealPlanService mealPlanService;
    private final MenuItemService menuItemService;

    @GetMapping("/new/{restaurantId}")
    public String showReservationForm(@PathVariable Long restaurantId, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("mealPlans", mealPlanService.getUpcomingMealPlans(restaurant));
        model.addAttribute("reservation", new Reservation());
        
        return "new-reservation"; // This matches your HTML template name
    }

    @PostMapping
    public String createReservation(
            @ModelAttribute Reservation reservation,
            @RequestParam Long restaurantId,
            @RequestParam Long mealPlanId,
            @RequestParam Long menuItemId,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Set the selected entities
            reservation.setRestaurant(restaurantService.getRestaurantById(restaurantId)
                    .orElseThrow(() -> new IllegalArgumentException("Restaurant not found")));
            reservation.setMealPlan(mealPlanService.findById(mealPlanId));
                    
            reservation.setSelectedMenuItem(menuItemService.findById(menuItemId));
                   
            
            Reservation savedReservation = reservationService.createReservation(reservation);
            return "redirect:/reservations/confirmation/" + savedReservation.getReservationCode();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
            return "redirect:/reservations/new/" + restaurantId;
        }
    }

    @GetMapping("/confirmation/{code}")
    public String showConfirmation(@PathVariable String code, Model model) {
        Reservation reservation = reservationService.getReservationByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        model.addAttribute("reservation", reservation);
        return "reservation-confirmation";
    }
}