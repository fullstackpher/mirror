package cn.vgonet.mirror.user.gateways.persistence;

import cn.vgonet.mirror.user.domain.User;
import cn.vgonet.mirror.user.domain.UserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, String>, UserRepository {
    @Override
    default void save(User user) {
        save(new MongoUser(user)).toDomain();
    }


    @Override
    default User userForId(String userId) {
        return findById(userId)
                .map(MongoUser::toDomain)
                .orElse(null);
    }
    
    @Override
    default User userForUserName(String username) {
        return findByUsername(username)
                .map(MongoUser::toDomain)
                .orElse(null);
    }

    @Override
    default boolean userExistsForUsername(String username) {
        return existsByUsername(username);
    }
    
    @Override
    default boolean UserExistsForEmail(String email) {
        return existsByEmail(email);
    }
    
    @Override
    default void removeAll() {
        deleteAll();
    }

    Optional<MongoUser> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}