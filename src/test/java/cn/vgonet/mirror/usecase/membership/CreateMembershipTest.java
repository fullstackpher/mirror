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

public class CreateMembershipTest extends IntegrationTest {
    private @Resource MembershipRepository membershipRepository;

    @BeforeEach
    void setUp() {
        Membership membership = new Membership("1002", "年会员", LocalDateTime.parse("2022-01-01T00:00:00"), true);
        membershipRepository.save(membership);
    }

    @Test
    void should_be_able_to_create_membership() {
        TestResponse response = post("/memberships", generateMembershipJson());
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.<String>value("$.errcode")).isEqualTo("0");
        assertThat(response.<String>value("$.errmsg")).isEqualTo("");
        assertThat(response.<String>value("$.data.id")).isEqualTo("1002");
        Membership membership = membershipRepository.membershipFor("1002");
        assertThat(membership.level()).isEqualTo("月会员");
        assertThat(membership.createdAt()).isEqualTo(LocalDateTime.parse("2022-01-01T00:00:00"));
        assertThat(membership.isActive()).isTrue();
    }

    private Map<String, Object> generateMembershipJson() {
        return Map.of(
                "id", "1002",
                "level", "月会员",
                "createdAt", "2022-01-01T00:00:00",
                "isActive", true
        );
    }

    @AfterEach
    void tearDown() {
        membershipRepository.removeAll();
    }
}
