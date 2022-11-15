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

import cu.edu.cujae.touristpacks.core.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.core.service.IDiaryActivityService;

@RestController
@RequestMapping("/api/v1/diaryActivities")
public class DiaryActivityController {

    @Autowired
    private IDiaryActivityService diaryActivityService;

    @GetMapping("/")
    public ResponseEntity<List<DiaryActivityDto>> getAll() throws SQLException {
        List<DiaryActivityDto> list = diaryActivityService.getDiaryActivities();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaryActivityDto> getById(@PathVariable Integer id) throws SQLException {
        DiaryActivityDto diaryActivity = diaryActivityService.getDiaryActivityById(id);
        return ResponseEntity.ok(diaryActivity);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody DiaryActivityDto diaryActivity) throws SQLException {
        diaryActivityService.createDiaryActivity(diaryActivity);
        return ResponseEntity.ok("DiaryActivity Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody DiaryActivityDto diaryActivity) throws SQLException {
        diaryActivityService.updateDiaryActivity(diaryActivity);
        return ResponseEntity.ok("DiaryActivity Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        diaryActivityService.deleteDiaryActivity(id);
        return ResponseEntity.ok("DiaryActivity deleted");
    }
}
