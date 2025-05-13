package cn.vgonet.mirror.frameworks.domain.core;

public interface IdentityService {
    String nextIdentity();

    String nextReadableIdentity();
}
