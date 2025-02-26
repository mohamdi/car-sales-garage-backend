package com.car_sales_garage.model.entity;

import com.car_sales_garage.config.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "car_model")
public class Model extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "make_id")
    private Make make;
    private String name;
}

