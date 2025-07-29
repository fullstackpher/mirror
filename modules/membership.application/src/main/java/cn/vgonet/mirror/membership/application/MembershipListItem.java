package cn.vgonet.mirror.membership.application;

import cn.vgonet.mirror.membership.domain.Membership;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MembershipListItem {
    private final String id;
    private final String level;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiredAt;
    private final Boolean isActive;

    public MembershipListItem(Membership membership) {
        this.id = membership.id();
        this.level = membership.level();
        this.createdAt = membership.createdAt();
        this.expiredAt = membership.expiredAt();
        this.isActive = membership.isActive();
    }
}
