package com.car_sales_garage.service;

import com.car_sales_garage.model.entity.Car;
import com.car_sales_garage.model.entity.Make;
import com.car_sales_garage.model.entity.Model;
import com.car_sales_garage.model.enumeration.FuelType;
import com.car_sales_garage.model.enumeration.Transmission;
import com.car_sales_garage.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceSaveTests {
    @Mock
    private CarRepository repository;
    @Mock
    private FileService fileService;

    @InjectMocks
    private CarService service;


    @Test
    void updatePicture_ShouldSaveAndReturnCar_WhenCarIsValidatedAndPictureIsNotNull() {
        Car car = new Car();
        car.setRegistrationDate(LocalDate.now());
        MultipartFile picture = mock(MultipartFile.class);
        String expectedPath = "uploads/picture.jpg";
        when(fileService.saveAndReturnPath(picture)).thenReturn(expectedPath);
        when(repository.save(car)).thenReturn(car);

        Car savedCar = service.save(car, picture);

        verify(repository).save(car);
        assertNotNull(savedCar);
        assertEquals(expectedPath, car.getPicture());
    }

    @Test
    void updatePicture_ShouldSaveAndReturnCar_WhenCarAllRequiredFieldsAreNotNull() {
        Car car = new Car();
        car.setRegistrationDate(LocalDate.now());
        car.setMake(new Make());
        car.setModel(new Model());
        car.setPrice(BigDecimal.valueOf(1000));
        car.setFuelType(FuelType.DIESEL);
        car.setMileage(50000);
        car.setTransmission(Transmission.MANUAL);

        MultipartFile picture = mock(MultipartFile.class);

        when(fileService.saveAndReturnPath(picture)).thenReturn("path");
        when(repository.save(car)).thenReturn(car);

        Car savedCar = service.save(car, picture);

        assertNotNull(savedCar.getMake());
        assertNotNull(savedCar.getModel());
        assertNotNull(savedCar.getRegistrationDate());
        assertNotNull(savedCar.getPrice());
        assertNotNull(savedCar.getFuelType());
        assertNotNull(savedCar.getMileage());
        assertNotNull(savedCar.getTransmission());
        assertNotNull(savedCar.getPicture());
    }

    @Test
    void updatePicture_ShouldThrowException_WhenCarRegistrationDateIsNull() {
        Car car = new Car();
        car.setRegistrationDate(null);
        MultipartFile picture = mock(MultipartFile.class);

        assertThrows(IllegalArgumentException.class, () ->
                service.save(car, picture)
        );
    }

    @Test
    void updatePicture_ShouldThrowException_WhenCarRegistrationDateIsBefore2015() {
        Car car = new Car();
        car.setRegistrationDate(LocalDate.of(2014, 1, 1));
        MultipartFile picture = mock(MultipartFile.class);

        assertThrows(IllegalArgumentException.class, () ->
                service.save(car, picture)
        );
    }

    @Test
    void updatePicture_ShouldThrowException_WhenPictureIsNull() {
        Car car = new Car();
        car.setRegistrationDate(LocalDate.now());

        when(fileService.saveAndReturnPath(null))
                .thenThrow(new IllegalArgumentException("File is required"));

        assertThrows(IllegalArgumentException.class, () ->
                service.save(car, null)
        );
    }

    @Test
    void updatePicture_ShouldThrowException_WhenCarIsNull() {
        Car car = null;
        MultipartFile picture = mock(MultipartFile.class);

        assertThrows(NullPointerException.class, () ->
                service.save(car, picture)
        );
    }

}
