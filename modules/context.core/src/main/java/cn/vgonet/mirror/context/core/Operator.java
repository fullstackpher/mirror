package cn.vgonet.mirror.context.core;


import cn.vgonet.mirror.frameworks.domain.core.IdAndName;

import javax.annotation.Nullable;
import java.util.Optional;

public class Operator {
    private final String id;
    private final String name;
    private final IdAndName client;

    public Operator(String id, String name, @Nullable IdAndName client) {
        this.id = id;
        this.name = name;
        this.client = client;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Optional<IdAndName> client() {
        return Optional.ofNullable(client);
    }
}
