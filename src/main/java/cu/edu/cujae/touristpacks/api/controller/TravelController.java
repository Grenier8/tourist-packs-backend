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

import cu.edu.cujae.touristpacks.core.dto.TravelDto;
import cu.edu.cujae.touristpacks.core.service.ITravelService;

@RestController
@RequestMapping("/api/v1/travels")
public class TravelController {

	@Autowired
    private ITravelService travelService;

    @GetMapping("/")
    public ResponseEntity<List<TravelDto>> getAll() throws SQLException {
        List<TravelDto> list = travelService.getTravels();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelDto> getById(@PathVariable Integer id) throws SQLException {
        TravelDto travel = travelService.getTravelById(id);
        return ResponseEntity.ok(travel);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TravelDto> getByName(@PathVariable String name) throws SQLException {
    	TravelDto travel = travelService.getTravelByName(name);
        return ResponseEntity.ok(travel);
    }
    
    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody TravelDto travel) throws SQLException {
        travelService.createTravel(travel);
        return ResponseEntity.ok("Travel Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody TravelDto travel) throws SQLException {
        travelService.updateTravel(travel);
        return ResponseEntity.ok("Travel Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        travelService.deleteTravel(id);
        return ResponseEntity.ok("Travel deleted");
    }
}
