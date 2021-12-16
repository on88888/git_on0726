
create table Person(
name string,
friends array<string>,
children map<string,int>,
address struct<street:string,city:string,email:string>
)
--各字段之间用什么分割开
row format delimited fields terminated by ','
--集合数据类型各数据之间按照什么分割开
collection items terminated by '_'
--map数据类型k,v是用什么分割开的
map keys terminated by ':'
--每条数据是用什么分割开的。默认是换行符
lines terminated by '\n';



