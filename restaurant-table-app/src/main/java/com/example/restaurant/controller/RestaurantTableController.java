package com.example.restaurant.controller;

import com.example.restaurant.model.RestaurantTable;
import com.example.restaurant.service.RestaurantTableService;
import jakarta.persistence.OptimisticLockException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Min;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final RestaurantTableService service;

    @GetMapping
    public ResponseEntity<List<RestaurantTable>> getAllTables() {
        return ResponseEntity.ok(service.getAllTables());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTable> getTableById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTableById(id));
    }

    @PostMapping
    public ResponseEntity<RestaurantTable> createTable(@Valid @RequestBody RestaurantTable table) {
        return new ResponseEntity<>(service.createTable(table), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTable> updateTable(@PathVariable Long id, @Valid @RequestBody RestaurantTable table) {
        return ResponseEntity.ok(service.updateTable(id, table));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        service.deleteTable(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reserve")
    public ResponseEntity<RestaurantTable> reserveTable(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.reserveTable(id));
        } catch (OptimisticLockException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }
    }

}
