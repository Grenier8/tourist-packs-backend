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

import cu.edu.cujae.touristpacks.core.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.core.service.ITransportServiceService;

@RestController
@RequestMapping("/api/v1/transport_services")
public class TransportServiceController {

	@Autowired
	private ITransportServiceService transportServiceService;

	@GetMapping("/")
	public ResponseEntity<List<TransportServiceDto>> getAll() throws SQLException {
		List<TransportServiceDto> list = transportServiceService.getTransportServices();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransportServiceDto> getById(@PathVariable Integer id) throws SQLException {
		TransportServiceDto transportService = transportServiceService.getTransportServiceById(id);
		return ResponseEntity.ok(transportService);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<TransportServiceDto> getByName(@PathVariable String name) throws SQLException {
		TransportServiceDto transportService = transportServiceService.getTransportServiceByName(name);
		return ResponseEntity.ok(transportService);
	}

	@PostMapping("/")
	public ResponseEntity<String> create(@RequestBody TransportServiceDto transportService) throws SQLException {
		transportServiceService.createTransportService(transportService);
		return ResponseEntity.ok("TransportService Created");
	}

	@PutMapping("/")
	public ResponseEntity<String> update(@RequestBody TransportServiceDto transportService) throws SQLException {
		transportServiceService.updateTransportService(transportService);
		return ResponseEntity.ok("TransportService Updated");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
		transportServiceService.deleteTransportService(id);
		return ResponseEntity.ok("TransportService deleted");
	}
}
