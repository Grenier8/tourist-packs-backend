package cu.edu.cujae.touristpacks.api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.touristpacks.core.dto.VehicleDto;
import cu.edu.cujae.touristpacks.core.service.IVehicleService;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
	
	    @Autowired
	    private IVehicleService vehicleService;

	    @GetMapping("/")
	    public ResponseEntity<List<VehicleDto>> getAll() throws SQLException {
	        List<VehicleDto> list = vehicleService.getVehicles();
	        return ResponseEntity.ok(list);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<VehicleDto> getById(@PathVariable Integer id) throws SQLException {
	        VehicleDto vehicle = vehicleService.getVehicleById(id);
	        return ResponseEntity.ok(vehicle);
	    }

	    @PostMapping("/")
	    public ResponseEntity<String> create(@RequestBody VehicleDto vehicle) throws SQLException {
	        vehicleService.createVehicle(vehicle);
	        return ResponseEntity.ok("Vehicle Created");
	    }

	    @PutMapping("/")
	    public ResponseEntity<String> update(@RequestBody VehicleDto vehicle) throws SQLException {
	        vehicleService.updateVehicle(vehicle);
	        return ResponseEntity.ok("Vehicle Updated");
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
	        vehicleService.deleteVehicle(id);
	        return ResponseEntity.ok("Vehicle deleted");
	    }
}
