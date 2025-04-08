package fr.tmsconsult.myproject.security.service;

import fr.tmsconsult.myproject.security.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailsService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    public boolean isUserExists(String email);
    public User loadUserByEmail(String email);
    public User loadUserByIdentifier(String identifier);

}
