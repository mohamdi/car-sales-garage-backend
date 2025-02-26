package com.car_sales_garage.model.entity;

import com.car_sales_garage.config.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "car_make")
public class Make extends BaseEntity {
    private String name;
}
