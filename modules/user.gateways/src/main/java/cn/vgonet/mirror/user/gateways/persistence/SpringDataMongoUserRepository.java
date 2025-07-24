package cn.vgonet.mirror.user.gateways.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataMongoUserRepository extends MongoRepository<MongoUser, String> {
    
    Optional<MongoUser> findByUsername(String username);
    
    Optional<MongoUser> findByEmail(String email);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}