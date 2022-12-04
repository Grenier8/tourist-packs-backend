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

import cu.edu.cujae.touristpacks.core.dto.TransportModalityDto;
import cu.edu.cujae.touristpacks.core.service.ITransportModalityService;

@RestController
@RequestMapping("/api/v1/transport_modalities")
public class TransportModalityController {

	@Autowired
    private ITransportModalityService transportModalityService;

    @GetMapping("/")
    public ResponseEntity<List<TransportModalityDto>> getAll() throws SQLException {
        List<TransportModalityDto> list = transportModalityService.getTransportModalities();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportModalityDto> getById(@PathVariable Integer id) throws SQLException {
        TransportModalityDto TransportModality = transportModalityService.getTransportModalityById(id);
        return ResponseEntity.ok(TransportModality);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody TransportModalityDto transportModality) throws SQLException {
        transportModalityService.createTransportModality(transportModality);
        return ResponseEntity.ok("TransportModality Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody TransportModalityDto transportModality) throws SQLException {
        transportModalityService.updateTransportModality(transportModality);
        return ResponseEntity.ok("TransportModality Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        transportModalityService.deleteTransportModality(id);
        return ResponseEntity.ok("TransportModality deleted");
    }
}
