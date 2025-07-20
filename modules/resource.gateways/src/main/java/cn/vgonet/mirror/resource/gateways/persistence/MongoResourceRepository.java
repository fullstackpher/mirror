package cn.vgonet.mirror.resource.gateways.persistence;

import cn.vgonet.mirror.resource.domain.Resource;
import cn.vgonet.mirror.resource.domain.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class MongoResourceRepository implements ResourceRepository {
    private @Autowired MongoTemplate mongoTemplate;
    @Override
    public void removeAll() {
        mongoTemplate.remove(Resource.class).all();
    }

    @Override
    public void save(Resource resource) {
        mongoTemplate.save(new MongoResource(resource));
    }

    @Override
    public Resource resourceForFirst() {
        Query query = new Query()
                .with(Sort.by(Sort.Direction.ASC, "create_at")) // 假设有createTime字段
                .limit(1);
        return Objects.requireNonNull(mongoTemplate.findOne(query, MongoResource.class)).asDomain();
    }
}
