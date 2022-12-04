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

import cu.edu.cujae.touristpacks.core.dto.TransportContractTransportServiceDto;
import cu.edu.cujae.touristpacks.core.service.ITransportContractTransportServiceService;

@RestController
@RequestMapping("/api/v1/transport_contract_transport_services")
public class TransportContractTransportServiceController {

    @Autowired
    private ITransportContractTransportServiceService TransportContractTransportServiceService;

    @GetMapping("/")
    public ResponseEntity<List<TransportContractTransportServiceDto>> getAll() throws SQLException {
        List<TransportContractTransportServiceDto> list = TransportContractTransportServiceService.getTransportContractTransportServices();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportContractTransportServiceDto> getById(@PathVariable Integer id) throws SQLException {
        TransportContractTransportServiceDto transportContractTransportService = TransportContractTransportServiceService.getTransportContractTransportServiceById(id);
        return ResponseEntity.ok(transportContractTransportService);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody TransportContractTransportServiceDto transportContract) throws SQLException {
        TransportContractTransportServiceService.createTransportContractTransportService(transportContract);
        return ResponseEntity.ok("TransportContractTransportService Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody TransportContractTransportServiceDto transportContract) throws SQLException {
        TransportContractTransportServiceService.updateTransportContractTransportService(transportContract);
        return ResponseEntity.ok("TransportContractTransportService Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        TransportContractTransportServiceService.deleteTransportContractTransportService(id);
        return ResponseEntity.ok("TransportContractTransportService deleted");
    }
}
