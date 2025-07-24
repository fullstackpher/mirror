package cn.vgonet.mirror.user.gateways.persistence;

import cn.vgonet.mirror.user.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MongoUser {
    @Id
    private String userId;
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String email;
    private String passwordHash;
    private LocalDateTime registerDate;
    private String referralCode;
    private Boolean isActive;
    public MongoUser(User user) {
        this.userId = user.userId();
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
            username,
            email,
            passwordHash,
            registerDate,
            referralCode,
            isActive
        );
    }
}
