package com.car_sales_garage.service;

import com.car_sales_garage.exception.NotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceUpdatePictureTests {
    @Mock
    private CarRepository repository;
    @Mock
    private FileService fileService;

    @InjectMocks
    private CarService service;

    @Test
    void updatePicture_ShouldUpdateAndReturnCarWithNewPicturePath_WhenCarExistsAndPictureIsNotNull() {
        Long carId = 1L;
        Car existingCar = new Car();
        MultipartFile picture = mock(MultipartFile.class);
        String newPicturePath = "uploads/new_picture.jpg";

        when(repository.findById(carId)).thenReturn(Optional.of(existingCar));
        when(fileService.saveAndReturnPath(picture)).thenReturn(newPicturePath);
        when(repository.save(any(Car.class))).thenReturn(existingCar);
        Car updatedCar = service.updatePicture(carId, picture);

        verify(repository).findById(carId);
        verify(fileService).saveAndReturnPath(picture);
        assertEquals(updatedCar.getId(), existingCar.getId());
        assertEquals(newPicturePath, updatedCar.getPicture());
    }

    @Test
    void updatePicture_ShouldUpdateAndReturnCarWithNewPicture_WhenCarExistsAndPictureIsNotNull() {
        Long carId = 1L;
        Car car = new Car();
        car.setPicture("uploads/old_picture.jpg");
        MultipartFile newPicture = mock(MultipartFile.class);
        String newPicturePath = "uploads/new_picture.jpg";

        when(repository.findById(carId)).thenReturn(Optional.of(car));
        when(fileService.saveAndReturnPath(newPicture)).thenReturn(newPicturePath);
        when(repository.save(car)).thenReturn(car);

        Car result = service.updatePicture(carId, newPicture);

        assertNotNull(result);
        assertEquals(newPicturePath, result.getPicture());
    }

    @Test
    void updatePicture_ShouldUpdateOnlyPicture_WhenCarExistsAndPictureIsNotNull() {
        Long carId = 1L;
        Car car = new Car();
        Make make = new Make();
        make.setId(2L);
        car.setMake(make);
        Model model = new Model();
        model.setId(3L);
        car.setModel(model);
        LocalDate registrationDate = LocalDate.of(2020, 12, 07);
        car.setRegistrationDate(registrationDate);
        car.setPrice(BigDecimal.valueOf(25000));
        car.setFuelType(FuelType.HYBRID);
        car.setMileage(50000);
        car.setTransmission(Transmission.AUTOMATIC);
        MultipartFile picture = mock(MultipartFile.class);
        String newPicturePath = "uploads/new_picture.jpg";

        when(repository.findById(carId)).thenReturn(Optional.of(car));
        when(fileService.saveAndReturnPath(picture)).thenReturn(newPicturePath);
        when(repository.save(any(Car.class))).thenReturn(car);

        Car updatedCar = service.updatePicture(carId, picture);

        assertEquals(2L, updatedCar.getMake().getId());
        assertEquals(3L, updatedCar.getModel().getId());
        assertEquals(registrationDate, updatedCar.getRegistrationDate());
        assertEquals(BigDecimal.valueOf(25000), updatedCar.getPrice());
        assertEquals(FuelType.HYBRID, updatedCar.getFuelType());
        assertEquals(50000, updatedCar.getMileage());
        assertEquals(Transmission.AUTOMATIC, updatedCar.getTransmission());
        assertEquals(newPicturePath, updatedCar.getPicture());
    }

    @Test
    void updatePicture_ShouldThrowException_WhenCarDoesNotExist() {
        Long invalidCarId = 999L;
        MultipartFile picture = mock(MultipartFile.class);

        when(repository.findById(invalidCarId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                service.updatePicture(invalidCarId, picture));
    }

    @Test
    void updatePicture_ShouldThrowException_WhenCarPictureIsNUll() {
        Long carId = 1L;
        Car car = new Car();

        when(repository.findById(carId)).thenReturn(Optional.of(car));
        when(fileService.saveAndReturnPath(null))
                .thenThrow(new IllegalArgumentException("File is required"));

        assertThrows(IllegalArgumentException.class, () ->
                service.updatePicture(carId, null));
    }

}
