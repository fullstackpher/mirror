package cn.vgonet.mirror.membership.domain;

public class ValidMembership {
    private final MembershipRepository membershipRepository;

    public ValidMembership(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public Membership validForId(String id) {
        Membership membership = membershipRepository.membershipFor(id);
        if (membership == null) {
            throw new IllegalArgumentException("Membership not found");
        }
        return membership;
    }
}
