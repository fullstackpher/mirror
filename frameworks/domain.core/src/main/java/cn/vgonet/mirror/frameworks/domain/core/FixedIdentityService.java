package cn.vgonet.mirror.frameworks.domain.core;


public class FixedIdentityService implements IdentityService {
    private final String id;

    public FixedIdentityService(String id) {
        this.id = id;
    }

    @Override
    public String nextIdentity() {
        return nextReadableIdentity();
    }

    @Override
    public String nextReadableIdentity() {
        return id;
    }
}
