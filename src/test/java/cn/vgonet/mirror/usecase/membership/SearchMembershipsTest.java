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

public class SearchMembershipsTest extends IntegrationTest {
    private @Resource MembershipRepository membershipRepository;

    @BeforeEach
    void setup() {
        membershipRepository.save(new Membership("1001", "永久会员", LocalDateTime.parse("2022-01-01T00:00:00"), LocalDateTime.parse("2022-01-02T00:00:00"), true));
        membershipRepository.save(new Membership("1002", "永久会员", LocalDateTime.parse("2022-01-01T00:00:00"), LocalDateTime.parse("2022-01-02T00:00:00"), true));
        membershipRepository.save(new Membership("1003", "永久会员", LocalDateTime.parse("2022-01-01T00:00:00"), LocalDateTime.parse("2022-01-02T00:00:00"), true));
        membershipRepository.save(new Membership("1004", "level2", LocalDateTime.parse("2022-01-01T00:00:00"), LocalDateTime.parse("2022-01-02T00:00:00"), true));
        membershipRepository.save(new Membership("1005", "level3", LocalDateTime.parse("2022-01-01T00:00:00"), LocalDateTime.parse("2022-01-02T00:00:00"), true));
    }

    @Test
    void should_be_able_to_search_memberships() {
        TestResponse response = get("/memberships?q=永久会员&size=2&page=1");
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.<String>value("$.errcode")).isEqualTo("0");
        assertThat(response.<String>value("$.errmsg")).isEqualTo("");
        assertThat(response.<Number>value("$.data.numberOfElements")).isEqualTo(1);
        assertThat(response.<Number>value("$.data.totalElements")).isEqualTo(3);
        assertThat(response.<Number>value("$.data.number")).isEqualTo(1);
        assertThat(response.<Number>value("$.data.size")).isEqualTo(2);
        assertThat(response.<String>value("$.data.content[0].id")).isEqualTo("1003");
        assertThat(response.<String>value("$.data.content[0].level")).isEqualTo("永久会员");
        assertThat(response.<String>value("$.data.content[0].createdAt")).isEqualTo("2022-01-01T00:00:00");
        assertThat(response.<String>value("$.data.content[0].expiredAt")).isEqualTo("2022-01-02T00:00:00");
        assertThat(response.<Boolean>value("$.data.content[0].isActive")).isTrue();
    }

    @AfterEach
    void tearDown() {
        membershipRepository.removeAll();
    }
}
