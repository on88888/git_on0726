
--- 集合数据类型案例
-- 创建personinfo表
create table peroninfo(
    name string,
    friends array<string>,
    children map<string,int>,
    addr   struct<street:string, city:string>
)
row format delimited
fields terminated by ','
collection items terminated by '_'
map keys terminated by ':'
lines terminated by '\n';
