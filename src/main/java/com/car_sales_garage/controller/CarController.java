package com.car_sales_garage.controller;

import com.car_sales_garage.model.dto.CarDTO;
import com.car_sales_garage.model.enumeration.FuelType;
import com.car_sales_garage.model.mapper.CarMapper;
import com.car_sales_garage.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "Car service", description = "Cars service REST APIs")
@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    private final CarService service;
    private final CarMapper mapper;
    private final ObjectMapper objectMapper;

    public CarController(CarService service, CarMapper mapper, ObjectMapper objectMapper) {
        this.service = service;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @Operation(
            summary = "Save car",
            description = "Add a new car to the garage",
            tags = {"Car service"},
            parameters = {
                    @Parameter(name = "car", description = "Car object in json format", required = true),
                    @Parameter(name = "picture", description = "File", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully saved"),
                    @ApiResponse(responseCode = "400", description = "Invalid car object")
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CarDTO> save(
            @RequestParam("car") String car,
            @RequestParam("picture") MultipartFile picture
    ) throws JsonProcessingException {
        CarDTO dto = objectMapper.readValue(car, CarDTO.class);
        CarDTO savedCard = mapper.toDto(service.save(mapper.toEntity(dto), picture));
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get cars by fuelType and maxPrice",
            description = "Returns a list of cars with a specific fuelType and price <= the specified price",
            tags = {"Car service"},
            parameters = {
                    @Parameter(name = "fuelType", description = "FuelType for the search", required = true),
                    @Parameter(name = "maxPrice", description = "MaxPrice in a numeric format", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found")
            }
    )
    @GetMapping("/by-fuel-type-and-max-price")
    public ResponseEntity<List<CarDTO>> findAllByFuelTypeAndMaxPrice(
            @RequestParam("fuelType") FuelType fuelType,
            @RequestParam("maxPrice") BigDecimal maxPrice
    ) {
        List<CarDTO> cars = mapper.toDtos(service.findAllByFuelTypeAndMaxPrice(fuelType, maxPrice));
        return ResponseEntity.ok(cars);
    }

    @Operation(
            summary = "Update car picture",
            description = "Update a specific car's picture and return the car object",
            tags = {"Car service"},
            parameters = {
                    @Parameter(name = "carId", description = "Id of the car in a numeric format", required = true),
                    @Parameter(name = "picture", description = "File", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated")
            }
    )
    @PutMapping(value = "/update-picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CarDTO> updatePicture(@RequestParam("carId") Long carId, @RequestParam("picture") MultipartFile picture) {
        CarDTO car = mapper.toDto(service.updatePicture(carId, picture));
        return ResponseEntity.ok(car);
    }

}
