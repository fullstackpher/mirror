package cn.vgonet.mirror.membership.application;

import cn.vgonet.mirror.membership.domain.Membership;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetMembershipOutput {
    private final String id;
    private final String level;
    private final LocalDateTime createAt;
    private final Boolean isActive;

    public GetMembershipOutput(Membership membership) {
        this.id = membership.id();
        this.level = membership.level();
        this.createAt = membership.createAt();
        this.isActive = membership.isActive();
    }
}
