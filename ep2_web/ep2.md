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
   
4.添加Jsp  
   
