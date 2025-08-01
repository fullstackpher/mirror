package cn.vgonet.mirror.user.gateways.acl;

import cn.vgonet.mirror.user.domain.User;
import cn.vgonet.mirror.user.domain.UserRepository;
import cn.vgonet.mirror.user.domain.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthUserService implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    public AuthUserService(PasswordEncoder passwordEncoder,
                           JwtTokenService jwtTokenService,
                           UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String username, String email, String password, String referralCode) {
        if (userRepository.userExistsForUsername(username)) {
            throw new IllegalArgumentException("用户名已存在");
        }

        if (userRepository.UserExistsForEmail(email)) {
            throw new IllegalArgumentException("邮箱已存在");
        }
        String userId = UUID.randomUUID().toString();
        String hashedPassword = passwordEncoder.encode(password);

        return new User(
            userId,
            username,
            email,
            hashedPassword,
            LocalDateTime.now(),
            referralCode,
            true
        );
    }

    @Override
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    @Override
    public String generateToken(User user) {
        return jwtTokenService.generateToken(user);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtTokenService.validateToken(token);
    }

    @Override
    public String extractUserIdFromToken(String token) {
        return jwtTokenService.extractUserId(token);
    }
}
