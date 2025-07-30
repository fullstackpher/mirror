package cn.vgonet.mirror.usecase.membership;

import cn.vgonet.mirror.membership.domain.Membership;
import cn.vgonet.mirror.membership.domain.MembershipRepository;
import cn.vgonet.mirror.test.IntegrationTest;
import cn.vgonet.mirror.test.TestResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UpgradeMembershipTest extends IntegrationTest {
    private @Resource MembershipRepository membershipRepository;

    @BeforeEach
    void setUp() {
        Membership membership = new Membership("1001", "普通会员",
                LocalDateTime.parse("2022-01-01T00:00:00"),
                LocalDateTime.parse("2022-01-01T00:00:00"), true);
        membershipRepository.save(membership);
    }

    @Test
    void should_be_able_to_upgrade_membership() {
        TestResponse response = put("/memberships/1001", upgradeInfo());
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.<String>value("$.errcode")).isEqualTo("0");
        assertThat(response.<String>value("$.errmsg")).isEqualTo("");
        Membership membership = membershipRepository.membershipFor("1001");
        assertThat(membership.level()).isEqualTo("年会员");
        assertThat(membership.createdAt()).isEqualTo(LocalDateTime.parse("2022-01-01T00:00:00"));
        assertThat(membership.expiredAt()).isEqualTo(LocalDateTime.parse("2023-01-01T00:00:00"));
        assertThat(membership.isActive()).isTrue();
    }

    private Map<String, Object> upgradeInfo() {
        return Map.of(
                "level", "年会员",
                "expiresAt", 1
        );
    }

    @AfterEach
    void tearDown() {
        membershipRepository.removeAll();
    }
}
