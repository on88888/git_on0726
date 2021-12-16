#! /bin/bash

# 判断参数
if (( $# == 0 ));then
  echo -e "请输入参数：\n start 开启kafka；\n stop 关闭kafka;"&&exit
fi

# case start、stop
case $1 in
"start")
  for host in hadoop103 hadoop102 hadoop104
    do
        echo "---------- $1 $host 的 kafka ----------"
        ssh $host "/opt/module/kafka/bin/kafka-server-start.sh -daemon /opt/module/kafka/config/server.properties"
    done
  ;;
"stop")
  for host in hadoop103 hadoop102 hadoop104
    do
        echo "---------- $1 $host 的 kafka ----------"
        ssh $host "/opt/module/kafka/bin/kafka-server-stop.sh  /opt/module/kafka/config/server.properties"
    done
  ;;
*)
  echo "----- 请输入正确的参数 -----"
  echo -e "请输入参数：\n start 开启kafka；\n stop 关闭kafka;"
;;
esac
