package com.car_sales_garage.controller;

import com.car_sales_garage.model.dto.ModelDTO;
import com.car_sales_garage.model.mapper.ModelMapper;
import com.car_sales_garage.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Model service", description = "Model service REST APIs")
@RestController
@RequestMapping("/api/v1/model")
public class ModelController {

    private final ModelService service;
    private final ModelMapper mapper;

    public ModelController(ModelService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Get models",
            description = "Returns a list of models",
            tags = {"Model service"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found")
            }
    )
    @GetMapping
    public ResponseEntity<List<ModelDTO>> findAll() {
        return ResponseEntity.ok(mapper.toDtos(service.findAll()));
    }

}
