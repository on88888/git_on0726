#! /bin/bash
for host in hadoop102 hadoop103
do
	echo "====================开始生成$host的日志数据======================"
	#&: 后台执行,ssh断开的时候,程序会终止
	#nohup: 免挂断,ssh断开的时候,程序不会终止
	#0<: 标准输入 
	#1>: 标准输出
	#2>: 错误输出
	#/dev/null:黑洞（垃圾堆）
	#ssh远程使用nohup指令的时候必须使用标准输出与错误输出
	ssh $host "cd /opt/module/applog;nohup java -jar gmall2020-mock-log-2021-01-22.jar 1>/dev/null 2>&1 &"
done
