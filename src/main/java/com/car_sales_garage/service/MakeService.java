package com.car_sales_garage.service;

import com.car_sales_garage.model.entity.Make;
import com.car_sales_garage.repository.MakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeService {

    private final MakeRepository repository;

    public MakeService(MakeRepository repository) {
        this.repository = repository;
    }

    public List<Make> findAll() {
        return repository.findAll();
    }

}
