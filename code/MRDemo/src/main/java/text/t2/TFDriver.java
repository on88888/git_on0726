package text.t2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TFDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);


        job.setJarByClass(TFDriver.class);

        job.setMapperClass(TFMapper.class);
        job.setReducerClass(TFReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TFBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(TFBean.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\io\\input2"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\io\\output2"));

        job.waitForCompletion(true);

    }
}
