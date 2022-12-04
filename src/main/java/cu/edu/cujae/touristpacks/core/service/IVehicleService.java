package cu.edu.cujae.touristpacks.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.touristpacks.core.dto.VehicleDto;

public interface IVehicleService {
    List<VehicleDto> getVehicles() throws SQLException;

    VehicleDto getVehicleById(int vehicleId) throws SQLException;

    void createVehicle(VehicleDto vehicle) throws SQLException;

    void updateVehicle(VehicleDto vehicle) throws SQLException;

    void deleteVehicle(int id) throws SQLException;
}
