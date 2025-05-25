package com.example.restaurant.integration;

import com.example.restaurant.model.RestaurantTable;
import com.example.restaurant.repository.RestaurantTableRepository;
import com.example.restaurant.service.RestaurantTableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RestaurantTableIntegrationTest {

    @Autowired
    private RestaurantTableRepository repository;

    @Autowired
    private RestaurantTableService service;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    public void testCreateAndGetTable() {
        // Create a table
        RestaurantTable table = new RestaurantTable(null, "Table 1", 4, "Indoor", true, null);
        RestaurantTable created = service.createTable(table);

        // Verify it was created with an ID
        assertNotNull(created.getId());

        // Get the table by ID
        RestaurantTable retrieved = service.getTableById(created.getId());

        // Verify it matches
        assertEquals("Table 1", retrieved.getName());
        assertEquals(4, retrieved.getNumberOfSeats());
    }

    @Test
    public void testConcurrentReservation() throws Exception {
        // Create a table
        RestaurantTable table = RestaurantTable.builder()
                .name("Test Table")
                .area("Indoor")
                .numberOfSeats(4)
                .reservable(true)
                .build();

        RestaurantTable savedTable = repository.save(table);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(1); // To start both threads at once

        Callable<Boolean> reserveTask = () -> {
            latch.await(); // Wait for signal
            try {
                service.reserveTable(savedTable.getId());
                return true;
            } catch (OptimisticLockingFailureException e) {
                return false;
            }
        };

        Future<Boolean> result1 = executor.submit(reserveTask);
        Future<Boolean> result2 = executor.submit(reserveTask);

        // Start both threads
        latch.countDown();

        boolean success1 = result1.get();
        boolean success2 = result2.get();

        // One should succeed, one should fail
        assertTrue(success1 || success2);
        assertFalse(success1 && success2);

        executor.shutdown();
    }
}