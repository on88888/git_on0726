package com.atguigu.mr.mapjoin10;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MJMapper extends Mapper<LongWritable, Text,OrderBean, NullWritable> {
    //用来缓存pd.txt    key：pid   value:pname
    private Map<Long,String> pdMap = new HashMap<Long,String>();
    /*
        任务开始时执行一次。在map方法执行前执行
        缓存pd.txt
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        BufferedReader br = null;
        FileSystem fs = null;
        try {
            //1.创建文件系统对象
            fs = FileSystem.get(context.getConfiguration());
            //获取缓存文件的路径
            URI[] cacheFiles = context.getCacheFiles();
            //2.创建流
            FSDataInputStream fis = fs.open(new Path(cacheFiles[0]));
            //3.读取数据（一行一行读（字符缓冲流））
            br = new BufferedReader(new InputStreamReader(fis,"utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                //将该行内容切割
                String[] split = line.split("\t");
                //将内容放入map中
                pdMap.put(Long.parseLong(split[0]), split[1]);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }finally {
            IOUtils.closeStream(br);
            //关闭资源
            if (fs != null){
                fs.close();
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        //切割数据
        String[] split = line.split("\t");
        //封装K,V
        OrderBean orderBean = new OrderBean(Long.parseLong(split[0]), Long.parseLong(split[1]),
                Long.parseLong(split[2]), pdMap.get(Long.parseLong(split[1])));
        //写出K,V
        context.write(orderBean,NullWritable.get());
    }
}
