package cn.vgonet.mirror.resource.application;

import cn.vgonet.mirror.frameworks.application.core.UseCase;
import cn.vgonet.mirror.resource.domain.ResourceRepository;
import cn.vgonet.mirror.resource.domain.SyncResourceService;

@UseCase
public class SyncResourceUseCase {

    private final ResourceRepository resourceRepository;
    private final SyncResourceService syncResourceService;

    public SyncResourceUseCase(ResourceRepository resourceRepository, SyncResourceService syncResourceService) {
        this.resourceRepository = resourceRepository;
        this.syncResourceService = syncResourceService;
    }

    public Object execute() {
        syncResourceService.sync();
        return resourceRepository.resourceForFirst().id();
    }
}
