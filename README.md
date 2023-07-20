
GenCode
=========
**GenCode** generate code automatically !  
Spring boot restful back end mvc code.  
Controller/Action + service (+ dao).  

**Bee** is an ORM framework.   
**Bee** is an easy and high efficiency ORM framework.   
Bee is a Java ORM framework for the new era of the internet, which supports Sharding.   
It supports various relational databases, as well as NoSQL databases such as Cassandra and MongoDB (V2.1).
Bee is faster, simpler, and more automated, allowing for faster development and execution, and it is also more intelligent.   
It supports multiple relational databases, including MySQL,MariaDB,Oracle,H2,SQLite,PostgreSQL,SQL Server,Access.   


quick start:
=========	
## 1.add dependency   
```xml
		<dependency>
			<groupId>org.teasoft</groupId>
			<artifactId>gencode</artifactId>
			<version>2.1.7</version>
			<type>pom</type>
		</dependency>
```

## 2.Just one line for gen CRUD   

```java
public static void main(String[] args) {
		GenCode.genRestRich("users", "com.abc");
	}
```

**Bee** :  
https://github.com/automvc/bee  

**Bee on gitee** :  
https://gitee.com/automvc/bee
