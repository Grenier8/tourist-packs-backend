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

import cu.edu.cujae.touristpacks.core.dto.RoleDto;
import cu.edu.cujae.touristpacks.core.service.IRoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/")
    public ResponseEntity<List<RoleDto>> getAll() throws SQLException {
        List<RoleDto> list = roleService.getRoles();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getById(@PathVariable Integer id) throws SQLException {
        RoleDto role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RoleDto> getByName(@PathVariable String name) throws SQLException {
        RoleDto role = roleService.getRoleByName(name);
        return ResponseEntity.ok(role);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody RoleDto role) throws SQLException {
        roleService.createRole(role);
        return ResponseEntity.ok("Role Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody RoleDto role) throws SQLException {
        roleService.updateRole(role);
        return ResponseEntity.ok("Role Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted");
    }
}
