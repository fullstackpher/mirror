package cn.vgonet.mirror.frameworks.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FixedIdentityServiceTest {
    public static final String FIXED = "fixed";

    @Test
    void should_generate_fixed_identity() {
        IdentityService identityService = new FixedIdentityService(FIXED);
        assertThat(identityService.nextIdentity()).isEqualTo(FIXED);
        assertThat(identityService.nextReadableIdentity()).isEqualTo(FIXED);
    }
}
