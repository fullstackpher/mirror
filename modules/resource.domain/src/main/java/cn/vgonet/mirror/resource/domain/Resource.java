package cn.vgonet.mirror.resource.domain;

import java.time.LocalDateTime;

public class Resource {
    private final String id;
    private final String title;
    private final String description;
    private final String type;
    private final String link;
    private final LocalDateTime createdAt;

    public Resource(String id, String title, String description, String type, String link, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.link = link;
        this.createdAt = createdAt;
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public String type() {
        return type;
    }

    public String link() {
        return link;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
