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

import cu.edu.cujae.touristpacks.core.dto.ProvinceDto;
import cu.edu.cujae.touristpacks.core.service.IProvinceService;

@RestController
@RequestMapping("/api/v1/provinces")
public class ProvinceController {

    @Autowired
    private IProvinceService provinceService;

    @GetMapping("/")
    public ResponseEntity<List<ProvinceDto>> getAll() throws SQLException {
        List<ProvinceDto> list = provinceService.getProvinces();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinceDto> getById(@PathVariable Integer id) throws SQLException {
        ProvinceDto province = provinceService.getProvinceById(id);
        return ResponseEntity.ok(province);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody ProvinceDto province) throws SQLException {
        provinceService.createProvince(province);
        return ResponseEntity.ok("Province Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody ProvinceDto province) throws SQLException {
        provinceService.updateProvince(province);
        return ResponseEntity.ok("Province Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        provinceService.deleteProvince(id);
        return ResponseEntity.ok("Province deleted");
    }
}
