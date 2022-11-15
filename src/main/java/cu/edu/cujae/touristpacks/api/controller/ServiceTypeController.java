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

import cu.edu.cujae.touristpacks.core.dto.ServiceTypeDto;
import cu.edu.cujae.touristpacks.core.service.IServiceTypeService;

@RestController
@RequestMapping("/api/v1/serviceTypes")
public class ServiceTypeController {

    @Autowired
    private IServiceTypeService serviceTypeService;

    @GetMapping("/")
    public ResponseEntity<List<ServiceTypeDto>> getAll() throws SQLException {
        List<ServiceTypeDto> list = serviceTypeService.getServiceTypes();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeDto> getById(@PathVariable Integer id) throws SQLException {
        ServiceTypeDto serviceType = serviceTypeService.getServiceTypeById(id);
        return ResponseEntity.ok(serviceType);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody ServiceTypeDto serviceType) throws SQLException {
        serviceTypeService.createServiceType(serviceType);
        return ResponseEntity.ok("ServiceType Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody ServiceTypeDto serviceType) throws SQLException {
        serviceTypeService.updateServiceType(serviceType);
        return ResponseEntity.ok("ServiceType Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        serviceTypeService.deleteServiceType(id);
        return ResponseEntity.ok("ServiceType deleted");
    }
}
