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

import cu.edu.cujae.touristpacks.core.dto.DiaryActivityTouristPackDto;
import cu.edu.cujae.touristpacks.core.service.IDiaryActivityTouristPackService;

@RestController
@RequestMapping("/api/v1/diary_activity_tourist_packs")
public class DiaryActivityTouristPackController {

    @Autowired
    private IDiaryActivityTouristPackService diaryActivityTouristPackService;

    @GetMapping("/")
    public ResponseEntity<List<DiaryActivityTouristPackDto>> getAll() throws SQLException {
        List<DiaryActivityTouristPackDto> list = diaryActivityTouristPackService.getDiaryActivityTouristPacks();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaryActivityTouristPackDto> getById(@PathVariable Integer id) throws SQLException {
        DiaryActivityTouristPackDto diaryActivityTouristPack = diaryActivityTouristPackService
                .getDiaryActivityTouristPackById(id);
        return ResponseEntity.ok(diaryActivityTouristPack);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody DiaryActivityTouristPackDto diaryActivityTouristPack)
            throws SQLException {
        diaryActivityTouristPackService.createDiaryActivityTouristPack(diaryActivityTouristPack);
        return ResponseEntity.ok("DiaryActivityTouristPack Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody DiaryActivityTouristPackDto diaryActivityTouristPack)
            throws SQLException {
        // TODO code for update
        return ResponseEntity.ok("DiaryActivityTouristPack Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        diaryActivityTouristPackService.deleteDiaryActivityTouristPack(id);
        return ResponseEntity.ok("DiaryActivityTouristPack deleted");
    }
}
