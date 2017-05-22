
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
	    
	    
	    Job job1=Job.getInstance(conf);
		job1.setJarByClass(MainClass.class);
		job1.setMapperClass(MapperTwo.class);
		job1.setReducerClass(ReducerTwo.class);
		job1.setOutputKeyClass(Text.class);
		//job1.setOutputValueClass(Text.class);
		//job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.setInputPaths(job1, new Path(OUT_PATH1));
		FileOutputFormat.setOutputPath(job1, new Path(OUT_PATH2));
		if (job1.waitForCompletion(true)) System.out.println("Job Two Completed");
		
	    Job job2=Job.getInstance(conf);
		job2.setJarByClass(MainClass.class);
		job2.setMapperClass(MapperThree.class);
		job2.setReducerClass(ReducerThree.class);
		job2.setOutputKeyClass(Text.class);
		//job1.setOutputValueClass(Text.class);
		//job1.setInputFormatClass(TextInputFormat.class);
		job2.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.setInputPaths(job2, new Path(OUT_PATH2));
		FileOutputFormat.setOutputPath(job2, new Path(args[1]));
		if (job2.waitForCompletion(true)) System.out.println("Job Three Completed");
	    
	}
	
}