package com.example.sbercreditdepartment.service.userdetails;

import com.example.sbercreditdepartment.exception.AuthenticationException;
import com.example.sbercreditdepartment.model.Manager;
import com.example.sbercreditdepartment.repository.ManagerRepository;
import com.example.sbercreditdepartment.utils.constants.UserRolesConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sbercreditdepartment.repository.UserRepository;
import com.example.sbercreditdepartment.model.User;
import static com.example.sbercreditdepartment.utils.constants.UserRolesConstants.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;

    @Value("${spring.security.user.name}")
    private String adminUserName;
    @Value("${spring.security.user.password}")
    private String adminPassword;

    public CustomUserDetailsService(UserRepository userRepository, ManagerRepository managerRepository) {
        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUserName)) {
            return new CustomUserDetails(adminPassword, List.of(new SimpleGrantedAuthority("ROLE_" + ADMIN)), adminUserName, null);
        } else {
            User user = userRepository.findUserByLogin(username);
            List<GrantedAuthority> authorities = new ArrayList<>();

            if (user != null) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + UserRolesConstants.USER));

                return new CustomUserDetails(user.getPassword(), authorities, username, user.getId());

            } else {
                Manager manager = managerRepository.findManagerByLogin(username);
                if (manager != null) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + UserRolesConstants.MANAGER));

                    return new CustomUserDetails(manager.getPassword(), authorities, username, manager.getId());
                } else {
                    throw new AuthenticationException();
                }
            }
        }

    }
}
