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

import cu.edu.cujae.touristpacks.core.dto.TransportContractDto;
import cu.edu.cujae.touristpacks.core.service.ITransportContractService;

@RestController
@RequestMapping("/api/v1/transport_contracts")
public class TransportContractController {
	
    @Autowired
    private ITransportContractService TransportContractService;

    @GetMapping("/")
    public ResponseEntity<List<TransportContractDto>> getAll() throws SQLException {
        List<TransportContractDto> list = TransportContractService.getTransportContracts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportContractDto> getById(@PathVariable Integer id) throws SQLException {
        TransportContractDto TransportContract = TransportContractService.getTransportContractById(id);
        return ResponseEntity.ok(TransportContract);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody TransportContractDto transportContract) throws SQLException {
        TransportContractService.createTransportContract(transportContract);
        return ResponseEntity.ok("TransportContract Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody TransportContractDto transportContract) throws SQLException {
        TransportContractService.updateTransportContract(transportContract);
        return ResponseEntity.ok("TransportContract Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        TransportContractService.deleteTransportContract(id);
        return ResponseEntity.ok("TransportContract deleted");
    }
}
