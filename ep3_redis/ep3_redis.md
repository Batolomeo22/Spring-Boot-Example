# EPISODE3 REDIS&SPRINGBOOT
    
## 如何使用
1. pom.xml中添加redis依赖   
    ```
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
    </dependencies>
    ```
     Spring Boot 1.0 默认使用的是 Jedis 客户端，2.0 替换成 Lettuce。  
     Lettuce 是一个可伸缩线程安全的 Redis 客户端，多个线程可以共享同一个 RedisConnection，它利用优秀 netty NIO 框架来高效地管理多个连接。
2. 在application.properties中添加配置  
   
    ```
    # Redis数据库索引（默认为0）
    spring.redis.database=0  
    # Redis服务器地址
    spring.redis.host=localhost
    # Redis服务器连接端口
    spring.redis.port=6379  
    # Redis服务器连接密码（默认为空）
    spring.redis.password=
    # 连接池最大连接数（使用负值表示没有限制） 默认 8
    spring.redis.lettuce.pool.max-active=8
    # 连接池最大阻塞等待时间（使用负值表示没有限制） 默认 -1
    spring.redis.lettuce.pool.max-wait=-1
    # 连接池中的最大空闲连接 默认 8
    spring.redis.lettuce.pool.max-idle=8
    # 连接池中的最小空闲连接 默认 0
    spring.redis.lettuce.pool.min-idle=0
    ```  
3. 添加Redis 配置类 RedisConfig  
   ```java
    package com.bravoz.ep3redis.config;
    
    import org.springframework.cache.annotation.CachingConfigurerSupport;
    import org.springframework.cache.annotation.EnableCaching;
    import org.springframework.cache.interceptor.KeyGenerator;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
    import org.springframework.data.redis.serializer.RedisSerializer;
    import org.springframework.data.redis.serializer.StringRedisSerializer;
    
    import java.lang.reflect.Method;
    
    @Configuration
    @EnableCaching
    public class RedisConfig extends CachingConfigurerSupport {
    
        @Bean
        public KeyGenerator keyGenerator(){
            return new KeyGenerator() {
                @Override
                public Object generate(Object target, Method method, Object... params) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.getClass().getName());
                    sb.append(method.getName());
                    for (Object obj : params){
                        sb.append(obj.toString());
                    }
                    System.out.println(sb.toString());
                    return sb.toString();
                }
            };
        }
    
        @Bean
        public RedisTemplate redisTemplate(RedisTemplate redisTemplate){
            RedisSerializer stringSerializer = new StringRedisSerializer();
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            redisTemplate.setKeySerializer(stringSerializer);
            redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
            redisTemplate.setHashKeySerializer(stringSerializer);
            redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    
            return redisTemplate;
    
        }
    }

   ```   
   说明：  
   - KeyGenerator的作用是在写入redis缓存的时候自定义key的格式。此处使用类名+方法名+方法参数  
    "user-key::com.bravoz.ep3redis.controller.cachableControllergetUser311  
   - redisTemplate作用是防止存入redis数据序列化出现乱码问题。  

4. 根据请求方法生成缓存
   ```java
    package com.bravoz.ep3redis.controller;
    
    import com.bravoz.ep3redis.model.User;
    import org.springframework.cache.annotation.Cacheable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    
    @RestController
    public class cachableController {
        /**
         * 第一个未指定key，第二第三个指定了key为传入参数的id
         * 127.0.0.1:6379> keys user-key*
         * 1) "user-key::com.bravoz.ep3redis.controller.cachableControllergetUser"
         * 2) "user-key::10"
         * 3) "user-key::11"     *
         */
    
        @RequestMapping("/getUser")
        @Cacheable(value="user-key")
        public User getUser(){
            User user = new User("aaa",15,"aaa@email.com");
            System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
    
            return user;
        }
    
        @RequestMapping("/getUser2")
        @Cacheable(value="user-key",key = "#id + ''")
        public User getUser2(int id){
            User user = new User("bbb",15,"bbb@email.com");
            System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
    
            return user;
        }
    
        @RequestMapping("/getUser3")
        @Cacheable(value="user-key")
        public User getUser3(int id){
            User user = new User("ccc",id,"ccc@email.com");
            System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
    
            return user;
        }
    }

   ```

## 共享Session

### Spring Session 官方说明

Spring Session 提供了一套创建和管理 Servlet HttpSession 的方案。Spring Session 提供了集群 Session（Clustered Sessions）功能，默认采用外置的 Redis 来存储 Session 数据，以此来解决 Session 共享的问题。  

### 如何使用
1. 引入依赖
   ```properties
    <dependency>
        <groupId>org.springframework.session</groupId>
        <artifactId>spring-session-data-redis</artifactId>
    </dependency>
   ```
2. 添加配置类SessionConfig
   ```java
    package com.bravoz.ep3redis.config;
    
    import org.springframework.context.annotation.Configuration;
    import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
    
    
    //TODO
    // maxInactiveIntervalInSeconds:设置 Session 失效时间，使用 Redis Session 之后，
    // 原 Spring Boot 的 server.session.timeout 属性不再生效。
    @Configuration
    @EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
    public class SessionConfig {
    }

   ```
3. 测试  
   - 添加测试方法获取session_id
   ```java
    @RestController
    public class SessionController {
    
        @RequestMapping("/uid")
        public String sessionTest(HttpSession session){
            UUID uid = (UUID)session.getAttribute("uid");
            if(uid == null){
                uid = UUID.randomUUID();
            }
            session.setAttribute("uid",uid);
            return session.getId();
        }
    }
   ```   
   - 在redis中查看保存的session信息
   ```shell script
    127.0.0.1:6379> keys *session*
    1) "spring:session:sessions:expires:30c1236a-ebfe-4cf8-a63f-6bdb69712a0f"
    2) "spring:session:expirations:1570087320000"
    3) "spring:session:sessions:30c1236a-ebfe-4cf8-a63f-6bdb69712a0f"
   ```

### 以上