package cn.vgonet.mirror.membership.application;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.membership.domain.Membership;
import cn.vgonet.mirror.membership.domain.MembershipRepository;
import cn.vgonet.mirror.membership.domain.ValidMembership;

@UseCase
public class GetMembershipUseCase {
    private final MembershipRepository membershipRepository;
    public GetMembershipUseCase(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }
    public Object execute(String id) {
        Membership membership = new ValidMembership(membershipRepository).validForId(id);
        return new GetMembershipOutput(membership);
    }
}
