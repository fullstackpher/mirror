package cn.vgonet.mirror.usecase.resource;

import cn.vgonet.mirror.resource.domain.*;
import cn.vgonet.mirror.resource.gateways.persistence.JpaSource;
import cn.vgonet.mirror.resource.gateways.persistence.JpaSourceRepository;
import cn.vgonet.mirror.test.IntegrationTest;
import cn.vgonet.mirror.test.TestResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


public class SyncResourceTest extends IntegrationTest {
    private @Resource ResourceRepository resourceRepository;
    private @Resource JpaSourceRepository jpaSourceRepository;
    private @Resource SyncResourceService syncResourceService;

    @BeforeEach
    void setUp() {
        Date fixedDate = Date.from(Instant.parse("2023-01-01T00:00:00Z"));
        jpaSourceRepository.save(new JpaSource(new Source("1001", "v1", "c001", "中创网",
                "link1", fixedDate)));
        jpaSourceRepository.save(new JpaSource(new Source("1002", "v2", "c002", "福缘网",
                "link2", fixedDate)));

        syncResourceService.sync();
    }

    @Test
    void show_be_able_to_sync_resource_successfully() {
        TestResponse response = get("/resources/sync");
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.<String>value("$.errcode")).isEqualTo("0");
        assertThat(response.<String>value("$.errmsg")).isEqualTo("");
        assertThat(response.<String>value("$.data.id")).isEqualTo("1001");
        cn.vgonet.mirror.resource.domain.Resource resource = resourceRepository.resourceForId("1001");
        assertThat(resource.title()).isEqualTo("v1");
        assertThat(resource.description()).isEqualTo("c001");
        assertThat(resource.type()).isEqualTo("中创网");
        assertThat(resource.link()).isEqualTo("link1");
        assertThat(resource.createAt()).isEqualTo(Date.from(Instant.parse("2023-01-01T00:00:00Z")));
    }


    @AfterEach
    void tearDown() {
        jpaSourceRepository.deleteAll();
        resourceRepository.removeAll();
    }

}
