package com.car_sales_garage.service;

import com.car_sales_garage.model.entity.Model;
import com.car_sales_garage.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    private final ModelRepository repository;

    public ModelService(ModelRepository repository) {
        this.repository = repository;
    }

    public List<Model> findAll() {
        return repository.findAll();
    }

}
