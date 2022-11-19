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

import cu.edu.cujae.touristpacks.core.dto.AlimentaryPlanDto;
import cu.edu.cujae.touristpacks.core.service.IAlimentaryPlanService;

@RestController
@RequestMapping("/api/v1/alimentary_plans")
public class AlimentaryPlanController {

    @Autowired
    private IAlimentaryPlanService alimentaryPlanService;

    @GetMapping("/")
    public ResponseEntity<List<AlimentaryPlanDto>> getAll() throws SQLException {
        List<AlimentaryPlanDto> list = alimentaryPlanService.getAlimentaryPlans();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlimentaryPlanDto> getById(@PathVariable Integer id) throws SQLException {
        AlimentaryPlanDto alimentaryPlan = alimentaryPlanService.getAlimentaryPlanById(id);
        return ResponseEntity.ok(alimentaryPlan);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody AlimentaryPlanDto alimentaryPlan) throws SQLException {
        alimentaryPlanService.createAlimentaryPlan(alimentaryPlan);
        return ResponseEntity.ok("AlimentaryPlan Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody AlimentaryPlanDto alimentaryPlan) throws SQLException {
        alimentaryPlanService.updateAlimentaryPlan(alimentaryPlan);
        return ResponseEntity.ok("AlimentaryPlan Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        alimentaryPlanService.deleteAlimentaryPlan(id);
        return ResponseEntity.ok("AlimentaryPlan deleted");
    }
}
