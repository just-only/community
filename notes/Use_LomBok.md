#### LomBok
   #####简介
     Lombok项目是一个Java库，它会自动插入编辑器和构建工具中，Lombok提供了一组有用的注释，用来消除Java类中的大量样板代码。
     仅五个字符(@Data)就可以替换数百行代码从而产生干净，简洁且易于维护的Java类。
   
   ##### 注意
     自动注入get/set等方法的过程在编译阶段，编译器需要引入lombok的插件，才能正常使用
     
   #####注释
     val
     最终局部变量。
     
     var
     局部变量。
     
     @NonNull
    不再担心NullPointerException。
     
     @Cleanup
     自动资源管理：close()轻松安全地调用方法。
     
     @Getter/@Setter
     不再写public int getFoo() {return foo;}。
     
     @ToString
     只需让lombok toString为您生成一个！
     
     @EqualsAndHashCode
     对象的字段生成hashCode和equals实现。
     
     @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor
     按顺序构造函数：生成不带参数的构造函数，每个final / non-nullfield都带一个参数，或者每个字段带一个参数。
     
     @Data
     为快捷方式@ToString，@EqualsAndHashCode， @Getter在所有领域，@Setter所有非final字段，以及 @RequiredArgsConstructor！
     
     @Value
     不可变的类非常容易。
     
     @Builder
    创建对象
     
     @SneakyThrows
     检查异常！
     
     @Synchronized
     synchronized 锁。
     
     @With
     创建克隆但具有一个已更改字段的方法。
     
     @Getter(lazy=true)
     Laziness is a virtue!
     
     @Log
     日志，日期为24435.7：
     
   ###### 使用
     
     1.引入依赖
            <dependency>
                   <groupId>org.projectlombok</groupId>
                   <artifactId>lombok</artifactId>
                   <version>1.18.12</version>
            </dependency>
     2.使用注释
        例如：
          @Data //该注释将自动给类创建get/set方法
          public class GithubUser {
              private Long id;
              private String name;
              private String bio;
          }
          