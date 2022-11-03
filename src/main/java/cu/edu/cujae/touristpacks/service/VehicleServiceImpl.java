package cu.edu.cujae.touristpacks.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cu.edu.cujae.touristpacks.core.dto.VehicleDto;
import cu.edu.cujae.touristpacks.core.service.IVehicleService;

@Service
public class VehicleServiceImpl implements IVehicleService {

	@Override
	public List<VehicleDto> getVehicles() {
		List<VehicleDto> vehicles = new ArrayList<>();
		vehicles.add(new VehicleDto(1, "B 021 452", "Audi", 6, 5, 7, 2013));
		vehicles.add(new VehicleDto(2, "P 143 259", "Mercedes", 8, 6, 10, 2015));
		return vehicles;
	}

	@Override
	public VehicleDto getVehicleById(int vehicleId) {
		// TODO Auto-generated method stub
		return getVehicles().stream().filter(r -> r.getIdVehicle() == vehicleId).findFirst().get();
	}

	@Override
	public void createVehicle(VehicleDto vehicle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateVehicle(VehicleDto vehicle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteVehicle(int id) {
		// TODO Auto-generated method stub

	}

}
