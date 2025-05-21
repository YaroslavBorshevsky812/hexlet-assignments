package exercise.service;

import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

// BEGIN
@Service
public class CustomUserDetailsService implements UserDetailsManager {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void createUser(UserDetails user) {
        User newUser = (User) user;
        newUser.setPasswordDigest(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

    @Override
    public void updateUser(UserDetails user) {
        User existingUser = (User) user;
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String username) {
        User user = (User) loadUserByUsername(username);
        userRepository.delete(user);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // Implementation not needed for this exercise
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByEmail(username).isPresent();
    }
}
// END
