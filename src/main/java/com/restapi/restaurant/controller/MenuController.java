package com.restapi.restaurant.controller;

import com.restapi.restaurant.model.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    
    private List<MenuItem> menuItems = new ArrayList<>();
    private Long nextId = 26977L;

    public MenuController() {
        menuItems.add(new MenuItem(26965L, "Rodrigue's Special Platter", "Chef Irakoze's signature dish", 29.99, "Main Course", true));
        menuItems.add(new MenuItem(26966L, "Spring Rolls", "Crispy vegetable spring rolls", 7.99, "Appetizer", true));
        menuItems.add(new MenuItem(26967L, "Bruschetta", "Toasted bread with tomato and basil", 6.99, "Appetizer", true));
        menuItems.add(new MenuItem(26968L, "BBQ Ribs", "Slow-cooked pork ribs with BBQ sauce", 19.99, "Main Course", true));
        menuItems.add(new MenuItem(26969L, "Salmon Teriyaki", "Grilled salmon with teriyaki glaze", 22.99, "Main Course", true));
        menuItems.add(new MenuItem(26970L, "Mushroom Risotto", "Creamy Italian rice with mushrooms", 14.99, "Main Course", false));
        menuItems.add(new MenuItem(26971L, "Tiramisu", "Classic Italian coffee dessert", 7.99, "Dessert", true));
        menuItems.add(new MenuItem(26972L, "Cheesecake", "New York style cheesecake", 6.99, "Dessert", true));
        menuItems.add(new MenuItem(26973L, "Iced Tea", "Freshly brewed iced tea", 2.99, "Beverage", true));
        menuItems.add(new MenuItem(26974L, "Lobster Bisque", "Creamy lobster soup", 11.99, "Appetizer", true));
        menuItems.add(new MenuItem(26975L, "Lamb Chops", "Grilled lamb with mint sauce", 26.99, "Main Course", true));
        menuItems.add(new MenuItem(26976L, "Mango Smoothie", "Fresh mango smoothie", 4.99, "Beverage", true));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuItems.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> result = menuItems.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableMenuItems(@RequestParam boolean available) {
        List<MenuItem> result = menuItems.stream()
                .filter(item -> item.isAvailable() == available)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuItemsByName(@RequestParam String name) {
        List<MenuItem> result = menuItems.stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        menuItem.setId(nextId++);
        menuItems.add(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        boolean removed = menuItems.removeIf(item -> item.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
