#1、定义agent、source、channel的名称
# a1 = agent
a1.sources = r1
a1.channels = c1

#2、描述source
a1.sources.r1.type = TAILDIR
#定义断点续传文件
a1.sources.r1.positionFile = /opt/module/flume/position.json
#定义监控的文件组
a1.sources.r1.filegroups = f1
#制定文件组监控的文件
a1.sources.r1.filegroups.f1 = /opt/module/applog/log/app.*
#定义source每个批次采集的数据
a1.sources.r1.batchSize = 100

#3、描述拦截器[过滤非json数据]
#定义拦截器名称
a1.sources.r1.interceptors = i1
#定义拦截器类型
a1.sources.r1.interceptors.i1.type = com.atguigu.interceptor.ETLInterceptor$Builder

#4、描述channel
a1.channels.c1.type = org.apache.flume.channel.kafka.KafkaChannel
#指定kafka集群地址
a1.channels.c1.kafka.bootstrap.servers = hadoop102:9092,hadoop103:9092
#指定写入的topic的名称
a1.channels.c1.kafka.topic = applog
#指定数据是否以Event数据格式写入kafka
a1.channels.c1.parseAsFlumeEvent = false

#5、关联source->channel
a1.sources.r1.channels = c1

