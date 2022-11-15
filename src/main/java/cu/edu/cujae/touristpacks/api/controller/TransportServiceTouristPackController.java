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

import cu.edu.cujae.touristpacks.core.dto.TransportServiceTouristPackDto;
import cu.edu.cujae.touristpacks.core.service.ITransportServiceTouristPackService;

@RestController
@RequestMapping("/api/v1/transport_service_tourist_packs")
public class TransportServiceTouristPackController {

    @Autowired
    private ITransportServiceTouristPackService transportServiceTouristPackService;

    @GetMapping("/")
    public ResponseEntity<List<TransportServiceTouristPackDto>> getAll() throws SQLException {
        List<TransportServiceTouristPackDto> list = transportServiceTouristPackService
                .getTransportServiceTouristPacks();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportServiceTouristPackDto> getById(@PathVariable Integer id) throws SQLException {
        TransportServiceTouristPackDto transportServiceTouristPack = transportServiceTouristPackService
                .getTransportServiceTouristPackById(id);
        return ResponseEntity.ok(transportServiceTouristPack);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody TransportServiceTouristPackDto transportServiceTouristPack)
            throws SQLException {
        transportServiceTouristPackService.createTransportServiceTouristPack(transportServiceTouristPack);
        return ResponseEntity.ok("TransportServiceTouristPack Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody TransportServiceTouristPackDto transportServiceTouristPack)
            throws SQLException {
        transportServiceTouristPackService.updateTransportServiceTouristPack(transportServiceTouristPack);
        return ResponseEntity.ok("TransportServiceTouristPack Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        transportServiceTouristPackService.deleteTransportServiceTouristPack(id);
        return ResponseEntity.ok("TransportServiceTouristPack deleted");
    }
}
