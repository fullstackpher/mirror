package cn.vgonet.mirror.frameworks.domain.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalIdentityService {
    private static IdentityService identityService;

    public static String next() {
        return identityService.nextIdentity();
    }

    static void reset(IdentityService identityService) {
        GlobalIdentityService.identityService = identityService;
    }
}
