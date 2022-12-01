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

import cu.edu.cujae.touristpacks.core.dto.MayorDto;
import cu.edu.cujae.touristpacks.core.service.IMayorService;

@RestController
@RequestMapping("/api/v1/dtb_tables")
public class MayorController {

    @Autowired
    private IMayorService minorService;

    @GetMapping("/")
    public ResponseEntity<List<MayorDto>> getAll() throws SQLException {
        List<MayorDto> list = minorService.getMayors();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MayorDto> getById(@PathVariable Integer id) throws SQLException {
        MayorDto minor = minorService.getMayorById(id);
        return ResponseEntity.ok(minor);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<MayorDto> getByName(@PathVariable String name) throws SQLException {
        MayorDto minor = minorService.getMayorByName(name);
        return ResponseEntity.ok(minor);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody MayorDto minor) throws SQLException {
        minorService.createMayor(minor);
        return ResponseEntity.ok("Mayor Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody MayorDto minor) throws SQLException {
        minorService.updateMayor(minor);
        return ResponseEntity.ok("Mayor Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        minorService.deleteMayor(id);
        return ResponseEntity.ok("Mayor deleted");
    }
}
