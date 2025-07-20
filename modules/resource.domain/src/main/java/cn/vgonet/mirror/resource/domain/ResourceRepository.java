package cn.vgonet.mirror.resource.domain;

public interface ResourceRepository {
    void removeAll();

    void save(Resource resource);

    Resource resourceForFirst();

    Resource resourceForId(String id);
}
