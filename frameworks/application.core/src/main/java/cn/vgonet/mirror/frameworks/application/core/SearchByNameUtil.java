package cn.vgonet.mirror.frameworks.application.core;

import lombok.Generated;
import org.apache.commons.lang3.StringUtils;

@Generated
public class SearchByNameUtil {
    public static String getQ(String name) {
        String q = "%" + name + "%";
        if (StringUtils.isBlank(name)) {
            q = "%%";
        }
        return q;
    }
}
