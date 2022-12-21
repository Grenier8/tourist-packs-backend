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

import cu.edu.cujae.touristpacks.core.dto.TmKmDto;
import cu.edu.cujae.touristpacks.core.service.ITmKmService;

@RestController
@RequestMapping("/api/v1/tm_kms")
public class TmKmController {

	@Autowired
    private ITmKmService tmKmService;

    @GetMapping("/")
    public ResponseEntity<List<TmKmDto>> getAll() throws SQLException {
        List<TmKmDto> list = tmKmService.getTmKms();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TmKmDto> getById(@PathVariable Integer id) throws SQLException {
        TmKmDto tmKm = tmKmService.getTmKmById(id);
        return ResponseEntity.ok(tmKm);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<TmKmDto> getByName(@PathVariable String name) throws SQLException {
    	TmKmDto tmKm = tmKmService.getTmKmByName(name);
        return ResponseEntity.ok(tmKm);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody TmKmDto tmKm) throws SQLException {
        tmKmService.createTmKm(tmKm);
        return ResponseEntity.ok("tmKm Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody TmKmDto tmKm) throws SQLException {
        tmKmService.updateTmKm(tmKm);
        return ResponseEntity.ok("tmKm Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        tmKmService.deleteTmKm(id);
        return ResponseEntity.ok("tmKm deleted");
    }
}
