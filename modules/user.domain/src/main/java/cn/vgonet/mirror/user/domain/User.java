package cn.vgonet.mirror.user.domain;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class User {
    private final String userId;
    private String membershipId;
    private final String username;
    private final String email;
    private final String passwordHash;
    private final LocalDateTime registerDate;
    private final String referralCode;
    private final Boolean isActive;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    private static final Pattern USERNAME_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_]{3,20}$"
    );

    public static final int MIN_PASSWORD_LENGTH = 6;

    public User(String userId, String username, String email, String passwordHash,
                LocalDateTime registerDate, String referralCode, Boolean isActive) {
        verify(username, email, passwordHash);
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.registerDate = registerDate;
        this.referralCode = referralCode;
        this.isActive = isActive;
    }

    public User(String userId, String membershipId, String username, String email, String passwordHash,
                LocalDateTime registerDate, String referralCode, Boolean isActive) {
        verify(username, email, passwordHash);
        this.userId = userId;
        this.membershipId = membershipId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.registerDate = registerDate;
        this.referralCode = referralCode;
        this.isActive = isActive;
    }

    public void assignMembership(String membershipId) {
        this.membershipId = membershipId;
    }

    public String userId() {
        return userId;
    }

    public String membershipId() {
        return membershipId;
    }

    public String username() {
        return username;
    }

    public String email() {
        return email;
    }

    public String passwordHash() {
        return passwordHash;
    }

    public LocalDateTime registerDate() {
        return registerDate;
    }

    public String referralCode() {
        return referralCode;
    }

    public boolean isActive() {
        return isActive;
    }

    private void validateNotEmpty(String value, String errorMessage) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public void verify(String username, String email, String passwordHash) {
        validateNotEmpty(username, "用户名不能为空");
        validateNotEmpty(email, "邮箱不能为空");
        validateNotEmpty(passwordHash, "密码不能为空");

        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("用户名只能包含字母、数字和下划线，长度3-20位");
        }

        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }

        if (!isValidPassword(passwordHash)) {
            throw new IllegalArgumentException("密码长度不能少于6位");
        }
    }

    public boolean isValidUsername(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    public boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() >= MIN_PASSWORD_LENGTH;
    }
}
