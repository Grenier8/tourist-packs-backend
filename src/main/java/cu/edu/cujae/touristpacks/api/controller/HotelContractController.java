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

import cu.edu.cujae.touristpacks.core.dto.HotelContractDto;
import cu.edu.cujae.touristpacks.core.service.IHotelContractService;

@RestController
@RequestMapping("/api/v1/hotel_contracts")
public class HotelContractController {

    @Autowired
    private IHotelContractService hotelContractService;

    @GetMapping("/")
    public ResponseEntity<List<HotelContractDto>> getAll() throws SQLException {
        List<HotelContractDto> list = hotelContractService.getHotelContracts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelContractDto> getById(@PathVariable Integer id) throws SQLException {
        HotelContractDto hotelContract = hotelContractService.getHotelContractById(id);
        return ResponseEntity.ok(hotelContract);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody HotelContractDto hotelContract) throws SQLException {
        hotelContractService.createHotelContract(hotelContract);
        return ResponseEntity.ok("HotelContract Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody HotelContractDto hotelContract) throws SQLException {
        hotelContractService.updateHotelContract(hotelContract);
        return ResponseEntity.ok("HotelContract Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        hotelContractService.deleteHotelContract(id);
        return ResponseEntity.ok("HotelContract deleted");
    }
}
