#springboot 整合mybatis步骤
	1.pom引入mybatis模块jar
	2.在 application.properties 应用配置文件，增加 Mybatis相关配置
		配置详情如下：
		数据源配置：
		spring.datasource.url=jdbc:mysql://localhost:3306/trs?useUnicode=true&characterEncoding=utf8
		spring.datasource.username=root
		spring.datasource.password=123456
		spring.datasource.driver-class-name=com.mysql.jdbc.Driver
		mybatis相关配置
		mybatis.typeAliasesPackage 配置为 org.spring.springboot.domain，指向实体类包路径。
		mybatis.mapperLocations 配置为 classpath 路径下 mapper 包下，* 代表会扫描所有 xml 文件。
	3.在 Application 应用启动类添加注解 MapperScan(例如@MapperScan("org.spring.springboot.dao"))
	4.添加相应的实体类以及mapper接口