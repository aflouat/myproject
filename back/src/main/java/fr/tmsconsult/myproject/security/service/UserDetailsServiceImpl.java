package fr.tmsconsult.myproject.security.service;

import fr.tmsconsult.myproject.security.model.User;
import fr.tmsconsult.myproject.security.model.UserPrincipal;
import fr.tmsconsult.myproject.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements IUserDetailsService {
    private final UserRepository userRepo;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.loadUserByIdentifier(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return UserPrincipal.builder().id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())

                .build();
    }



    public boolean isUserExists(String email) {
        return userRepo.findByEmail(email) != null;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }


    @Override
    public User loadUserByIdentifier(String identifier) {
        User user = userRepository.findByEmail(identifier);

        // Si aucun utilisateur trouvé par email, rechercher par username
        if (user == null) {
            user = userRepository.findByUsername(identifier);
        }
        return user;
    }
}
