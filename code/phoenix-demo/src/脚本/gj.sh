#!/bin/bash
if [ $# -lt 1 ]
 then 
   echo "NO Args Input Error!!!"
   exit     
fi          
case $1 in  
"shutdown") 
  echo "===========shutdown now============"
  ssh -t hadoop104 "echo \"123456\" | sudo -S shutdown -h now"
  ssh -t hadoop103 "echo \"123456\" | sudo -S shutdown -h now"
  ssh -t hadoop102 "echo \"123456\" | sudo -S shutdown -h now"
;;  
"reboot")
  echo "=========reboot============="
  ssh -t hadoop104 "echo \"123456\" | sudo -S reboot"
  ssh -t hadoop103 "echo \"123456\" | sudo -S reboot"
  ssh -t hadoop102 "echo \"123456\" | sudo -S reboot"
;;
*)
echo "INput args Error!! 关机：shutdown  重启：reboot"
;;
esac
