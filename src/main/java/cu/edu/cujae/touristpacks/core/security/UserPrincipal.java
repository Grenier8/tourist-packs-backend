package cu.edu.cujae.touristpacks.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cu.edu.cujae.touristpacks.core.dto.security.UserDto;

public class UserPrincipal implements UserDetails {
    private int id;
    private String email;
    private String password;
    private boolean active;
    private String username;
    private String fullName;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(int id, String email, String password, boolean active,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.active = active;
        this.authorities = authorities;
    }

    public static UserPrincipal create(UserDto user) {
        List<GrantedAuthority> authorities;
        try {
            String roleName = user.getRole().getRoleName();
            // Collection<String> roleNames = user.getRoles().stream().map(role ->
            // role.getRoleName())
            // .collect(Collectors.toList());
            authorities = AuthorityUtils.createAuthorityList(roleName);
        } catch (Exception e) {
            authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        }
        return new UserPrincipal(
                user.getIdUser(),
                user.getEmail(),
                user.getPassword(),
                true,
                authorities);
    }

    public static UserPrincipal create(UserDto user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return userPrincipal;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getFullName() {
        return fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
