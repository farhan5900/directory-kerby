<!--
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
-->

MySQL Plugin
===============

## Install MySQL

Please refer to [install mysql](https://dev.mysql.com/doc/refman/5.7/en/linux-installation.html).

## Prepare user infomation in MySQL

### Create database "has" and create table "has_user"
```
mysql> create database has;
mysql> use has;
mysql> CREATE TABLE has_user(user_name VARCHAR(100), pass_word VARCHAR(100));
```

### Insert user into table
Example, username is "hdfs", password is "test":
```
mysql> INSERT INTO has_user VALUES ('hdfs', 'test');
```

## Config HAS server 
Example:
```
export mysqlUrl=jdbc:mysql://127.0.0.1:3306/has
export mysqlUser=root
export mysqlPasswd=123456
```

## Config client
Example:
```
export userName=hdfs
export password=test
```
