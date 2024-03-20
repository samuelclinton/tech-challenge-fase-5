package com.ecommerce.auth.domain.service;

import com.ecommerce.auth.domain.exception.UserAlreadyExistsException;
import com.ecommerce.auth.domain.exception.UserAuthenticationException;
import com.ecommerce.auth.domain.exception.UserNotFoundException;
import com.ecommerce.auth.domain.model.Role;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void verifyIfUserAlreadyExists(String email, String cpf) {
        boolean userWithEmailExists = userRepository.findByEmail(email).isPresent();
        boolean userWithCpfExists = userRepository.findByCpf(cpf).isPresent();
        if (userWithEmailExists || userWithCpfExists) {
            String invalidData = Boolean.TRUE.equals(userWithEmailExists) ? email : cpf;
            throw  new UserAlreadyExistsException(invalidData);
        }
    }

    @Override
    public User register(User newUser) {
        verifyIfUserAlreadyExists(newUser.getEmail(), newUser.getCpf());
        newUser.encryptPassword(passwordEncoder);
        return userRepository.save(newUser);
    }

    @Override
    public User authenticate(String email, String password) {
        User user = (User) loadUserByUsername(email);
        if (user.passwordIsIncorrect(passwordEncoder, password)) {
            throw new UserAuthenticationException();
        } else {
            return user;
        }
    }

    private User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User changeEmail(String userId, String newEmail, String password) {

        if (userRepository.findByEmail(newEmail).isPresent()) {
            throw new UserAlreadyExistsException(newEmail);
        }

        User user = getById(userId);

        if (user.passwordIsIncorrect(passwordEncoder, password)) {
            throw new UserAuthenticationException();
        } else {
            user.setEmail(newEmail);
            return userRepository.save(user);
        }

    }

    @Override
    public void changePassword(String userId, String newPassword, String password) {
        User user = getById(userId);
        if (user.passwordIsIncorrect(passwordEncoder, password)) {
            throw new UserAuthenticationException();
        } else {
            user.setPassword(newPassword);
            user.encryptPassword(passwordEncoder);
            userRepository.save(user);
        }
    }

    @Override
    public User changeAuthority(String userId, String newAuthority) {
        User user = getById(userId);
        user.setRole(new Role(newAuthority));
        return userRepository.save(user);
    }

    @Override
    public void delete(String userId) {
        User existingUser = getById(userId);
        userRepository.delete(existingUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
    }

}
