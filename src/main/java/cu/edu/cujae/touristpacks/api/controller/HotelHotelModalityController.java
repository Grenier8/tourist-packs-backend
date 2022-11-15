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

import cu.edu.cujae.touristpacks.core.dto.HotelHotelModalityDto;
import cu.edu.cujae.touristpacks.core.service.IHotelHotelModalityService;

@RestController
@RequestMapping("/api/v1/hotel_hotel_modalities")
public class HotelHotelModalityController {

    @Autowired
    private IHotelHotelModalityService hotelHotelModalityService;

    @GetMapping("/")
    public ResponseEntity<List<HotelHotelModalityDto>> getAll() throws SQLException {
        List<HotelHotelModalityDto> list = hotelHotelModalityService.getHotelHotelModalities();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelHotelModalityDto> getById(@PathVariable Integer id) throws SQLException {
        HotelHotelModalityDto hotelHotelModality = hotelHotelModalityService.getHotelHotelModalityById(id);
        return ResponseEntity.ok(hotelHotelModality);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody HotelHotelModalityDto hotelHotelModality) throws SQLException {
        hotelHotelModalityService.createHotelHotelModality(hotelHotelModality);
        return ResponseEntity.ok("HotelHotelModality Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody HotelHotelModalityDto hotelHotelModality) throws SQLException {
        hotelHotelModalityService.updateHotelHotelModality(hotelHotelModality);
        return ResponseEntity.ok("HotelHotelModality Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        hotelHotelModalityService.deleteHotelHotelModality(id);
        return ResponseEntity.ok("HotelHotelModality deleted");
    }
}
