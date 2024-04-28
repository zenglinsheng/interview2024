package interview.redisDistributeLock;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings("all")
public class RedisLockWithSetNx {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLockWithSetNx(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public String getLock() {
        String uuid = UUID.randomUUID().toString();
        //redis分布式锁     如果key不存在设置成功  设置过期时间  5分钟
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent("lock", uuid,5, TimeUnit.MINUTES);
        if (lock){
            //加锁成功  调用方法返回数据
            String dataFromDB = getDataFromDB();
            //获取到锁的值
            String curLock = stringRedisTemplate.opsForValue().get("lock");
            if (curLock.equals(uuid)){
                //如果curLock的值等于当前uuid的值  那么就删除锁
                stringRedisTemplate.delete("lock");
            }
            String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
                    "    return redis.call(\"del\",KEYS[1])\n" +
                    "else\n" +
                    "    return 0\n" +
                    "end";
            //new DefaultRedisScript<Integer>(script)  泛型为返回值的类型 参数为脚本
            Long execute = stringRedisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList("lock"), uuid);

            return dataFromDB;
        } else {
            //没有分布式锁,等待,进入自旋
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //自旋
            return getLock();
        }
    }

    private String getDataFromDB() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

}
