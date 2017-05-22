
package dataExtraction;

/**
 * @author Nikhil Patil <patilnikhils19@gmail.com>
 * Apr 13, 2017
 * MainClass.java
 */

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class MainClass {
	
	private static final String OUT_PATH1="OUTPUTJOB1-EditCount";
	private static final String OUT_PATH2="OUTPUTJOB2-Final";
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
		
		Configuration conf = new Configuration();

	    Job job = new Job(conf);
	    job.setJarByClass(MainClass.class);
	    job.setJobName("MyParser");

	    job.setInputFormatClass(WikiPageFormat.class);
	    
	    job.setMapperClass(Mapperfirst.class);
	    job.setReducerClass(Reducerfirst.class);
	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(OUT_PATH1));
	    
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);
	    if (job.waitForCompletion(true)) System.out.println("Job One Completed");
	    
	    
	}
	
}