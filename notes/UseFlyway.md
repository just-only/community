## FlyWay工具
   #### 简介
     FlyWay用于数据库的版本控制。当一个项目中有多个用户对数据库进行控制时，
    该工具用于将多个用户的数据库版本来合并，推送到测试数据库和生产数据库中
   
   #### 如何工作
     该工具使用Metadata表和6个命令来进行管理，metadata用于记录元数据，
     每种命令功能和解决问题范围不一样
     
      1.Metadata表
        用于记录所有版本演化和状态，在Flyway首次启动时会创建默认名为SCHEMA_VERSION的元数据表
        
      2.操作命令
        Migrate是指把数据库迁移到最新版本，
           是Flyway工作流的核心功能，Flyway在Migrate时会检查Metadata(元数据)表，
           如果不存在会创建Metadata表，Metadata表主要用于记录版本变更历史之类 
           Migrate会将Classpath下的Migrations文件进行检测，
           然后和Metadata数据表已存在的记录比对，将未应用的MIGratios应用到数据库
        clean 清楚缓存数据表
        info 用于打印migrate中的详尽信息
        validate 指验证已经Apply的Migrations是否有变更  原理是对比Metadata表与本地Migrations的Checksum值
        Baseline Baseline可以应用到特定的版本，这样在已有表结构的数据库中也可以实现添加Metadata表
        Repair 操作能够修复Metadata表，该操作在Metadata表出现错误时是非常有用的
      
   #### 操作
      
      1. 创建Migrates
        flyway对Migrates的扫描文件需要遵循一定的命名格式
        有两个文件进行命名
        Versioned migrations  一般常用的是Versioned类型，用于版本升级
        Repeatable  Repeatable是指可重复加载的Migrations，其每一次的更新会影响Checksum值，然后都会被重新加载，并不用于版本升级Versioned 
        migrations 文件命名：
        {prefix}{version}_{description}{suffix}
        Repeatable
        {prefix}_{description}{suffix}
        V+版本号 +双下划线+秒速+结束符 
        prefix: 可配置，前缀标识，默认值 V 表示 Versioned, R 表示 Repeatable
        version: 标识版本号, 由一个或多个数字构成, 数字之间的分隔符可用点.或下划线_
        separator: 可配置, 用于分隔版本标识与描述信息, 默认为两个下划线__
        description: 描述信息, 文字之间可以用下划线或空格分隔
        suffix: 可配置, 后续标识, 默认为.sql
        
        实例：
        V20180317.10.59__V1.2_Unique_User_Names.sql
        V20180317.14.59__V1.2_Add_SomeTables.sql
      
      2.支持数据库
        # PostgreSQL
        flyway.url = jdbc:postgresql://localhost:5432/postgres?currentSchema=myschema
        # MySQL
        flyway.url = jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC&useSSL=true
        # H2
        flyway.url = jdbc:h2:./.tmp/testdb
        # Hsql
        flyway.url = jdbc:hsqldb:hsql//localhost:1476/testdb
      
      3.配置文件配置
        ## 设定 db source 属性
        spring.datasource.url=jdbc:mysql://localhost:3306/world
        spring.datasource.username=root
        spring.datasource.password=toor
        
        ## 设定 flyway 属性 
        spring.flyway.cleanDisabled = true 
          # flyway 的 clean 命令会删除指定 schema 下的所有 table, 杀伤力太大了, 应该禁掉. 
        spring.flyway.enabled = true
          # 启用或禁用 flyway 
        spring.flyway.locations =classpath:db/migration
          # 设定 SQL 脚本的目录,多个路径使用逗号分隔, 比如取值为 classpath:db/migration,filesystem:/sql-migrations
        spring.flyway.baselineOnMigrate=true
          # 如果指定 schema 包含了其他表,但没有 flyway schema history 表的话, 在执行 flyway migrate 命令之前, 必须先执行 flyway baseline 命令.
          # 设置 spring.flyway.baseline-on-migrate 为 true 后, flyway 将在需要 baseline 的时候, 自动执行一次 baseline. 
        spring.flyway.baselineVersion=1 
          # 指定 baseline 的版本号,缺省值为 1, 低于该版本号的 SQL 文件, migrate 的时候被忽略. 
        #spring.flyway.encoding=
          # Encoding of SQL migrations (default: UTF-8)
        spring.flyway.table=flyway_schema_history_myapp
          # 设定 flyway 的 metadata 表名, 缺省为 flyway_schema_history
        spring.flyway.outOfOrder=true
          # 开发环境最好开启 outOfOrder, 生产环境关闭 outOfOrder . 
        #spring.flyway.schemas=
          # 需要 flyway 管控的 schema list, 缺省的话, 使用的时 dbsource.connection直连上的那个 schema, 可以指定多个schema, 但仅会在第一个schema下建立 metadata 表, 也仅在第一个schema应用migration sql 脚本. 但flyway Clean 命令会依次在这些schema下都执行一遍
   #### 使用
      首先需要将配置文件进行配置，将插件进行集合
      1.创建文件夹 classpath:db/migration
      2.在migration中创建文件V2__Add-bio-to-user.sql等文件
      3.使用命令 mvn flyway:baseline 来创建 flyway_schema_history 表，进行初始化
      4.使用命令 mvn flyway:migrate 将文件内容推送到 flyway_schema_history表中
      5. 使用命令 mvn flyway:info 来查看表flyway_schema_history中的文件记录是否成功
      6.使用命令 mvn flyway:validate 将flyway_schema_history表中的sql根据checknum来比对进行数据库的更改
 