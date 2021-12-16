create external table if not exists dept(
    deptno int,
    dname string,
    loc int
)
row format delimited
    fields terminated by '\t';

create external table if not exists emp(
   empno int,
   ename string,
   job string,
   mgr int,
   hiredate string,
   sal double,
   comm double,
   deptno int)
row format delimited fields terminated by '\t';

-- 查询全表
select * from emp;
select empno,ename,job,mgr,hiredate,sal,comm,deptno from emp;

--- 查询特定列
select deptno,ename, sal from emp;

-- 列的别名
select ename, sal+comm  from emp;
select ename, sal+comm  income  from emp;
select ename, sal+(nvl(comm, 0)) income from emp;


-- 常用函数
select deptno,count(*) from emp group by deptno;

select max(sal) from emp;
select max(sal + nvl(comm, 0)) from emp;

select min(sal) from emp;
select * from emp where sal = 800;

select sum(sal) from emp;
select deptno, sum(sal) from emp group by deptno;

select deptno, avg(sal) from emp group by deptno;


--- limit
select ename, sal from emp order by  sal desc;
select ename, sal from emp order by  sal desc limit 3;
select ename, sal from emp order by  sal desc limit 3,3;

-- where
select ename,sal from emp where sal >1000;

--- 比较运算符
select ename, mgr, comm , mgr <=> comm flage from emp;
select ename, mgr, comm , mgr <> comm flage from emp;
select ename, sal from emp where sal between 2000 and 3000;

select ename ,comm from emp where comm is null;


-- 逻辑运算符
select ename,sal,deptno from emp where sal >1000 and deptno=30;
select ename,sal,deptno from emp where sal >1000 or deptno=30;
select * from emp where deptno != 20 and deptno != 30;
select * from emp where deptno not in(20,30);

--- group by
select deptno, avg(sal) from emp group by deptno;
select  deptno, max(sal) from emp group by deptno;
select  deptno, min(sal) from emp group by deptno;

---
select deptno, max(sal) max_sal from emp group by deptno having max_sal >3000;

select * from (
                  select  deptno, max(sal) max_sal from emp group by deptno
                  ) tmp
where max_sal > 3000;


--- join
-- 内连接：只有进行连接的两个表中都存在与连接条件相匹配的数据才会被保留下来。
select
    d.deptno,
    e.ename,
    e.sal,
    d.dname
from emp e
join dept d on e.deptno = d.deptno;
-- 左外连接：JOIN操作符左边表中符合WHERE子句的所有记录将会被返回。
select
    e.deptno,
    e.ename,
    e.sal,
    d.dname
from emp e
left join dept d on e.deptno = d.deptno;
-- 仅保留作表特有的值：在左外连接基础上经过过滤得到
select
    e.deptno,
    e.ename,
    e.sal,
    d.dname
from emp e
left join dept d on e.deptno = d.deptno
where d.dname is null;

--右外连接：JOIN操作符右边表中符合WHERE子句的所有记录将会被返回
select
    d.deptno,
    e.ename,
    e.sal,
    d.dname
from emp e
right join dept d on e.deptno = d.deptno;

--查询右表中特有的数据：在右外连接基础上过滤ename为null数据
select
    d.deptno,
    e.ename,
    e.sal,
    d.dname
from emp e
right join dept d on e.deptno = d.deptno
where e.ename is null;


-- 满外连接：将会返回所有表中符合WHERE语句条件的所有记录。
-- 如果任一表的指定字段没有符合条件的值的话，那么就使用NULL值替代。
select
    nvl(d.deptno, e.deptno),
    e.ename,
    e.sal,
    d.dname
from emp e
full join dept d on e.deptno = d.deptno;

--- 查询两个表中特有的数据:在满外连接基础上进行过滤
select
    nvl(d.deptno, e.deptno),
    e.ename,
    e.sal,
    d.dname
from emp e
full join dept d on e.deptno = d.deptno
where e.ename is null or d.dname is null;


--- 多表连接

create table if not exists location(
   loc int,
   loc_name string
)
    row format delimited fields terminated by '\t';

--- 多表连接

select
    d.dname,
    e.ename,
    l.loc_name
from emp e
join dept d on d.deptno = e.deptno
join location l on d.loc = l.loc;


--- 笛卡尔积
select
    *
from dept d
, location l ;


--- 排序

-- order by

--- 查询员工name按工资升序排列
select
    ename,
    sal
from emp
order by sal;

select
    ename,
    sal
from emp
order by sal desc ;


--多列排序
select
    deptno,
    ename,
    sal
from emp
order by deptno,sal;


--- sort by
--- 根据部门编号降序查看员工信息
select
    deptno,
    ename,
    sal
from emp
sort by deptno desc ;


---- Distribute By
insert overwrite local directory
    '/opt/module/hive/datas/distribute-result'
select
    deptno,
    ename,
    sal
from emp distribute by deptno sort by sal desc;

--- cluster by

insert overwrite local directory
    '/opt/module/hive/datas/distribute-result'
select
    deptno,
    ename,
    sal
from emp cluster by deptno;
