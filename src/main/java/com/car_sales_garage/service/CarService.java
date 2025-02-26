package com.car_sales_garage.service;

import com.car_sales_garage.exception.NotFoundException;
import com.car_sales_garage.model.entity.Car;
import com.car_sales_garage.model.enumeration.FuelType;
import com.car_sales_garage.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository repository;
    private final FileService fileService;
    private static final int MIN_YEAR = 2015;

    public CarService(CarRepository repository, FileService fileService) {
        this.repository = repository;
        this.fileService = fileService;
    }

    public Car save(Car car, MultipartFile picture) {
        validateCar(car);
        car.setPicture(fileService.saveAndReturnPath(picture));
        return repository.save(car);
    }

    public List<Car> findAllByFuelTypeAndMaxPrice(FuelType fuelType, BigDecimal maxPrice) {
        if (fuelType == null) {
            throw new IllegalArgumentException("FuelType cannot be null");
        }
        if (maxPrice == null) {
            throw new IllegalArgumentException("MaxPrice cannot be null");
        }
        return repository.findAllByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice);
    }

    public Car updatePicture(Long carId, MultipartFile picture) {
        Car car = repository.findById(carId).orElseThrow(() -> new NotFoundException("Car not found"));
        car.setPicture(fileService.saveAndReturnPath(picture));
        return repository.save(car);
    }

    public void validateCar(Car car) {
        Optional.ofNullable(car.getRegistrationDate())
                .ifPresentOrElse(
                        registrationDate -> {
                            if (registrationDate.getYear() <= MIN_YEAR) {
                                throw new IllegalArgumentException("Car must be registered after " + MIN_YEAR);
                            }
                        },
                        () -> {
                            throw new IllegalArgumentException("Registration date is required");
                        }
                );

    }
}
