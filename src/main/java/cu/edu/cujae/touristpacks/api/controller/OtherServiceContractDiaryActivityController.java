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

import cu.edu.cujae.touristpacks.core.dto.OtherServiceContractDiaryActivityDto;
import cu.edu.cujae.touristpacks.core.service.IOtherServiceContractDiaryActivityService;

@RestController
@RequestMapping("/api/v1/otherServiceContractDiaryActivities")
public class OtherServiceContractDiaryActivityController {

    @Autowired
    private IOtherServiceContractDiaryActivityService otherServiceContractDiaryActivityService;

    @GetMapping("/")
    public ResponseEntity<List<OtherServiceContractDiaryActivityDto>> getAll() throws SQLException {
        List<OtherServiceContractDiaryActivityDto> list = otherServiceContractDiaryActivityService
                .getOtherServiceContractDiaryActivities();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OtherServiceContractDiaryActivityDto> getById(@PathVariable Integer id) throws SQLException {
        OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity = otherServiceContractDiaryActivityService
                .getOtherServiceContractDiaryActivityById(id);
        return ResponseEntity.ok(otherServiceContractDiaryActivity);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(
            @RequestBody OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity) throws SQLException {
        otherServiceContractDiaryActivityService
                .createOtherServiceContractDiaryActivity(otherServiceContractDiaryActivity);
        return ResponseEntity.ok("OtherServiceContractDiaryActivity Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(
            @RequestBody OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity) throws SQLException {
        otherServiceContractDiaryActivityService
                .updateOtherServiceContractDiaryActivity(otherServiceContractDiaryActivity);
        return ResponseEntity.ok("OtherServiceContractDiaryActivity Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        otherServiceContractDiaryActivityService.deleteOtherServiceContractDiaryActivity(id);
        return ResponseEntity.ok("OtherServiceContractDiaryActivity deleted");
    }
}
