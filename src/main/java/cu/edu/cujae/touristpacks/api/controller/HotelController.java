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

import cu.edu.cujae.touristpacks.core.dto.HotelDto;
import cu.edu.cujae.touristpacks.core.service.IHotelService;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @GetMapping("/")
    public ResponseEntity<List<HotelDto>> getAll() throws SQLException {
        List<HotelDto> list = hotelService.getHotels();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getById(@PathVariable Integer id) throws SQLException {
        HotelDto hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<HotelDto> getByName(@PathVariable String name) throws SQLException {
        HotelDto hotel = hotelService.getHotelByName(name);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/modality/{modality}")
    public ResponseEntity<List<HotelDto>> getAllByModality(@PathVariable String modality) throws SQLException {
        List<HotelDto> list = hotelService.getHotelsByModality(modality);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody HotelDto hotel) throws SQLException {
        hotelService.createHotel(hotel);
        return ResponseEntity.ok("Hotel Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody HotelDto hotel) throws SQLException {
        hotelService.updateHotel(hotel);
        return ResponseEntity.ok("Hotel Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("Hotel deleted");
    }
}
