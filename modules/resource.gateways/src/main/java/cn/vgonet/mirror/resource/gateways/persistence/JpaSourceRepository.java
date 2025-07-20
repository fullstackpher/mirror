package cn.vgonet.mirror.resource.gateways.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaSourceRepository extends CrudRepository<JpaSource, String> {

}

