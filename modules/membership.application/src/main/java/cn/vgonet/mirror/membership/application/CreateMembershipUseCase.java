package cn.vgonet.mirror.membership.application;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.membership.domain.Membership;
import cn.vgonet.mirror.membership.domain.MembershipRepository;

@UseCase
public class CreateMembershipUseCase {
    private final MembershipRepository membershipRepository;

    public CreateMembershipUseCase(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public Object execute(CreateMembershipInput input) {
        Membership membership = new Membership(input.getId(), input.getLevel(),
                input.getCreateAt(), input.getIsActive());
        membershipRepository.save(membership);
        return membership.id();
    }
}
