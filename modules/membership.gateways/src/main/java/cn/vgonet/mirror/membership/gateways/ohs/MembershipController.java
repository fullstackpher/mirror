package cn.vgonet.mirror.membership.gateways.ohs;

import cn.vgonet.mirror.membership.application.CreateMembershipInput;
import cn.vgonet.mirror.membership.application.CreateMembershipUseCase;
import cn.vgonet.mirror.membership.application.GetMembershipUseCase;
import cn.vgonet.mirror.membership.application.SearchMembershipsUseCase;
import com.google.common.collect.ImmutableMap;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/memberships")
public class MembershipController {

    private @Resource CreateMembershipUseCase createMembershipUseCase;
    private @Resource GetMembershipUseCase getMembershipUseCase;
    private @Resource SearchMembershipsUseCase searchMembershipsUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@RequestBody CreateMembershipInput input) {
        return ImmutableMap.of(
                "id", createMembershipUseCase.execute(input)
        );
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        return getMembershipUseCase.execute(id);
    }

    @GetMapping
    public Object search(@RequestParam String q, Pageable pageable) {
        return searchMembershipsUseCase.execute(q, pageable.getPageNumber(), pageable.getPageSize());
    }
}
