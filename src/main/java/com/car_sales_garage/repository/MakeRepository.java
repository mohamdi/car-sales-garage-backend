package com.car_sales_garage.repository;

import com.car_sales_garage.model.entity.Make;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakeRepository extends JpaRepository<Make, Long> {

}
