
Mybatis-generator是什么？

书归正传，Mybatis-generator是什么？在使用Mybatis的时候，dao接口，entity实体类，还有每个实体类对应的xml都得自己写吧，这其实也是工作量稍微大一点的事情，
而我们的插件Mybatis-generator就是自动生成这些代码的
Mybatis-generator怎么使用？

 第一步，Maven文件引用

首先，新建一个过程，带Maven就行，我新建了一个SpringBoot项目，pom.xml文件需要添加4个引用，如下

<dependencies>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.0.1</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.15</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-core</artifactId>
            <version>1.3.7</version>
        </dependency>

    </dependencies>


    <build>
        <plugins>

            <plugin>
                <!--Mybatis-generator插件,用于自动生成Mapper和POJO-->
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.2</version>
                <configuration>
                    <!--配置文件的位置-->
                    <configurationFile>src/main/resources/mybatis/mybatis-generator.xml</configurationFile>
                    <verbose>true</verbose>
                    <overwrite>true</overwrite>
                </configuration>
                <executions>
                    <execution>
                        <id>Generate MyBatis Artifacts</id>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                    </execution>
                </executions>
                <dependencies>
                    <dependency>
                        <groupId>org.mybatis.generator</groupId>
                        <artifactId>mybatis-generator-core</artifactId>
                        <version>1.3.2</version>
                    </dependency>
                </dependencies>
            </plugin>


        </plugins>
    </build>

</project>


mysql-connector-java 我的数据库是mysql，这里我用了最新的8版本，需要注意的是，我电脑上安装的mysql就是最新版的，所以我只能选8版本之后的，旧版不行
1. mybatis-generator-core 插件，也得要吧
2. plugin 下面的plugin插件那里，复制我的就可以了，需要注意的是src/main/resources/mybatis/mybatis-generator.xml这个路径是我自己的，你自己改一下你的mybatis-generator.xml的路径，这个文件接下来建
 第二步，数据库建立

数据库建两个表吧，我把我的贴出来，你想自己写就自己写，不想写就复制我的

-- auto-generated definition
create table user_info
(
  id             int                      not null,
  name           varchar(10) charset utf8 not null,
  gender         varchar(10) charset utf8 null,
  age            int                      null,
  phone          varchar(20)              null,
  register_mode  varchar(20)              null comment '注册方式，例如手机注册，微信注册等',
  third_party_id varchar(64)              null comment '第三方id，例如微信的id',
  constraint user_info_id_uindex
    unique (id)
);

alter table user_info
  add primary key (id);


-- auto-generated definition
create table user_password
(
  id               int          not null
    primary key,
  encrypt_password varchar(128) not null,
  user_id          int          not null
);


数据库是miaosha，自己新建数据库，然后执行上面的创建表的语句。数据暂时不需要
 第三步，写mybatis-generator.xml

最后一步了，这个xml位置你看着放，内容复制我的，我告诉你改哪里

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <!--数据库驱动-->
    <classPathEntry    location="D:\Java\apache-maven-3.5.4\repository\mysql\mysql-connector-java\8.0.15\mysql-connector-java-8.0.15.jar"/>
    <context id="context"    targetRuntime="MyBatis3">
        <commentGenerator>
            <property name="suppressDate" value="true"/>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>
        <!--数据库链接地址账号密码-->
        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver" connectionURL="jdbc:mysql://localhost:3306/miaosha?serverTimezone=UTC" userId="root" password="123">
        </jdbcConnection>
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>
        <!--生成Model类存放位置-->
        <javaModelGenerator targetPackage="com.miaosha.dataobject" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!--生成映射文件存放位置-->
        <sqlMapGenerator targetPackage="mybatis.mapper" targetProject="src/main/resources">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>
        <!--生成Dao类存放位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.miaosha.dao" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>
        <!--生成对应表及类名-->
        <table tableName="user_info" domainObjectName="userDAO" enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"></table>
        <table tableName="user_password" domainObjectName="userpasswordDAO" enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"></table>
    </context>
</generatorConfiguration>


需要改的地方：
1. location="D:\Java\apache-maven-3.5.4\repository\mysql\mysql-connector-java\8.0.15\mysql-connector-java-8.0.15.jar" 这个location啊，这一点我是真的无语，非得写绝对路径？我暂时不知道其他的写法，网上的文章这一块都是瞎几把写的。这里你就写你的Maven仓库的路径就可以了，找到mysql的jar，和maven里面引用的mysql的版本一致
2.  这个需要改，你自己写吧，mysql8版本之后需要加com.mysql.cj.jdbc.Driver，加上cj。还有，数据库后面跟上这个时区?serverTimezone=UTC
3. model和mapper没啥改的，包名改成你的，路径就是src/main/resources或者java
4.  最后这个也简单，表名一写，dao名一写，完事
 第四步，运行

这个很简单，编辑配置，新建maven，然后输入图中的内容，直接运行即可
mybatis-generator:generate
