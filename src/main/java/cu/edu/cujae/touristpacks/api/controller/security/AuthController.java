package cu.edu.cujae.touristpacks.api.controller.security;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.touristpacks.core.dto.security.LoginRequestDto;
import cu.edu.cujae.touristpacks.core.dto.security.UserAuthenticatedDto;
import cu.edu.cujae.touristpacks.core.dto.security.UserDto;
import cu.edu.cujae.touristpacks.core.security.TokenProvider;
import cu.edu.cujae.touristpacks.core.service.security.IUserService;
import io.swagger.annotations.Api;

@RestController
@Api(tags = "Authentication endpoint controller")
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IUserService userService;

	@Autowired
	private TokenProvider tokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequestDto loginRequestDto) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),
							loginRequestDto.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String token = tokenProvider.createToken(authentication);

			UserDto user = userService.getUserByUsername(loginRequestDto.getUsername());
			UserAuthenticatedDto userAuth = new UserAuthenticatedDto(user.getIdUser(), user.getUsername(), null,
					user.getName(), user.getRole(),
					user.getEmail(), token);

			return ResponseEntity.ok(userAuth);
		} catch (BadCredentialsException | SQLException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
		}
	}

}
