package com.car_sales_garage.model.dto;

import com.car_sales_garage.config.BaseDTO;
import com.car_sales_garage.model.enumeration.FuelType;
import com.car_sales_garage.model.enumeration.Transmission;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarDTO extends BaseDTO {
    private MakeDTO make;
    private ModelDTO model;
    private LocalDate registrationDate;
    private BigDecimal price;
    private FuelType fuelType;
    private Integer mileage;
    private Transmission transmission;
    private String picture;
}
