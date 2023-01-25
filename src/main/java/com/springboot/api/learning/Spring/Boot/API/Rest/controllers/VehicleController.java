package com.springboot.api.learning.Spring.Boot.API.Rest.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.learning.Spring.Boot.API.Rest.models.VehicleModel;
import com.springboot.api.learning.Spring.Boot.API.Rest.services.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<ArrayList<VehicleModel>> getVehicles(){
        ArrayList<VehicleModel> vehicles = this.vehicleService.getVehicles();
        if (vehicles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(vehicles);
    }

    @GetMapping(path = "/{licensePlate}")
    public ResponseEntity<VehicleModel> getVehicles(@PathVariable(value="licensePlate") String licensePlate){
        Optional<VehicleModel> vehicle = this.vehicleService.getVehicleByLicensePlate(licensePlate);
        if (vehicle.isPresent()) {
            return ResponseEntity.ok().body(vehicle.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public VehicleModel saveVehicle(@Validated @RequestBody VehicleModel model){
        return this.vehicleService.saveVehicle(model);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteVehicle(String licensePlate){
        Optional<VehicleModel> vehicle = this.vehicleService.getVehicleByLicensePlate(licensePlate);
        if (vehicle.isPresent()) {
            boolean result = this.vehicleService.deleteVehicle(licensePlate);
            if (result)
                return ResponseEntity.ok().body(String.format("Vehicle with license plate %s has been deleted", licensePlate));
            else
                return ResponseEntity.internalServerError().body(String.format("Could not delete vehicle with license plate %s", licensePlate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
