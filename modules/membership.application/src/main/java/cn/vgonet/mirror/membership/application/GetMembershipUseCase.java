package cn.vgonet.mirror.membership.application;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.membership.domain.MembershipRepository;

@UseCase
public class GetMembershipUseCase {
    private final MembershipRepository membershipRepository;
    public GetMembershipUseCase(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }
    public Object execute(String id) {
        return new GetMembershipOutput(membershipRepository.membershipFor(id));
    }
}
