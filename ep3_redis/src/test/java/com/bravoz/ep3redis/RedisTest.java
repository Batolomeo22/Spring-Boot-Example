package com.bravoz.ep3redis;

import com.bravoz.ep3redis.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "1111");
        Assert.assertEquals("1111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        User user = new User("jack", 20, "jackloverose@titanic.com");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("xxx", user);

        operations.set("bravoy", user, 1, TimeUnit.SECONDS);//生命周期1秒
        Thread.sleep(1000);

        boolean exist = redisTemplate.hasKey("bravoy");
        if (exist) {
            System.out.println("bravoy is alive!");
        } else {
            System.out.println("bravoy has gone! RIP!");
        }

    }
}
