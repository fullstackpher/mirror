package cn.vgonet.mirror.user.gateways.persistence;

import cn.vgonet.mirror.user.domain.User;
import cn.vgonet.mirror.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MongoUserRepository implements UserRepository {
    
    private final SpringDataMongoUserRepository springDataRepository;
    
    public MongoUserRepository(SpringDataMongoUserRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }
    
    @Override
    public void save(User user) {
        MongoUser mongoUser = new MongoUser(user);
        springDataRepository.save(mongoUser);
    }
    
    @Override
    public User userForId(String userId) {
        return springDataRepository.findById(userId)
                .map(MongoUser::toDomain)
                .orElse(null);
    }
    
    @Override
    public User userForUserName(String username) {
        return springDataRepository.findByUsername(username)
                .map(MongoUser::toDomain)
                .orElse(null);
    }

    @Override
    public boolean userExistsForUsername(String username) {
        return springDataRepository.existsByUsername(username);
    }
    
    @Override
    public boolean UserExistsForEmail(String email) {
        return springDataRepository.existsByEmail(email);
    }
    
    @Override
    public void removeAll() {
        springDataRepository.deleteAll();
    }
}