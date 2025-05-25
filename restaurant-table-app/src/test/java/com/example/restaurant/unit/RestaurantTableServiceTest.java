package com.example.restaurant.unit;

import com.example.restaurant.exception.ResourceNotFoundException;
import com.example.restaurant.model.RestaurantTable;
import com.example.restaurant.repository.RestaurantTableRepository;
import com.example.restaurant.service.impl.RestaurantTableServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantTableServiceTest {

    @Mock
    private RestaurantTableRepository repository;

    private RestaurantTableServiceImpl service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new RestaurantTableServiceImpl(repository);
    }

    @Test
    public void testCreateTable() {
        RestaurantTable table = new RestaurantTable(1L, "Table 1", 4, "Indoor", true,1L);
        when(repository.save(table)).thenReturn(table);
        RestaurantTable result = service.createTable(table);
        assertEquals(1L, result.getId());
        verify(repository).save(table);
    }

    @Test
    public void testGetTableById_found() {
        RestaurantTable table = new RestaurantTable(1L, "Table 1", 4, "Indoor", true,1L);
        when(repository.findById(1L)).thenReturn(Optional.of(table));

        RestaurantTable result = service.getTableById(1L);
        assertEquals("Table 1", result.getName());
    }

    @Test
    public void testDeleteTable_notFound() {
        when(repository.existsById(2L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> service.deleteTable(2L));
    }
}
