package cn.vgonet.mirror.membership.gateways.persistence;

import cn.vgonet.mirror.membership.domain.Membership;
import cn.vgonet.mirror.membership.domain.MembershipRepository;
import org.springframework.data.repository.Repository;

public interface MongoMembershipRepository extends Repository<MongoMembership, String>, MembershipRepository {

    @Override
    default void save(Membership membership) {
        save(new MongoMembership(membership)).toDomain();
    }

    MongoMembership save(MongoMembership membership);

    @Override
    default void removeAll() {
        deleteAll();
    }

    @Override
    default Membership membershipFor(String id) {
        return findById(id).toDomain();
    }

    MongoMembership findById(String id);

    void deleteAll();
}
