package cn.vgonet.mirror.frameworks.domain.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UuidBasedIdentityServiceTest {
    private IdentityService identityService;

    @BeforeEach
    public void setUp() {
        identityService = new UuidBasedIdentityService();
    }

    @Test
    public void should_generate_identity_correctly() {
        assertThat(32).isEqualTo(identityService.nextIdentity().length());
    }

    @Test
    public void should_generate_non_duplicated_identities() {
        String id = identityService.nextIdentity();
        assertThat(id).isNotEqualTo(identityService.nextIdentity());
    }

    @Test
    public void should_generate_readable_identity_correctly() {
        assertThat(identityService.nextReadableIdentity().length()).isEqualTo(21);
        assertThat(identityService.nextReadableIdentity().chars().allMatch(Character::isDigit)).isTrue();
    }

    @Test
    public void should_generate_non_duplicated_readable_identities() {
        String id = identityService.nextReadableIdentity();
        assertThat(identityService.nextReadableIdentity()).isNotEqualTo(id);
    }
}
