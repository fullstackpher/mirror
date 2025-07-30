package cn.vgonet.mirror.membership.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpgradeMembershipInput {
    private String level;
    private Long expiresAt;
}
