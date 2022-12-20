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

import cu.edu.cujae.touristpacks.core.dto.TmTravelDto;
import cu.edu.cujae.touristpacks.core.service.ITmTravelService;

@RestController
@RequestMapping("/api/v1/tm_travels")
public class TmTravelController {

	@Autowired
    private ITmTravelService tmTravelService;

    @GetMapping("/")
    public ResponseEntity<List<TmTravelDto>> getAll() throws SQLException {
        List<TmTravelDto> list = tmTravelService.getTmTravels();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TmTravelDto> getById(@PathVariable Integer id) throws SQLException {
        TmTravelDto tmTravel = tmTravelService.getTmTravelById(id);
        return ResponseEntity.ok(tmTravel);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TmTravelDto> getByName(@PathVariable String name) throws SQLException {
    	TmTravelDto tmTravel = tmTravelService.getTmTravelByName(name);
        return ResponseEntity.ok(tmTravel);
    }
    
    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody TmTravelDto tmTravel) throws SQLException {
        tmTravelService.createTmTravel(tmTravel);
        return ResponseEntity.ok("TmTravel Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody TmTravelDto tmTravel) throws SQLException {
        tmTravelService.updateTmTravel(tmTravel);
        return ResponseEntity.ok("TmTravel Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        tmTravelService.deleteTmTravel(id);
        return ResponseEntity.ok("TmTravel deleted");
    }
}
