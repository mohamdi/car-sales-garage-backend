package com.car_sales_garage.service;

import com.car_sales_garage.model.entity.Car;
import com.car_sales_garage.model.enumeration.FuelType;
import com.car_sales_garage.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceFindAllByFuelTypeAndMaxPriceTests {
    @Mock
    private CarRepository repository;

    @InjectMocks
    private CarService service;

    @Test
    void findAllByFuelTypeAndPriceLessThanEqual_ShouldReturnList_WhenFuelTypeAndMaxPriceExists() {
        List<Car> expectedCars = List.of(new Car());
        when(repository.findAllByFuelTypeAndPriceLessThanEqual(FuelType.DIESEL, new BigDecimal("10000")))
                .thenReturn(expectedCars);

        List<Car> result = service.findAllByFuelTypeAndMaxPrice(FuelType.DIESEL, new BigDecimal("10000"));

        assertEquals(expectedCars, result);
        verify(repository).findAllByFuelTypeAndPriceLessThanEqual(FuelType.DIESEL, new BigDecimal("10000"));
    }

    @Test
    void findAllByFuelTypeAndPriceLessThanEqual_ShouldReturnEmptyList_WhenNoMatching() {
        when(repository.findAllByFuelTypeAndPriceLessThanEqual(any(), any()))
                .thenReturn(Collections.emptyList());

        List<Car> result = service.findAllByFuelTypeAndMaxPrice(FuelType.DIESEL, new BigDecimal("5000"));

        assertTrue(result.isEmpty());
    }

    @Test
    void findAllByFuelTypeAndPriceLessThanEqual_ShouldReturnListWithMatchingSize_WhenFuelTypeAndMaxPriceMatches() {
        List<Car> expectedCars = Arrays.asList(new Car(), new Car(), new Car());
        when(repository.findAllByFuelTypeAndPriceLessThanEqual(FuelType.HYBRID, new BigDecimal("20000")))
                .thenReturn(expectedCars);

        List<Car> result = service.findAllByFuelTypeAndMaxPrice(FuelType.HYBRID, new BigDecimal("20000"));

        assertEquals(3, result.size());
        assertEquals(expectedCars, result);
    }

    @Test
    void findAllByFuelTypeAndPriceLessThanEqual_ShouldReturnMatchingData_WhenFuelTypeAndMaxPriceMatches() {
        Car dieselCar = new Car();
        dieselCar.setFuelType(FuelType.DIESEL);
        dieselCar.setPrice(new BigDecimal("15000"));
        when(repository.findAllByFuelTypeAndPriceLessThanEqual(FuelType.DIESEL, new BigDecimal("19000")))
                .thenReturn(Collections.singletonList(dieselCar));

        List<Car> result = service.findAllByFuelTypeAndMaxPrice(FuelType.DIESEL, new BigDecimal("19000"));

        assertEquals(FuelType.DIESEL, result.getFirst().getFuelType());
        assertThat(new BigDecimal("19000").doubleValue(), greaterThan(result.getFirst().getPrice().doubleValue()));
    }

    @Test
    void findAllByFuelTypeAndPriceLessThanEqual_ShouldThrowException_WhenFuelTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                service.findAllByFuelTypeAndMaxPrice(null, new BigDecimal("10000")));
    }

    @Test
    void findAllByFuelTypeAndPriceLessThanEqual_ShouldThrowException_WhenMaxPriceIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                service.findAllByFuelTypeAndMaxPrice(FuelType.DIESEL, null));
    }
}
