-- 创建本地person_info.txt，导入数据

-- 孙悟空,白羊座,A
-- 大海,射手座,A
-- 宋宋,白羊座,B
-- 猪八戒,白羊座,A
-- 凤姐,射手座,A
-- 苍老师,白羊座,B

create table person_info(
    name string,
    constellation string,
    blood_type string
)
row format delimited
fields terminated by  ",";
-- 导入数据
-- load data local '/opt/module/hive/datas/person_info.txt' into table person_info;

-- 需求
-- 把星座和血型一样的人归类到一起。结果如下：
-- 射手座,A            大海|凤姐
-- 白羊座,A            孙悟空|猪八戒
-- 白羊座,B            宋宋|苍老师

select
    name,
    concat_ws(",",constellation,blood_type) c_b
from person_info;
-- name 	c_b
-- 孙悟空	白羊座,A
-- 大海	    射手座,A
-- 宋宋	    白羊座,B
-- 猪八戒	白羊座,A
-- 凤姐	    射手座,A
-- 苍老师	白羊座,B

-- 方式一
select
    t1.c_b `星座与血型`,
    concat_ws("|",collect_set(t1.name)) `姓名`
--     COLLECT_SET(col)：函数只接受基本数据类型，它的主要作用是将某字段的值进行去重汇总，产生array数组类型字段。
from(
        select
            name,
            concat_ws(",",constellation,blood_type) c_b
        from person_info
        ) t1
group by t1.c_b;
-- 星座与血型	姓名
-- 射手座,A	    大海|凤姐
-- 白羊座,A	    孙悟空|猪八戒
-- 白羊座,B	    宋宋|苍老师

-- 方式二
select
    t1.c_b `星座与血型`,
    concat_ws("|",collect_list(name)) `姓名`
--     COLLECT_LIST(col)：函数只接受基本数据类型，它的主要作用是将某字段的值进行不去重汇总，产生array类型字段。
from(
        select
            name,
            concat_ws(",",constellation,blood_type) c_b
        from person_info
    ) t1
group by t1.c_b;
-- 星座与血型	姓名
-- 射手座,A	    大海|凤姐
-- 白羊座,A	    孙悟空|猪八戒
-- 白羊座,B	    宋宋|苍老师