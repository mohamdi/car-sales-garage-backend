package com.car_sales_garage.model.mapper;

import com.car_sales_garage.model.dto.ModelDTO;
import com.car_sales_garage.model.entity.Model;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper extends BasicMapper<Model, ModelDTO> {
}
