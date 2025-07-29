package cn.vgonet.mirror.user.gateways.persistence;

import cn.vgonet.mirror.user.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MongoUser {
    @Id
    private String userId;
    @Field("membership_id")
    private String membershipId;
    @Indexed(unique = true)
    @Field("username")
    private String username;
    @Indexed(unique = true)
    @Field("email")
    private String email;
    @Field("password_hash")
    private String passwordHash;
    @Field("register_date")
    private LocalDateTime registerDate;
    @Field("referral_code")
    private String referralCode;
    @Field("is_active")
    private Boolean isActive;

    public MongoUser(User user) {
        this.userId = user.userId();
        this.membershipId = user.membershipId();
        this.username = user.username();
        this.email = user.email();
        this.passwordHash = user.passwordHash();
        this.registerDate = user.registerDate();
        this.referralCode = user.referralCode();
        this.isActive = user.isActive();
    }

    public User toDomain() {
        return new User(
                userId,
                membershipId,
                username,
                email,
                passwordHash,
                registerDate,
                referralCode,
                isActive
        );
    }
}
