package com.car_sales_garage.model.mapper;

import com.car_sales_garage.model.dto.MakeDTO;
import com.car_sales_garage.model.entity.Make;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MakeMapper extends BasicMapper<Make, MakeDTO> {
}
