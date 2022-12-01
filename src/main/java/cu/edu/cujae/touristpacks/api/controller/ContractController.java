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

import cu.edu.cujae.touristpacks.core.dto.ContractDto;
import cu.edu.cujae.touristpacks.core.service.IContractService;

@RestController
@RequestMapping("/api/v1/contracts")
public class ContractController {

    @Autowired
    private IContractService contractService;

    @GetMapping("/")
    public ResponseEntity<List<ContractDto>> getAll() throws SQLException {
        List<ContractDto> list = contractService.getContracts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getById(@PathVariable Integer id) throws SQLException {
        ContractDto contract = contractService.getContractById(id);
        return ResponseEntity.ok(contract);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<ContractDto> getByTitle(@PathVariable String title) throws SQLException {
        ContractDto contract = contractService.getContractByTitle(title);
        return ResponseEntity.ok(contract);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody ContractDto contract) throws SQLException {
        contractService.createContract(contract);
        return ResponseEntity.ok("Contract Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody ContractDto contract) throws SQLException {
        contractService.updateContract(contract);
        return ResponseEntity.ok("Contract Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        contractService.deleteContract(id);
        return ResponseEntity.ok("Contract deleted");
    }
}
