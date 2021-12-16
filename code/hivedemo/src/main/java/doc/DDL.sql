--- 1. 数据库
-- 创建
-- 查询
-- 修改
-- 删除

create database bigdata;
create database if not exists bigdata2;
create database if not exists bigdata3
location '/bigdata3.db';

desc database bigdata2;
desc database extended bigdata2;

alter database bigdata3
    set dbproperties('createTime'='20210922');

drop database bigdata2;
drop database if  exists bigdata2;
drop database if  exists bigdata3 cascade ;


--2. 表
-- 创建
-- 修改
-- 删除
create table student(
    id int,
    name string
)
row format delimited
fields terminated by '\t'
stored as textfile
location '/user/hive/warehouse/student';


create table student2
    as select * from student where id <=1005;

create table student3
like student;


create external table student4(
    id int,
    name string
)
    row format delimited
        fields terminated by '\t'
location '/student4';


-- 重命名表
alter table student4 rename to student5;


test2---> insert into --->count(*)-->2

test3---> hadoop dfs -put ---> count(*) --> 0

test4 --> load  ----> count(*) --> MR+4
