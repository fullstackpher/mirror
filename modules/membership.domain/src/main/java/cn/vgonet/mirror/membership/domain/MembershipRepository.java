package cn.vgonet.mirror.membership.domain;

public interface MembershipRepository {

    void save(Membership membership);

    void removeAll();

    Membership membershipFor(String id);
}
