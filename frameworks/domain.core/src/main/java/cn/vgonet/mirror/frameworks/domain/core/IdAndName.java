package cn.vgonet.mirror.frameworks.domain.core;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class IdAndName {
    private final String id;
    private final String name;

    public IdAndName(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
