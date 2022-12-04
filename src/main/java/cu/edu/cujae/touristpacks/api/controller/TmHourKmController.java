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

import cu.edu.cujae.touristpacks.core.dto.TmHourKmDto;
import cu.edu.cujae.touristpacks.core.service.ITmHourKmService;

@RestController
@RequestMapping("/api/v1/tm_hour_kms")
public class TmHourKmController {

	@Autowired
    private ITmHourKmService tmHourKmService;

    @GetMapping("/")
    public ResponseEntity<List<TmHourKmDto>> getAll() throws SQLException {
        List<TmHourKmDto> list = tmHourKmService.getTmHourKms();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TmHourKmDto> getById(@PathVariable Integer id) throws SQLException {
        TmHourKmDto tmHourKm = tmHourKmService.getTmHourKmById(id);
        return ResponseEntity.ok(tmHourKm);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody TmHourKmDto tmHourKm) throws SQLException {
        tmHourKmService.createTmHourKm(tmHourKm);
        return ResponseEntity.ok("TmHourKm Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody TmHourKmDto tmHourKm) throws SQLException {
        tmHourKmService.updateTmHourKm(tmHourKm);
        return ResponseEntity.ok("TmHourKm Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        tmHourKmService.deleteTmHourKm(id);
        return ResponseEntity.ok("TmHourKm deleted");
    }
}
