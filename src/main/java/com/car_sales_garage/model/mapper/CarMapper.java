package com.car_sales_garage.model.mapper;

import com.car_sales_garage.model.dto.CarDTO;
import com.car_sales_garage.model.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper extends BasicMapper<Car, CarDTO> {
}
