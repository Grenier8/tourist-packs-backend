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

import cu.edu.cujae.touristpacks.core.dto.HotelModalityDto;
import cu.edu.cujae.touristpacks.core.service.IHotelModalityService;

@RestController
@RequestMapping("/api/v1/hotel_modalities")
public class HotelModalityController {

    @Autowired
    private IHotelModalityService hotelModalityService;

    @GetMapping("/")
    public ResponseEntity<List<HotelModalityDto>> getAll() throws SQLException {
        List<HotelModalityDto> list = hotelModalityService.getHotelModalities();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelModalityDto> getById(@PathVariable Integer id) throws SQLException {
        HotelModalityDto hotelModality = hotelModalityService.getHotelModalityById(id);
        return ResponseEntity.ok(hotelModality);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody HotelModalityDto hotelModality) throws SQLException {
        hotelModalityService.createHotelModality(hotelModality);
        return ResponseEntity.ok("HotelModality Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody HotelModalityDto hotelModality) throws SQLException {
        // TODO code for update
        return ResponseEntity.ok("HotelModality Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        hotelModalityService.deleteHotelModality(id);
        return ResponseEntity.ok("HotelModality deleted");
    }
}
