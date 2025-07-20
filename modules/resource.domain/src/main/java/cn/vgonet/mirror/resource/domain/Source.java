package cn.vgonet.mirror.resource.domain;

import java.util.Date;

public class Source {
    private final String id;
    private final String title;
    private final String description;
    private final String type;
    private final String link;
    private final Date createAt;

    public Source(String id, String title, String description, String type, String link, Date createAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.link = link;
        this.createAt = createAt;
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

    public Date createAt() {
        return createAt;
    }
}
