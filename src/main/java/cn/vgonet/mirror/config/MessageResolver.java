package cn.vgonet.mirror.config;

public interface MessageResolver {
    String resolve(String code, Object... args);
}
