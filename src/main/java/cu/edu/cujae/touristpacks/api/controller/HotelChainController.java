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

import cu.edu.cujae.touristpacks.core.dto.HotelChainDto;
import cu.edu.cujae.touristpacks.core.service.IHotelChainService;

@RestController
@RequestMapping("/api/v1/hotel_chains")
public class HotelChainController {

    @Autowired
    private IHotelChainService hotelChainService;

    @GetMapping("/")
    public ResponseEntity<List<HotelChainDto>> getAll() throws SQLException {
        List<HotelChainDto> list = hotelChainService.getHotelChains();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelChainDto> getById(@PathVariable Integer id) throws SQLException {
        HotelChainDto hotelChain = hotelChainService.getHotelChainById(id);
        return ResponseEntity.ok(hotelChain);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<HotelChainDto> getByName(@PathVariable String name) throws SQLException {
        HotelChainDto hotelChain = hotelChainService.getHotelChainByName(name);
        return ResponseEntity.ok(hotelChain);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody HotelChainDto hotelChain) throws SQLException {
        hotelChainService.createHotelChain(hotelChain);
        return ResponseEntity.ok("Cadena hotelera insertada");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody HotelChainDto hotelChain) throws SQLException {
        hotelChainService.updateHotelChain(hotelChain);
        return ResponseEntity.ok("Cadena hotelera actualizada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        hotelChainService.deleteHotelChain(id);
        return ResponseEntity.ok("Cadena hotelera eliminada");
    }

}
