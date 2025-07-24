package cn.vgonet.mirror.user.domain;

public interface UserService {
    /**
     * 创建用户.
     */
    User createUser(String username, String email, String password, String referralCode);
    /**
     * 验证密码.
     */
    boolean verifyPassword(String rawPassword, String hashedPassword);
    /**
     * 生成JWT令牌.
     */
    String generateToken(User user);
    /**
     * 验证JWT令牌.
     */
    boolean validateToken(String token);
    /**
     * 从令牌中提取用户ID.
     */
    String extractUserIdFromToken(String token);
    /**
     * 发布用户注册事件.
     */
    void publishUserRegisteredEvent(User user);
}
