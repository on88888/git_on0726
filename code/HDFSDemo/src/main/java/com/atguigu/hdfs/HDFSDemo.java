package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class HDFSDemo {
    FileSystem fs;

    @Before
    public void before() throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("hdfs://hadoop102:9820");
        Configuration conf = new Configuration();
        String user = "atguigu";
        fs = FileSystem.get(uri, conf, user);
    }

    @After
    public void after() {
        try {
            if (fs != null)
                fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() throws IOException {
        //上传   copyFromLocalFile：从本地文件复制
        fs.copyFromLocalFile(false, true, new Path("D:\\io\\hdfs\\a.zip"), new Path("/"));
        /*
        copyFromLocalFile(boolean delSrc, boolean overwrite,
                                Path src, Path dst)
           delSrc : 是否删除源文件（本地文件）
           overwrite : 如果HDFS上已经存在是否覆盖。
                如果不覆盖，那么在已经存在的情况下会报错。
            src : 源文件（本地）
            dst : 目标路径（HDFS）
         */
    }

    @Test
    public void test2() throws IOException {
        //下载    copyToLocalFile：复制到本地文件
        fs.copyToLocalFile(false, new Path("/a/b/Shell.txt"), new Path("D:\\io\\hdfs"), false);
         /*
        copyToLocalFile(boolean delSrc, Path src, Path dst,
                                boolean useRawLocalFileSystem)
           delSrc : 是否删除源文件（HDFS）
           src : 源文件（HDFS）
           dst : 目标路径（本地）
           useRawLocalFileSystem ： 是否使用RawLocalFileSystem
                true : 不会生成crc文件
                false : 会生成crc文件
         */
    }

    @Test
    public void test3() throws IOException {
        // 文件删除
        fs.delete(new Path("/a.txt"), false);
        /*
        delete(Path f, boolean recursive)
        f : 文件或目录的路径
        recursive ： 如果是文件true和false都可以。如果是目录必须为true否则报错。
                注意：如果是空目录true和false也都可能。
         */
    }

    @Test
    public void test4() throws IOException {
        //文件名更改或移动文件
        //更改名字
        fs.rename(new Path("/a/b/liubei.txt"), new Path("/a/b/longge.txt"));
        //移动文件
        //fs.rename(new Path("/liubei.txt"), new Path("/a/b"));
         /*
        rename(Path src, Path dst)
        src : 源文件
        dst : 目标文件或路径
         */
    }

    @Test
    public void test5() throws IOException {
        //文件详情查看
        /*
        listFiles(final Path f, final boolean recursive)
        f : 目标路径
        recursive ： 是否递归
         */
        //RemoteIterator ： 迭代器
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        //遍历
        while (listFiles.hasNext()) {
            LocatedFileStatus file = listFiles.next();
            System.out.println("===========================文件名:" + file.getPath().getName() + "==================");
            System.out.println("所属主:" + file.getOwner());
            System.out.println("副本数:" + file.getReplication());
            //获取文件的块信息
            BlockLocation[] blockLocations = file.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));
        }
    }


}
