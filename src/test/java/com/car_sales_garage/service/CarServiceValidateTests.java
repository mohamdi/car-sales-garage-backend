package com.car_sales_garage.service;

import com.car_sales_garage.model.entity.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarServiceValidateTests {

    @InjectMocks
    private CarService carService;

    @Test
    void validateCar_ShouldNotThrowException_WhenRegistrationDateIsAfter2015() {
        Car car = new Car();
        car.setRegistrationDate(LocalDate.of(2017, 1, 1));

        assertDoesNotThrow(() -> carService.validateCar(car));
    }

    @Test
    void validateCar_ShouldThrowException_WhenRegistrationDateIsNull() {
        Car car = new Car();
        car.setRegistrationDate(null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> carService.validateCar(car));
        assertEquals("Registration date is required", exception.getMessage());
    }

    @Test
    void validateCar_ShouldThrowException_WhenRegistrationDateIs2015() {
        Car car = new Car();
        car.setRegistrationDate(LocalDate.of(2015, 1, 1));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> carService.validateCar(car));
        assertEquals("Car must be registered after 2015", exception.getMessage());
    }

    @Test
    void validateCar_ShouldThrowException_WhenRegistrationDateIsBefore2015() {
        Car car = new Car();
        car.setRegistrationDate(LocalDate.of(2014, 12, 31));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> carService.validateCar(car));
        assertEquals("Car must be registered after 2015", exception.getMessage());
    }

    @Test
    void validateCar_ShouldThrowException_WhenCarIsNull() {
        assertThrows(NullPointerException.class, () -> carService.validateCar(null));
    }

}
