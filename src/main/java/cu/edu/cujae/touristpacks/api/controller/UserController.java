package cu.edu.cujae.touristpacks.api.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

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

import cu.edu.cujae.touristpacks.core.dto.UserDto;
import cu.edu.cujae.touristpacks.core.email.EmailSenderService;
import cu.edu.cujae.touristpacks.core.email.Mail;
import cu.edu.cujae.touristpacks.core.service.IUserService;
import freemarker.template.TemplateException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private EmailSenderService emailService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUsers() throws SQLException {
        List<UserDto> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) throws SQLException {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody UserDto user) throws SQLException {
        userService.createUser(user);
        sendMailToUserWithCredentials(user.getName(), user.getEmail());
        return ResponseEntity.ok("User Created");
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody UserDto user) throws SQLException {
        userService.updateUser(user);
        return ResponseEntity.ok("User Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

    private void sendMailToUserWithCredentials(String fullName, String email) {
        Mail mail = new Mail();
        mail.setMailTo(email);
        mail.setSubject("Registro de usuario");
        mail.setTemplate("user-registration-template.ftl");

        Map<String, Object> model = new HashMap<>();
        model.put("name", fullName);
        mail.setProps(model);

        try {
            emailService.sendEmail(mail);
        } catch (MessagingException | IOException | TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
