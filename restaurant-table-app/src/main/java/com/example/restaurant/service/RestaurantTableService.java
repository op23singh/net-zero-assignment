package com.example.restaurant.service;

import com.example.restaurant.model.RestaurantTable;

import java.util.List;

public interface RestaurantTableService {
    List<RestaurantTable> getAllTables();
    RestaurantTable getTableById(Long id);
    RestaurantTable createTable(RestaurantTable table);
    RestaurantTable updateTable(Long id, RestaurantTable table);
    RestaurantTable reserveTable(Long id);
    void deleteTable(Long id);
}
