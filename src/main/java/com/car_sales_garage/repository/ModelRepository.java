package com.car_sales_garage.repository;

import com.car_sales_garage.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {

}
