package cn.vgonet.mirror.resource.gateways.persistence;

import cn.vgonet.mirror.resource.domain.Resource;
import cn.vgonet.mirror.resource.domain.ResourceRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoResourceRepository extends MongoRepository<MongoResource, String>, ResourceRepository {
    @Override
    default void removeAll() {
        deleteAll();
    }

    @Override
    default void save(Resource resource) {
        save(new MongoResource(resource));
    }

    @Override
    default Resource resourceForFirst() {
        return findFirstByOrderByCreatedAtAsc().map(MongoResource::toDomain).orElse(null);
    }

    @Override
    default Resource resourceForId(String id) {
        return findById(id).map(MongoResource::toDomain).orElse( null);
    }

    Optional<MongoResource> findFirstByOrderByCreatedAtAsc();
}
