package com.car_sales_garage.config;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
