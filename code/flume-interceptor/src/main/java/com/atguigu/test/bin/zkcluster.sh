#!/bin/bash

if [ $# -ne 1 ]
	then
		echo -e "--------------请输入参数---------------"
		echo -e "start  启动zookeeper集群;\nstatus 查看zookeeper集群状态;\nstop   停止zookeeper集群;\n"
		exit
fi

var=""

case $1 in
"start")
	var="start"
	;;
"stop")
	var="stop"
	;;
"status")
	var="status"
	;;
*)
        echo -e "---------- 请输入正确的参数 ----------"
        echo -e "start  启动zookeeper集群;\nstatus 查看zookeeper集群状态;\nstop   停止zookeeper集群;\n" && exit

	;;
esac
	
	
for host in hadoop102 hadoop103 hadoop104
do
	echo "==========================$1 $host 的zookeeper======================"
	ssh $host /opt/module/zookeeper/bin/zkServer.sh $var
done
