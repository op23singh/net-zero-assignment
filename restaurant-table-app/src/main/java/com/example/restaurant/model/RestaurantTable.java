package com.example.restaurant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Table name cannot be blank")
    private String name;

    @Min(value = 1, message = "Table must have at least one seat")
    private int numberOfSeats;
    private String area;
    private boolean reservable = true;
    @Version
    private Long version;
}
