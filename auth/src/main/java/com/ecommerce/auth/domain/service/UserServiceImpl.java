package com.ecommerce.auth.domain.service;

import com.ecommerce.auth.domain.exception.UserAlreadyExistsException;
import com.ecommerce.auth.domain.exception.UserAuthenticationException;
import com.ecommerce.auth.domain.exception.UserNotFoundException;
import com.ecommerce.auth.domain.model.Authority;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private Mono<Void> verifyIfUserAlreadyExists(String email, String cpf) {
        Mono<Boolean> existingUserWithEmail = userRepository.findByEmail(email).hasElement();
        Mono<Boolean> existingUserWithCpf = userRepository.findByCpf(cpf).hasElement();
        return Mono.zip(existingUserWithEmail, existingUserWithCpf)
                .flatMap(tuple -> {
                   if (tuple.getT1() || tuple.getT2()) {
                       String invalidData = Boolean.TRUE.equals(tuple.getT1()) ? email : cpf;
                       return Mono.error(() -> new UserAlreadyExistsException(invalidData));
                   } else {
                       return Mono.empty();
                   }
                });
    }

    @Override
    public Mono<User> register(User newUser) {
        return verifyIfUserAlreadyExists(newUser.getEmail(), newUser.getCpf())
                .then(Mono.defer(() -> {
                    newUser.encryptPassword(passwordEncoder);
                    return userRepository.save(newUser);
                }));
    }

    private Mono<User> getByEmail(String email) {
        return userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(UserNotFoundException::new));
    }

    @Override
    public Mono<User> authenticate(String email, String password) {
        Mono<User> existingUser = getByEmail(email);
        return existingUser
                .flatMap(user -> {
                    if (user.passwordIsIncorrect(passwordEncoder, password)) {
                        return Mono.error(UserAuthenticationException::new);
                    } else {
                        return Mono.just(user);
                    }
                });
    }

    private Mono<User> getById(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(UserNotFoundException::new));
    }

    @Override
    public Mono<User> changeEmail(String userId, String newEmail, String password) {

        Mono<Boolean> userWithSameEmailBoolean = userRepository.findByEmail(newEmail).hasElement();
        Mono<User> existingUser = getById(userId);

        return userWithSameEmailBoolean
                .flatMap(userWithSameEmailAlreadyExists -> {
                    if (Boolean.TRUE.equals(userWithSameEmailAlreadyExists)) {
                        return Mono.error(() -> new UserAlreadyExistsException(newEmail));
                    }
                    return Mono.empty();
                })
                .then(Mono.defer(() -> existingUser.flatMap(user -> {
                    if (user.passwordIsIncorrect(passwordEncoder, password)) {
                        return Mono.error(UserAuthenticationException::new);
                    } else {
                        user.setEmail(newEmail);
                        return userRepository.save(user);
                    }
                })));
    }

    @Override
    public Mono<Void> changePassword(String userId, String newPassword, String password) {
        Mono<User> existingUser = getById(userId);
        return existingUser
                .flatMap(user -> {
                    if (user.passwordIsIncorrect(passwordEncoder, password)) {
                        return Mono.error(UserAuthenticationException::new);
                    } else {
                        user.setPassword(newPassword);
                        user.encryptPassword(passwordEncoder);
                        return userRepository.save(user).then();
                    }
                });
    }

    @Override
    public Mono<User> changeAuthority(String userId, String newAuthority) {
        Mono<User> existingUser = getById(userId);
        return existingUser
                .flatMap(user -> {
                    user.setAuthority(Authority.valueOf(newAuthority));
                    return userRepository.save(user);
                });
    }

    @Override
    public Mono<Void> delete(String userId) {
        Mono<User> existingUser = getById(userId);
        return existingUser.flatMap(userRepository::delete);
    }

}
