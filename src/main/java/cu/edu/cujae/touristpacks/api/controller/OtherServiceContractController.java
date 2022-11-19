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

import cu.edu.cujae.touristpacks.core.dto.OtherServiceContractDto;
import cu.edu.cujae.touristpacks.core.service.IOtherServiceContractService;

@RestController
@RequestMapping("/api/v1/other_service_contracts")
public class OtherServiceContractController {

    @Autowired
    private IOtherServiceContractService otherServiceContractService;

    @GetMapping("/")
    public ResponseEntity<List<OtherServiceContractDto>> getAll() throws SQLException {
        List<OtherServiceContractDto> list = otherServiceContractService.getOtherServiceContracts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OtherServiceContractDto> getById(@PathVariable Integer id) throws SQLException {
        OtherServiceContractDto otherServiceContract = otherServiceContractService.getOtherServiceContractById(id);
        return ResponseEntity.ok(otherServiceContract);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody OtherServiceContractDto otherServiceContract)
            throws SQLException {
        otherServiceContractService.createOtherServiceContract(otherServiceContract);
        return ResponseEntity.ok("OtherServiceContract Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody OtherServiceContractDto otherServiceContract)
            throws SQLException {
        otherServiceContractService.updateOtherServiceContract(otherServiceContract);
        return ResponseEntity.ok("OtherServiceContract Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        otherServiceContractService.deleteOtherServiceContract(id);
        return ResponseEntity.ok("OtherServiceContract deleted");
    }
}
