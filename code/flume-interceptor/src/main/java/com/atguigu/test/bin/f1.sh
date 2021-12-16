#! /bin/bash
#1、判断参数是否传入
if [ $# -lt 1 ]
then 
	echo "必须传入参数start/stop"
	exit
fi
#2、根据参数匹配逻辑
case $1 in
"start")
	for host in hadoop102 hadoop103
	do
	  echo "===============start $host file to kafka================"
		ssh $host "nohup /opt/module/flume/bin/flume-ng agent -n a1 -c /opt/module/flume/conf/ -f /opt/module/flume/job/file_to_kafka.sh -Dflume.root.logger=INFO,LOGFILE >/opt/module/flume/logs.txt 2>&1 &"
	done
;;
"stop")
	for host in hadoop102 hadoop103
	do
	  echo "===============stop $host file to kafka================"
		ssh $host "ps -ef |grep file_to_kafka.sh |grep -v grep |awk '{print \$2}'|xargs kill -9"
	done
;;
*)
	echo "参数输入错误....."
	echo "必须传入参数start/stop"
;;
esac
