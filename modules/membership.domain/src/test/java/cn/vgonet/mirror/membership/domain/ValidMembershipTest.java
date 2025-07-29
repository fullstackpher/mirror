package cn.vgonet.mirror.membership.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ValidMembershipTest {
    private MembershipRepository membershipRepository;
    @BeforeEach
    void setUp() {
        membershipRepository = mock(MembershipRepository.class);
    }

    @Test
    void should_throw_exception_when_membership_is_not_exists() {
        given(membershipRepository.membershipFor(any())).willReturn(null);
        Throwable throwable = catchThrowable(() -> new ValidMembership(membershipRepository).validForId(any()));
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("Membership not found");
    }
}