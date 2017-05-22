
package dataExtraction;

/**
 * @author Nikhil Patil <patilnikhils19@gmail.com>
 * Apr 13, 2017
 * MapperTwo.java
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MapperTwo extends Mapper<LongWritable, Text, Text, Text>{
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
	String page = value.toString();
	String[] attributes = page.split(" ");
	String articleId = attributes[0];
	String revId = attributes[1];
	String articleTitle = attributes[2];
	String timeStamp = attributes[3];
	String userName= attributes[4];
	String userId= attributes[5];
	String editCount= attributes[6];
	String totalEditCount= attributes[7];
	
	context.write(new Text(userId), new Text(articleId+" "+revId+" "+articleTitle+" "+timeStamp+" "+userName+" "+editCount+" "+totalEditCount));
	}

}
