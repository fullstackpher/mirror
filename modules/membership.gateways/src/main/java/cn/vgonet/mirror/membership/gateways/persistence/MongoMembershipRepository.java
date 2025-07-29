package cn.vgonet.mirror.membership.gateways.persistence;

import cn.vgonet.mirror.frameworks.domain.core.Page;
import cn.vgonet.mirror.frameworks.persistence.spring.SpringPage;
import cn.vgonet.mirror.membership.domain.Membership;
import cn.vgonet.mirror.membership.domain.MembershipRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoMembershipRepository extends MongoRepository<MongoMembership, String>, MembershipRepository {

    @Override
    default void save(Membership membership) {
        save(new MongoMembership(membership));
    }

    @Override
    default void removeAll() {
        deleteAll();
    }

    @Override
    default Membership membershipFor(String id) {
        return findById(id)
                .map(MongoMembership::toDomain)
                .orElse(null);
    }

    @Override
    default Page<Membership> memberships(String q, int pageNumber, int pageSize) {
        return SpringPage.from(
                findByLevelContainingIgnoreCaseOrderByIdAsc(q, PageRequest.of(pageNumber, pageSize))
                .map(MongoMembership::toDomain)
        );
    }

    @Query("{'level': {$regex: ?0, $options: 'i'}}")
    PageImpl<MongoMembership> findByLevelContainingIgnoreCaseOrderByIdAsc(String q, Pageable pageable);
}
