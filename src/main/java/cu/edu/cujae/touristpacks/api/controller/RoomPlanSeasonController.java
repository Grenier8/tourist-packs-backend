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

import cu.edu.cujae.touristpacks.core.dto.RoomPlanSeasonDto;
import cu.edu.cujae.touristpacks.core.service.IRoomPlanSeasonService;

@RestController
@RequestMapping("/api/v1/roomPlanSeasons")
public class RoomPlanSeasonController {

    @Autowired
    private IRoomPlanSeasonService roomPlanSeasonService;

    @GetMapping("/")
    public ResponseEntity<List<RoomPlanSeasonDto>> getAll() throws SQLException {
        List<RoomPlanSeasonDto> list = roomPlanSeasonService.getRoomPlanSeasons();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomPlanSeasonDto> getById(@PathVariable Integer id) throws SQLException {
        RoomPlanSeasonDto roomPlanSeason = roomPlanSeasonService.getRoomPlanSeasonById(id);
        return ResponseEntity.ok(roomPlanSeason);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody RoomPlanSeasonDto roomPlanSeason) throws SQLException {
        roomPlanSeasonService.createRoomPlanSeason(roomPlanSeason);
        return ResponseEntity.ok("RoomPlanSeason Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody RoomPlanSeasonDto roomPlanSeason) throws SQLException {
        roomPlanSeasonService.updateRoomPlanSeason(roomPlanSeason);
        return ResponseEntity.ok("RoomPlanSeason Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        roomPlanSeasonService.deleteRoomPlanSeason(id);
        return ResponseEntity.ok("RoomPlanSeason deleted");
    }
}
