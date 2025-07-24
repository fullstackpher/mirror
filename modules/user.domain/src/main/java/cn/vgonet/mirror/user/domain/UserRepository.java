package cn.vgonet.mirror.user.domain;

public interface UserRepository {
    
    void save(User user);
    
    User userForId(String userId);
    
    User userForUserName(String username);

    boolean userExistsForUsername(String username);
    
    boolean UserExistsForEmail(String email);
    
    void removeAll();
}
