package cn.vgonet.mirror.ping;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pong {
    private @Id final String id;
    private final String pong;

    public Pong(String id, String pong) {
        this.id = id;
        this.pong = pong;
    }

    public String getId() {
        return id;
    }

    public String getPong() {
        return pong;
    }
}
