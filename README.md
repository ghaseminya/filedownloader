# filedownloader
this is a simple servlet for download file from any folder in server in webserver such as tomcat and jboss wildfly

database structure:

```sql
CREATE TABLE `file` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `fpath` varchar(5000) NOT NULL,
  `downloaded` int(11) NOT NULL DEFAULT '0',
  `dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

set mysql configuration:
change this variable in db.java class
```java
DB_URL="";
USER="";
PASS="";
```
