package cn.vgonet.mirror.user.domain;

import org.apache.commons.lang3.StringUtils;

public class UserValid {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserValid(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void check(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("密码不能为空");
        }
    }

    public User validForUserName(String username, String password) {
        User user = userRepository.userForUserName(username);
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        if (!user.isActive()) {
            throw new IllegalArgumentException("账户已被禁用");
        }
        if (!userService.verifyPassword(password, user.passwordHash())) {
            throw new IllegalArgumentException("密码错误");
        }
        return user;
    }

    public User validForUserId(String id) {
        User user = userRepository.userForId(id);
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        return user;
    }
}
