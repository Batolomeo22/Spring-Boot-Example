##坑点记录

1. 文件编码问题  
IDEA编码：  
File->Settings->搜索File Encoding 修改为UTF-8

2. 读取配置中的中文字符   
    直接输出会显示为乱码,需要转换编码
    ```$java
    String tilteStr = new String(title.getBytes("ISO8859-1"), "UTF-8");
    ```
3. 实体类和dao  
   - 如实体类名称为User，dao名称要UserRepository，否则会报错  
   - dao继承JpaRepository就可以了，里面的方法是根据实体类里的属性可以自动生成。   
   - 实体类里需要指定id，不指定id也会报错。
   - 实体类字段为stuName,则数据库表字段为stu_name
   
4. JSP页面   
    - 先在pom.xml中添加下列依赖   
    ```$xml
   <!-- 添加servlet依赖模块 -->
       <dependencies>
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>javax.servlet-api</artifactId>
               <scope>provided</scope>
           </dependency>
           <!-- 添加jstl标签库依赖模块 -->
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>jstl</artifactId>
           </dependency>
           <!--添加tomcat依赖模块.-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-tomcat</artifactId>
               <scope>provided</scope>
           </dependency>
           <!-- 使用jsp引擎，springboot内置tomcat没有此依赖 -->
           <dependency>
               <groupId>org.apache.tomcat.embed</groupId>
               <artifactId>tomcat-embed-jasper</artifactId>
               <scope>provided</scope>
           </dependency>
       </dependencies>
    ```
    - application.properties中添加属性
    ```properties
    spring.mvc.view.prefix=/WEB-INF/
    spring.mvc.view.view-name=jsp/*
    spring.mvc.view.suffix=.jsp
    spring.mvc.view.order=2
    ```
   - 添加viewResolver
   ```java
    @Configuration
    public class JspResolver {
    
        @Value("${spring.mvc.view.prefix}")
        private String prefix;
    
        @Value("${spring.mvc.view.view-name}")
        private String viewname;
    
        @Value("${spring.mvc.view.suffix}")
        private String suffix;
    
        @Value("${spring.mvc.view.order}")
        private int order;
    
        @Bean
        InternalResourceViewResolver jspViewResolver(){
            final InternalResourceViewResolver  viewResolver = new InternalResourceViewResolver();
            viewResolver.setPrefix(prefix);
            viewResolver.setViewNames(viewname);
            viewResolver.setSuffix(suffix);
            viewResolver.setOrder(order);
    
            return viewResolver;
        }
    }
   ```
   - 在/src/main下添加目录webapp/WEB-INF/jsp,添加indexJsp.jsp页面
   - 在controller中添加requestmapping
   ```java
   public class WebController {
       @RequestMapping("/indexJsp")
       public String getJsp(){
           return "jsp/indexJsp";
       }
   }
   ```
5.mysql问题   
    在配置文件application.properties中需要添加时区参数，否则会报错
    spring.datasource.url=jdbc:mysql://localhost:3306/world?serverTimezone=Asia/Shanghai