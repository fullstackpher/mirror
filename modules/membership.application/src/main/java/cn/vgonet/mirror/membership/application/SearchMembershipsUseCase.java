package cn.vgonet.mirror.membership.application;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.frameworks.domain.core.Page;
import cn.vgonet.mirror.membership.domain.MembershipRepository;

@UseCase
public class SearchMembershipsUseCase {
    private final MembershipRepository membershipRepository;

    public SearchMembershipsUseCase(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public Page<MembershipListItem> execute(String q, int pageNumber, int pageSize) {
        return membershipRepository.memberships(q, pageNumber, pageSize).map(MembershipListItem::new);
    }
}
