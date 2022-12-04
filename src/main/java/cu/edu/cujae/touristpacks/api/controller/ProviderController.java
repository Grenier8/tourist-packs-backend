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

import cu.edu.cujae.touristpacks.core.dto.ProviderDto;
import cu.edu.cujae.touristpacks.core.service.IProviderService;

@RestController
@RequestMapping("/api/v1/providers")
public class ProviderController {

	@Autowired
    private IProviderService providerService;

    @GetMapping("/")
    public ResponseEntity<List<ProviderDto>> getAll() throws SQLException {
        List<ProviderDto> list = providerService.getProviders();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDto> getById(@PathVariable Integer id) throws SQLException {
        ProviderDto provider = providerService.getProviderById(id);
        return ResponseEntity.ok(provider);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody ProviderDto provider) throws SQLException {
        providerService.createProvider(provider);
        return ResponseEntity.ok("Provider Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody ProviderDto provider) throws SQLException {
        providerService.updateProvider(provider);
        return ResponseEntity.ok("Provider Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        providerService.deleteProvider(id);
        return ResponseEntity.ok("Provider deleted");
    }
}
