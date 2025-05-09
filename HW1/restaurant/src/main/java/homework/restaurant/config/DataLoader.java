package homework.restaurant.config;

import homework.restaurant.Models.*;
import homework.restaurant.Repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    @Profile("!test") // Don't load in tests
    CommandLineRunner initDatabase(RestaurantRepository restaurantRepo,
                                 MealPlanRepository mealPlanRepo,
                                 MenuItemRepository menuItemRepo,
                                 ReservationRepository reservationRepo) {
        return args -> {
            // Clear existing data in proper order to avoid constraint violations
            reservationRepo.deleteAll(); // Clear reservations first since they reference other tables
            menuItemRepo.deleteAll();   // Then menu items (they reference meal plans)
            mealPlanRepo.deleteAll();   // Then meal plans (they reference restaurants)
            restaurantRepo.deleteAll(); // Finally restaurants

            // Create Restaurants
            Restaurant mainCafe = new Restaurant(null, "Main Campus Cafeteria", 
                "Building A, 123 University Ave", 150, null);
            Restaurant engBistro = new Restaurant(null, "Engineering Bistro", 
                "Building B, 456 Innovation Drive", 75, null);

            List<Restaurant> savedRestaurants = restaurantRepo.saveAll(List.of(mainCafe, engBistro));
            mainCafe = savedRestaurants.get(0);
            engBistro = savedRestaurants.get(1);

            // Create Meal Plans (2 for main cafe, 1 for engineering bistro)
            MealPlan mpToday = new MealPlan(null, LocalDate.now(), mainCafe, null, 120);
            MealPlan mpTomorrow = new MealPlan(null, LocalDate.now().plusDays(1), mainCafe, null, 110);
            MealPlan mpEngToday = new MealPlan(null, LocalDate.now(), engBistro, null, 50);

            List<MealPlan> savedMealPlans = mealPlanRepo.saveAll(List.of(
                mpToday, mpTomorrow, mpEngToday
            ));
            mpToday = savedMealPlans.get(0);
            mpTomorrow = savedMealPlans.get(1);
            mpEngToday = savedMealPlans.get(2);

            // Create Menu Items for each meal plan
            // Main Cafe - Today's Meal Plan
            MenuItem chicken = new MenuItem(null, "Grilled Chicken", 
                "With roasted vegetables and mashed potatoes", 
                MenuType.MAIN_COURSE, new BigDecimal("12.99"), mpToday);
            
            MenuItem lasagna = new MenuItem(null, "Vegetable Lasagna", 
                "Layers of pasta with seasonal vegetables and cheese", 
                MenuType.VEGETARIAN, new BigDecimal("10.50"), mpToday);
            
            // Main Cafe - Tomorrow's Meal Plan
            MenuItem burger = new MenuItem(null, "Beef Burger", 
                "200g beef patty with cheddar and bacon", 
                MenuType.MAIN_COURSE, new BigDecimal("14.25"), mpTomorrow);
            
            MenuItem pizza = new MenuItem(null, "Margherita Pizza", 
                "Classic pizza with fresh mozzarella and basil", 
                MenuType.VEGETARIAN, new BigDecimal("11.00"), mpTomorrow);
            
            // Engineering Bistro - Today's Meal Plan
            MenuItem salad = new MenuItem(null, "Tuna Salad", 
                "With mixed greens, cherry tomatoes and balsamic dressing", 
                MenuType.FISH, new BigDecimal("9.75"), mpEngToday);
            
            MenuItem shake = new MenuItem(null, "Protein Shake", 
                "Chocolate flavor with whey protein", 
                MenuType.DRINK, new BigDecimal("6.50"), mpEngToday);

            // Save all menu items
            menuItemRepo.saveAll(List.of(
                chicken, lasagna, burger, pizza, salad, shake
            ));

            System.out.println("Sample data loaded successfully!");
        };
    }
}