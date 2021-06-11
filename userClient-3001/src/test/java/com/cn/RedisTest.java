package com.cn;

import com.cn.feignclient.RoleFeignClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;

/**
 * @auther
 * @date 2021-06-11-15:43
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest{

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;

/*    @Autowired
    private Jedis jedis;*/

    @Test
    public void test(){
        redisTemplate.boundValueOps("springcloud").set("alibaba",60, TimeUnit.SECONDS);
        Long time = redisTemplate.getExpire("springcloud");
        System.out.println(time+" : "+time);
        String value = redisTemplate.opsForValue().get("springcloud");
        System.out.println("键:springcloud == 值"+value);
    }

    @Test
    public void redisTest(){
        Jedis jedis = jedisPool.getResource();
        String lockKey = "lockkey";
        String requestId = UUID.randomUUID().toString();
        if("OK".equals(jedis.set(lockKey,requestId,"NX","PX",60))){
            jedis.set("lockKey","锁测试");
            jedis.expire("lockKey",10);
            jedis.del(lockKey);
        }else {
            try{
                sleep(10);
            }catch (Exception e){
            }
        }
    }

}
