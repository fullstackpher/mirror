package cn.vgonet.mirror.resource.gateways.acl;

import cn.vgonet.mirror.resource.domain.ResourceRepository;
import cn.vgonet.mirror.resource.domain.SyncResourceService;
import cn.vgonet.mirror.resource.gateways.persistence.JpaSourceRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MongoSyncResourceService implements SyncResourceService {

    private @Resource JpaSourceRepository sourceRepository;
    private @Resource ResourceRepository resourceRepository;

    @Override
    public void sync() {
        sourceRepository.findAll().forEach(source -> resourceRepository.save(source.toResource().asDomain()));
    }
}
