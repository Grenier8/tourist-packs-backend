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

import cu.edu.cujae.touristpacks.core.dto.SeasonDto;
import cu.edu.cujae.touristpacks.core.service.ISeasonService;

@RestController
@RequestMapping("/api/v1/seasons")
public class SeasonController {

    @Autowired
    private ISeasonService seasonService;

    @GetMapping("/")
    public ResponseEntity<List<SeasonDto>> getAll() throws SQLException {
        List<SeasonDto> list = seasonService.getSeasons();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonDto> getById(@PathVariable Integer id) throws SQLException {
        SeasonDto season = seasonService.getSeasonById(id);
        return ResponseEntity.ok(season);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SeasonDto> getByName(@PathVariable String name) throws SQLException {
        SeasonDto season = seasonService.getSeasonByName(name);
        return ResponseEntity.ok(season);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody SeasonDto season) throws SQLException {
        seasonService.createSeason(season);
        return ResponseEntity.ok("Season Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody SeasonDto season) throws SQLException {
        seasonService.updateSeason(season);
        return ResponseEntity.ok("Season Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        seasonService.deleteSeason(id);
        return ResponseEntity.ok("Season deleted");
    }
}
