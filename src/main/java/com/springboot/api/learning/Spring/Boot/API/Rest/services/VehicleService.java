package com.springboot.api.learning.Spring.Boot.API.Rest.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.learning.Spring.Boot.API.Rest.models.VehicleModel;
import com.springboot.api.learning.Spring.Boot.API.Rest.repositories.VehicleRepository;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public ArrayList<VehicleModel> getVehicles() {
        return (ArrayList<VehicleModel>) this.vehicleRepository.findAll();
    }

    public Optional<VehicleModel> getVehicleByLicensePlate(String licensePlate) {
        return this.vehicleRepository.findById(licensePlate);
    }

    public VehicleModel saveVehicle(VehicleModel model) {
        return this.vehicleRepository.save(model);
    }

    public boolean deleteVehicle(String licensePlate) {
        try {
            this.vehicleRepository.deleteById(licensePlate);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
