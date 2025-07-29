package cn.vgonet.mirror.membership.gateways.persistence;

import cn.vgonet.mirror.membership.domain.Membership;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Document(collation = "memberships")
public class MongoMembership {
    private @Id String id;
    private @Field("level") String level;
    private @Field("create_at") LocalDateTime createAt;
    private @Field("is_active") Boolean isActive;

    public MongoMembership(Membership membership) {
        this.id = membership.id();
        this.level = membership.level();
        this.createAt = membership.createAt();
        this.isActive = membership.isActive();
    }

    public Membership toDomain() {
        return new Membership(id, level, createAt, isActive);
    }
}
