package com.car_sales_garage.repository;

import com.car_sales_garage.model.entity.Car;
import com.car_sales_garage.model.enumeration.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByFuelTypeAndPriceLessThanEqual(FuelType fuelType, BigDecimal maxPrice);
}
