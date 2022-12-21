package cu.edu.cujae.touristpacks.core.security;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cu.edu.cujae.touristpacks.core.dto.security.UserDto;
import cu.edu.cujae.touristpacks.core.service.security.IUserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserDto user = null;
        try {
            user = userService.getUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found.");
            }
        } catch (SQLException e) {
            user = null;
        }

        return UserPrincipal.create(user);
    }

}