package dataExtraction;


/**
 * @author Nikhil Patil <patilnikhils19@gmail.com>
 * Apr 13, 2017
 * MainClass.java
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapperfirst extends Mapper<LongWritable, Text, Text, Text>{
	public static List<String> tags = new ArrayList<String>();
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String page = value.toString();
		String[] line = page.split("\n");
		String firstLine = line[0];
		String[] attributes = firstLine.split(" ");
		String articleId = attributes[1];
		String revId = attributes[2];
		String articleTitle = attributes[3];
		String timeStamp = attributes[4];
		String userName= attributes[5];
		String userId= attributes[6];
		String time = timeStamp.split("-")[0];
		
		context.write(new Text(articleId), new Text(revId+" "+articleTitle+" "+time+" "+userName+" "+userId));
		
	}
	
}
