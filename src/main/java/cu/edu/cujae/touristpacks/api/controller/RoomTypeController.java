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

import cu.edu.cujae.touristpacks.core.dto.RoomTypeDto;
import cu.edu.cujae.touristpacks.core.service.IRoomTypeService;

@RestController
@RequestMapping("/api/v1/roomTypes")
public class RoomTypeController {

    @Autowired
    private IRoomTypeService roomTypeService;

    @GetMapping("/")
    public ResponseEntity<List<RoomTypeDto>> getAll() throws SQLException {
        List<RoomTypeDto> list = roomTypeService.getRoomTypes();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeDto> getById(@PathVariable Integer id) throws SQLException {
        RoomTypeDto roomType = roomTypeService.getRoomTypeById(id);
        return ResponseEntity.ok(roomType);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody RoomTypeDto roomType) throws SQLException {
        roomTypeService.createRoomType(roomType);
        return ResponseEntity.ok("RoomType Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody RoomTypeDto roomType) throws SQLException {
        roomTypeService.updateRoomType(roomType);
        return ResponseEntity.ok("RoomType Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        roomTypeService.deleteRoomType(id);
        return ResponseEntity.ok("RoomType deleted");
    }
}
