package com.car_sales_garage.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FuelType {
    DIESEL("DIESEL"),
    ELECTRIC("ELECTRIC"),
    HYBRID("HYBRID");
    private final String value;
}
