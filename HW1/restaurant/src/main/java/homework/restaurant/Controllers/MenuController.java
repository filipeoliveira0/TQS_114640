package homework.restaurant.Controllers;

import homework.restaurant.Models.*;
import homework.restaurant.Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menu")
public class MenuController {
    private final MenuItemService menuItemService;
    private final MealPlanService mealPlanService;
    private final RestaurantService restaurantService;

    public MenuController(MenuItemService menuItemService, 
                         MealPlanService mealPlanService,
                         RestaurantService restaurantService) {
        this.menuItemService = menuItemService;
        this.mealPlanService = mealPlanService;
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public String showAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        return "restaurants";
    }

    @GetMapping("/{restaurantId}")
    public String showMealPlans(@PathVariable Long restaurantId, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("mealPlans", mealPlanService.getUpcomingMealPlans(restaurant));
        return "mealplans";
    }

    @GetMapping("/mealplan/{mealPlanId}")
    public String showMenuItems(@PathVariable Long mealPlanId, Model model) {
        MealPlan mealPlan = mealPlanService.getMealPlanById(mealPlanId)
                .orElseThrow(() -> new RuntimeException("Meal plan not found"));
        model.addAttribute("mealPlan", mealPlan);
        model.addAttribute("menuItems", menuItemService.getMenuItemsByMealPlan(mealPlanId));
        return "menu-items";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        model.addAttribute("mealPlans", mealPlanService.getAllUpcomingMealPlans());
        model.addAttribute("menuTypes", MenuType.values());
        return "add-menu-item";
    }

    @PostMapping("/add")
    public String addMenuItem(@ModelAttribute MenuItem menuItem) {
        menuItemService.createMenuItem(menuItem);
        return "redirect:/menu/mealplan/" + menuItem.getMealPlan().getId();
    }

    @GetMapping("/{restaurantId}/home")
    public String restaurantHome(@PathVariable Long restaurantId, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        model.addAttribute("restaurant", restaurant);
        return "restaurant-home";
    }
}