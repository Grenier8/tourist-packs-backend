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

import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonHotelContractDto;
import cu.edu.cujae.touristpacks.core.service.IRoomPlanSeasonHotelContractService;

@RestController
@RequestMapping("/api/v1/room_plan_season_hotel_contracts")
public class RoomPlanSeasonHotelContractController {

    @Autowired
    private IRoomPlanSeasonHotelContractService roomPlanSeasonHotelContractService;

    @GetMapping("/")
    public ResponseEntity<List<RoomPlanSeasonHotelContractDto>> getAll() throws SQLException {
        List<RoomPlanSeasonHotelContractDto> list = roomPlanSeasonHotelContractService
                .getRoomPlanSeasonHotelContracts();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomPlanSeasonHotelContractDto> getById(@PathVariable Integer id) throws SQLException {
        RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract = roomPlanSeasonHotelContractService
                .getRoomPlanSeasonHotelContractById(id);
        return ResponseEntity.ok(roomPlanSeasonHotelContract);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract)
            throws SQLException {
        roomPlanSeasonHotelContractService.createRoomPlanSeasonHotelContract(roomPlanSeasonHotelContract);
        return ResponseEntity.ok("RoomPlanSeasonHotelContract Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody RoomPlanSeasonHotelContractDto roomPlanSeasonHotelContract)
            throws SQLException {
        roomPlanSeasonHotelContractService.updateRoomPlanSeasonHotelContract(roomPlanSeasonHotelContract);
        return ResponseEntity.ok("RoomPlanSeasonHotelContract Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        roomPlanSeasonHotelContractService.deleteRoomPlanSeasonHotelContract(id);
        return ResponseEntity.ok("RoomPlanSeasonHotelContract deleted");
    }
}
