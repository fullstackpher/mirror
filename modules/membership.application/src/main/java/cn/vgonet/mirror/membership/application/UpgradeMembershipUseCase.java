package cn.vgonet.mirror.membership.application;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.membership.domain.Membership;
import cn.vgonet.mirror.membership.domain.MembershipRepository;

@UseCase
public class UpgradeMembershipUseCase {
    private final MembershipRepository membershipRepository;

    public UpgradeMembershipUseCase(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public void execute(UpgradeMembershipInput input, String id) {
        // TODO 支付逻辑
        Membership membership = membershipRepository.membershipFor(id);
        membership.upgrade(input.getLevel(), input.getExpiresAt());
        membershipRepository.save(membership);
    }
}
