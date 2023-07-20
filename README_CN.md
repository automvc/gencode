
GenCode
=========
**GenCode** 是基于Bee的一个快速生成代码工具！  
支持生成restful风格的后端代码.  
Controller/Action + service (+ dao).  

Spring boot restful back end mvc code.  
Controller/Action + service (+ dao).  

Bee，互联网新时代的 Java ORM 框架，支持 Sharding；JDBC，Android，HarmonyOS；支持多种关系型数据库，还支持 NoSQL 的 Cassandra，Mongodb(V2.1) 等；更快、更简单、更自动，开发速度快，运行快，更智能！  
支持多种关系型数据库：MySQL,MariaDB,Oracle,H2,SQLite,PostgreSQL,SQL Server,Access 等。     

快速开始:
=========	
## 1.添加依赖     
```xml
		<dependency>
			<groupId>org.teasoft</groupId>
			<artifactId>gencode</artifactId>
			<version>2.1.7</version>
			<type>pom</type>
		</dependency>
```

## 2.只一行Java代码,即可生成CRUD后端代码     

```java
public static void main(String[] args) {
		GenCode.genRestRich("users", "com.abc");
	}
```

**Bee** 网址:  
https://github.com/automvc/bee  

**Bee在gitee** 的网址:  
https://gitee.com/automvc/bee
