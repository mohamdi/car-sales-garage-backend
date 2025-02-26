package com.car_sales_garage.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Transmission {
    MANUAL("MANUAL"),
    SEMI_AUTOMATIC("SEMI_AUTOMATIC"),
    AUTOMATIC("AUTOMATIC");

    private final String value;
}
