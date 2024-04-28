package interview.zkDistributeLock05;

import interview.redisson06.RedissonProperties;
import io.micrometer.core.instrument.util.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Config.class)
@EnableConfigurationProperties(RedissonProperties.class)
@SuppressWarnings("all")
public class RedissonConfig {

    @Autowired
    private RedissonProperties redissonProperties;

    /**
     * 单机模式自动装配
     * @return
     */
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.setCodec(new StringCodec());
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redissonProperties.getAddress())
                .setPassword(redissonProperties.getPassword())
                .setTimeout(redissonProperties.getTimeout())
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize());

        if(StringUtils.isNotEmpty(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }

        return Redisson.create(config);
    }

}
