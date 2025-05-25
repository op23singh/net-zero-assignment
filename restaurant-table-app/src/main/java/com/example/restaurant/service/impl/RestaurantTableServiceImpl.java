package com.example.restaurant.service.impl;

import com.example.restaurant.exception.ResourceNotFoundException;
import com.example.restaurant.model.RestaurantTable;
import com.example.restaurant.repository.RestaurantTableRepository;
import com.example.restaurant.service.RestaurantTableService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantTableServiceImpl implements RestaurantTableService {

    private final RestaurantTableRepository repository;

    @Override
    public List<RestaurantTable> getAllTables() {
        return repository.findAll();
    }

    @Override
    public RestaurantTable getTableById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found with id " + id));
    }

    @Override
    public RestaurantTable createTable(RestaurantTable table) {
        return repository.save(table);
    }

    @Override
    public RestaurantTable updateTable(Long id, RestaurantTable newTable) {
        RestaurantTable existing = getTableById(id);
        existing.setName(newTable.getName());
        existing.setNumberOfSeats(newTable.getNumberOfSeats());
        existing.setArea(newTable.getArea());
        existing.setReservable(newTable.isReservable());
        return repository.save(existing);
    }

    @Override
    public void deleteTable(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Table not found with id " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public RestaurantTable reserveTable(Long id) {
        RestaurantTable table = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Table not found"));

        if (!table.isReservable()) {
            throw new IllegalStateException("Table is already reserved.");
        }

        table.setReservable(false);
        return repository.save(table);
    }

}
