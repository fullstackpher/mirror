package cn.vgonet.mirror.resource.gateways.ohs;

import cn.vgonet.mirror.resource.application.SyncResourceUseCase;
import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    private @Resource SyncResourceUseCase syncResourceUseCase;

    @GetMapping("/sync")
    public Object sync() {
        return ImmutableMap.of("id", syncResourceUseCase.execute());
    }
}
