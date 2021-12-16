--- 1. 分区

--- 1.1 创建一个分区表
create table dept_partition(
   deptno int,
   dname string,
   loc string
)
partitioned by (day string)
row format delimited fields terminated by '\t';


--- 向分区表加载数据
load data local inpath
'/opt/module/hive/data/dept_20210923.log'
into table dept_partition
partition (day='20210923');

insert into table dept_partition
partition (day='20210924')
select * from dept where deptno in (30,40);


--- 二级分区表
create table dept_partition2(
    deptno int,
    dname string,
    loc string
)
    partitioned by (day string, hour string)
    row format delimited fields terminated by '\t';
--- 向20210923-11
load data local inpath
'/opt/module/hive/data/dept_20210923.log'
into table dept_partition2
partition (day='20210923',hour='11');

load data local inpath
    '/opt/module/hive/data/dept_20210924.log'
    into table dept_partition2
    partition (day='20210923',hour='12');

-- 方式一：上传数据后修复
msck repair table dept_partition2;

-- 方式二：上传数据后添加分区
alter table dept_partition2 add partition(day='20210923',hour='14');

--- 方式三：创建文件夹后load数据到分区


-- 动态分区

-- 创建一个动态分区表
create table dept_partition_dy(
                                  id int,
                                  name string
)
    partitioned by (loc int)
    row format delimited fields terminated by '\t';

insert into table dept_partition_dy
    partition(loc)
    select deptno, dname, loc from dept;



--- 2. 分桶表

-- 2.1 创建分桶表
create table stu_bucket(id int, name string)
    clustered by(id)
        into 4 buckets
    row format delimited fields terminated by '\t';


--- -2.2 加载数据
load data local inpath '/opt/module/hive/data/student.txt'
into table stu_bucket;
