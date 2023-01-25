package com.springboot.api.learning.Spring.Boot.API.Rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.api.learning.Spring.Boot.API.Rest.models.VehicleModel;

@Repository
public interface VehicleRepository extends CrudRepository<VehicleModel, String> {
    
}
