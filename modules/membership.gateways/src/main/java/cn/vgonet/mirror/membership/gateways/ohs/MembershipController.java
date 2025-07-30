package cn.vgonet.mirror.membership.gateways.ohs;

import cn.vgonet.mirror.membership.application.*;
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
    private @Resource UpgradeMembershipUseCase upgradeMembershipUseCase;

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

    @PutMapping("/{id}")
    public void upgrade(@RequestBody UpgradeMembershipInput input, @PathVariable String id) {
        upgradeMembershipUseCase.execute(input, id);
    }
}
