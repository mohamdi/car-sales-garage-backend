package com.car_sales_garage.controller;

import com.car_sales_garage.model.dto.MakeDTO;
import com.car_sales_garage.model.mapper.MakeMapper;
import com.car_sales_garage.service.MakeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Make service", description = "Make service REST APIs")
@RestController
@RequestMapping("/api/v1/make")
public class MakeController {

    private final MakeService service;
    private final MakeMapper mapper;

    public MakeController(MakeService service, MakeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Get makes",
            description = "Returns a list of makes",
            tags = {"Make service"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found")
            }
    )
    @GetMapping
    public ResponseEntity<List<MakeDTO>> findAll() {
        return ResponseEntity.ok(mapper.toDtos(service.findAll()));
    }

}
