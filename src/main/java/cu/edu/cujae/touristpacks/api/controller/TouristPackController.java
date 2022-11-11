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

import cu.edu.cujae.touristpacks.core.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.core.service.ITouristPackService;

@RestController
@RequestMapping("/api/v1/tourist_packs")
public class TouristPackController {

    @Autowired
    private ITouristPackService touristPackService;

    @GetMapping("/")
    public ResponseEntity<List<TouristPackDto>> getAll() throws SQLException {
        List<TouristPackDto> list = touristPackService.getTouristPacks();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TouristPackDto> getById(@PathVariable Integer id) throws SQLException {
        TouristPackDto touristPack = touristPackService.getTouristPackById(id);
        return ResponseEntity.ok(touristPack);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody TouristPackDto touristPack) throws SQLException {
        touristPackService.createTouristPack(touristPack);
        return ResponseEntity.ok("TouristPack Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody TouristPackDto touristPack) throws SQLException {
        // TODO code for update
        return ResponseEntity.ok("TouristPack Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        touristPackService.deleteTouristPack(id);
        return ResponseEntity.ok("TouristPack deleted");
    }
}
