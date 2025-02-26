package com.car_sales_garage.model.entity;

import com.car_sales_garage.config.BaseEntity;
import com.car_sales_garage.model.enumeration.FuelType;
import com.car_sales_garage.model.enumeration.Transmission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "car")
public class Car extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "make_id", nullable = false)
    private Make make;
    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "fuelType", nullable = false)
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Column(name = "mileage", nullable = false)
    private Integer mileage;
    @Column(name = "transmission", nullable = false)
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Column(name = "picture", nullable = false)
    private String picture;
}
