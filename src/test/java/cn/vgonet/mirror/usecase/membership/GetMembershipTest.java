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

import static org.assertj.core.api.Assertions.assertThat;

public class GetMembershipTest extends IntegrationTest {
    private @Resource MembershipRepository membershipRepository;
    @BeforeEach
    void setUp() {
        Membership membership = new Membership("1001", "永久会员",
                LocalDateTime.parse("2022-01-01T00:00:00"), true);
        membershipRepository.save(membership);
    }

    @Test
    void should_be_able_to_view_membership() {
        TestResponse response = get("/memberships/1001");
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.<String>value("$.errcode")).isEqualTo("0");
        assertThat(response.<String>value("$.errmsg")).isEqualTo("");
        assertThat(response.<String>value("$.data.id")).isEqualTo("1001");
        assertThat(response.<String>value("$.data.level")).isEqualTo("永久会员");
        assertThat(response.<String>value("$.data.createAt")).isEqualTo("2022-01-01T00:00:00");
        assertThat(response.<Boolean>value("$.data.isActive")).isTrue();
    }

    @AfterEach
    void tearDown() {
        membershipRepository.removeAll();
    }
}
