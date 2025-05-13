package db.migration;

import cn.vgonet.mirror.ping.Pong;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator.impl.MongockTemplate;

@ChangeLog
public class V202109251646PongInitialChange {
    @ChangeSet(order = "V202109251646", id = "Initialize pong", author = "van@highsoft.ltd")
    public void initializePong(MongockTemplate mongoTemplate) {
        mongoTemplate.save(new Pong("ping", "archegos"));
    }
}
