-- 创建本地movie_info.txt，导入数据

-- 《疑犯追踪》	悬疑,动作,科幻,剧情
-- 《Lie to me》悬疑,警匪,动作,心理,剧情
-- 《战狼2》	    战争,动作,灾难

create table movie_info(
    movie string,
    category string
)
row format delimited
fields terminated by '\t';

-- 导入数据
-- load data local inpath '/opt/module/hive/datas/movie_info.txt' into table movie_info;

-- 需求
-- 将电影分类中的数组数据展开。结果如下：
-- 《疑犯追踪》      悬疑
-- 《疑犯追踪》      动作
-- 《疑犯追踪》      科幻
-- 《疑犯追踪》      剧情
-- 《Lie to me》   悬疑
-- 《Lie to me》   警匪
-- 《Lie to me》   动作
-- 《Lie to me》   心理
-- 《Lie to me》   剧情
-- 《战狼2》        战争
-- 《战狼2》        动作
-- 《战狼2》        灾难

select
    movie,
    category_name
from movie_info
lateral view explode(split(category,',')) lv as category_name;
--lateral view explode(数组) 虚拟表名 as 炸开的字段名

-- movie	        category_name
-- 《疑犯追踪》	    悬疑
-- 《疑犯追踪》	    动作
-- 《疑犯追踪》	    科幻
-- 《疑犯追踪》	    剧情
-- 《Lie to me》	悬疑
-- 《Lie to me》	警匪
-- 《Lie to me》	动作
-- 《Lie to me》	心理
-- 《Lie to me》	剧情
-- 《战狼2》	        战争
-- 《战狼2》	        动作
-- 《战狼2》	        灾难
