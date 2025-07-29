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
@Document(collection = "memberships")
public class MongoMembership {
    private @Id String id;
    private @Field("level") String level;
    private @Field("created_at") LocalDateTime createdAt;
    private @Field("expired_at") LocalDateTime expiredAt;
    private @Field("is_active") Boolean isActive;

    public MongoMembership(Membership membership) {
        this.id = membership.id();
        this.level = membership.level();
        this.createdAt = membership.createdAt();
        this.expiredAt = membership.expiredAt();
        this.isActive = membership.isActive();
    }

    public Membership toDomain() {
        return new Membership(id, level, createdAt, expiredAt, isActive);
    }
}
