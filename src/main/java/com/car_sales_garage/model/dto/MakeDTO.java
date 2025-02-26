package com.car_sales_garage.model.dto;

import com.car_sales_garage.config.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MakeDTO extends BaseDTO {
    private String name;
}
