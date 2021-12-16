--- DDL

---数据库

--- 创建
create database bigdata
location '/bigdata.db'
with dbproperties ('key'='value')

-- 查询
show databases;
show databases like 'd.*';
desc database bigdata;
use default;
-- 修改
alter database bigdata set dbproperties ('key1'='value1');

-- 删除
drop database bigdata;
--不能删除非空数据库
-- 强制删除 cascade
drop database bigdata cascade ;


--- 表

--- 创建
create table student(
    stu_id int,
    stu_name string
)
-- 分区
-- 分桶
-- row format |row format delimited | SerDe
row format delimited
    fields terminated by '\t'
    collection items terminated by '_'
    map keys terminated by ':'
    lines terminated by '\n'
stored as textfile
location '/user/hive/warehouse/bigdata.db';
-- as select_statment
--like table_exists;

--- 修改表
-- 更新
alter table student change [column ] col_old col_new int   [first| after column ]
-- 增加和替换
alter table student add columns (col_name int);
alter table student replace columns (col_name int);

--删除
drop table student;
truncate table student

---- DML
--- 导入
insert into/overwrite
load data [local] inpath
hadoop dfs -put
import  (export)
create table student as select * from student2;
-- 导出
insert overwrite [local] directory
hadoop dfs -get
hive -e "select * from student" > /opt/module/hive/data/hive.log
export
sqoop





