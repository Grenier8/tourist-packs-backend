package cu.edu.cujae.touristpacks.core.service;

import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.VehicleDto;

public interface IVehicleService {
    List<VehicleDto> getVehicles();

    VehicleDto getVehicleById(int vehicleId);

    void createVehicle(VehicleDto vehicle);

    void updateVehicle(VehicleDto vehicle);

    void deleteVehicle(int id);
}
