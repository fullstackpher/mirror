package cn.vgonet.mirror.membership.domain;

import cn.vgonet.mirror.frameworks.domain.core.Page;

public interface MembershipRepository {

    void save(Membership membership);

    void removeAll();

    Membership membershipFor(String id);

    Page<Membership> memberships(String q, int pageNumber, int pageSize);
}
