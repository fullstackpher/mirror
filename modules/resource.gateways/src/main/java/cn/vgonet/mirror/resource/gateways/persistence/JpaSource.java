package cn.vgonet.mirror.resource.gateways.persistence;

import cn.vgonet.mirror.resource.domain.Resource;
import cn.vgonet.mirror.resource.domain.Source;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "source")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JpaSource {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String link;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public JpaSource(Source source) {
        this.id = source.id();
        this.title = source.title();
        this.description = source.description();
        this.type = source.type();
        this.link = source.link();
        this.createdAt = source.createdAt();
    }

    public Resource toResource() {
        return new Resource(id, title, description, type, link, createdAt);
    }
}
