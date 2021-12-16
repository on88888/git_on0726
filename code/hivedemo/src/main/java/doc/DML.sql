--- 1. 数据导入

--- 1.1 insert into/overwrite table values()/select * from table;

--- 1.2 hadoop dfs -put

--- 1.3 load data [local ] inpath '/opt/module/....' [overwrite] into table test2;

---- 1.4 as select_...__> create table test5 as select * from test3;

--- 1.5 create table test6 (id string) location '/input/';

--- 1.6 import 未完待续
import table student2_2 from '/output/export';



from test1
insert into table test2
select id  where id = '1001'
insert into table test3
select id  where id = '1002';




--- 2. 数据导出

--- 2.1 insert
insert overwrite  local directory
'/opt/module/hive/data/export/student/'
select distinct *  from student;

insert overwrite  local directory
    '/opt/module/hive/data/export/student/'
row format delimited fields terminated by ','
select distinct *  from student;

--- 2.2 -get

hadoop dfs -get /user/hive/warehouse/test3/000000_1

--- 2.3 hive shell

hive -e "select * from test4" > /test4.txt;

--- 2.4 export
export table student2 to '/output/export';
