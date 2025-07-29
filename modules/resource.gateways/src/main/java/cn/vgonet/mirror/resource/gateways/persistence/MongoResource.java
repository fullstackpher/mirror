package cn.vgonet.mirror.resource.gateways.persistence;

import cn.vgonet.mirror.resource.domain.Resource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document("resources")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MongoResource {
    private @Id String id;
    private @Field("title") String title;
    private @Field("type") String type;
    private @Field("description") String description;
    private @Field("link") String link;
    private @Field("create_at") LocalDateTime createAt;

    public MongoResource(Resource resource) {
        this.id = resource.id();
        this.title = resource.title();
        this.type = resource.type();
        this.description = resource.description();
        this.link = resource.link();
        this.createAt = resource.createAt();
    }

    public Resource asDomain() {
        return new Resource(id, title, description, type, link, createAt);
    }
}
