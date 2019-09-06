## EPISODE4——Thymeleaf的使用

### 1.Thymeleaf动态刷新
- 1、pom中添加下列配置。  
```
<!-- 项目热部署 -->
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional> <!-- 表示依赖不会传递 -->
        <scope>true</scope>
    </dependency>
</dependencies>
<plugins>
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
            <fork>true</fork> <!-- 如果没有该配置，devtools不会生效 -->
        </configuration>
    </plugin>
</plugins>
```

- 2、设置IDEA相关配置  
搜索配置项 Build Project Automatically 打勾  
搜索配置项 Registry -> compiler.automake.allow.when.app.running

### 2. 