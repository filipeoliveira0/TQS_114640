package homework.restaurant.Services;

import homework.restaurant.Models.MenuItem;
import homework.restaurant.Models.Reservation;
import homework.restaurant.Repositories.MenuItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import homework.restaurant.Repositories.ReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final ReservationRepository reservationRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
    }

    public List<MenuItem> getMenuItemsByMealPlan(Long mealPlanId) {
        return menuItemRepository.findByMealPlanId(mealPlanId);
    }


    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(Long id, MenuItem menuItemDetails) {
        MenuItem menuItem = getMenuItemById(id);
        menuItem.setName(menuItemDetails.getName());
        menuItem.setDescription(menuItemDetails.getDescription());
        menuItem.setType(menuItemDetails.getType());
        menuItem.setPrice(menuItemDetails.getPrice());
        return menuItemRepository.save(menuItem);
    }

    public MenuItem findById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
    }
    @Transactional
    public void deleteMenuItem(Long id) {
        // Find all reservations using this menu item
        List<Reservation> reservations = reservationRepository.findBySelectedMenuItemId(id);
        
        // Set the menu item reference to null
        reservations.forEach(reservation -> reservation.setSelectedMenuItem(null));
        reservationRepository.saveAll(reservations);
        
        // Now safe to delete the menu item
        menuItemRepository.deleteById(id);
    }
}