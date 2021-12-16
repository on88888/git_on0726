package com.atguigu.mr.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CompressDemo {

    @Test
    public void test() throws IOException {

        FileInputStream fis = new FileInputStream("D:\\io\\compress\\hello.txt");
        CompressionCodec gzipCodec = ReflectionUtils.newInstance(GzipCodec.class, new Configuration());
        CompressionOutputStream cos = gzipCodec.createOutputStream(new FileOutputStream("D:\\io\\decompress\\hello.txt" + gzipCodec.getDefaultExtension()));
        IOUtils.copyBytes(fis, cos, 1024, true);

    }

    @Test
    public void test2() throws IOException {

        CompressionCodec gzipCodec = ReflectionUtils.newInstance(GzipCodec.class, new Configuration());
        CompressionInputStream cis = gzipCodec.createInputStream(new FileInputStream("D:\\io\\decompress\\aaa.txt.gz"));
        FileOutputStream fos = new FileOutputStream("D:\\io\\decompress\\aaa.txt");
        IOUtils.copyBytes(cis, fos, 1024, true);

    }

    @Test
    public void test3() throws IOException {

        CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());
        CompressionCodec codec = factory.getCodec(new Path("D:\\io\\decompress\\aaa.txt.gz"));
        CompressionInputStream cis = codec.createInputStream(new FileInputStream("D:\\io\\decompress\\aaa.txt.gz"));
        FileOutputStream fos = new FileOutputStream("D:\\io\\decompress\\aaa.txt");
        IOUtils.copyBytes(cis, fos, 1024, true);

    }

}
