package cn.vgonet.mirror.membership.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreateMembershipInput {
    private String id;
    private String level;
    private LocalDateTime createAt;
    private Boolean isActive;
}
