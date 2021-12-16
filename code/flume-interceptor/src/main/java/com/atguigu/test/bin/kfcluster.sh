#! /bin/bash
if (($#==0)); then
  echo -e "请输入参数：\n start  启动kafka集群;\n stop  停止kafka集群;\n" && exit
fi

case $1 in
  "start")
    for host in hadoop102 hadoop103 hadoop104
      do
        echo "==================== $1 $host 的kafka ===================="
        #-daemon  （后台启动）
        ssh $host "/opt/module/kafka/bin/kafka-server-start.sh -daemon /opt/module/kafka/config/server.properties"
      done
      ;;
  "stop")
    for host in hadoop102 hadoop103 hadoop104
      do
        echo "==================== $1 $host 的kafka ===================="
        ssh $host "/opt/module/kafka/bin/kafka-server-stop.sh /opt/module/kafka/config/server.properties"
      done
      ;;
  "status")
    for host in hadoop102 hadoop103 hadoop104
      do
        echo "==================== $host ===================="
        # kafka服务启动后的进程最后有 server.properties 这个关键词
        pid=$(ssh $host "ps -ef | grep server.properties | grep -v grep")
        # 查看关键词进程
        # echo "$pid"
        [ "$pid" ] && echo "kafka进程正常" || echo "kafka进程不存在或异常"
      done
      ;;
    *)
        echo -e "---------- 请输入正确的参数 ----------\n"
        echo -e "start  启动kafka集群;\n stop  停止kafka集群;\n" && exit
      ;;
esac
