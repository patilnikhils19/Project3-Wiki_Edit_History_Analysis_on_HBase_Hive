/**
 * 
 */
package dataExtraction;

/**
 * @author Nikhil Patil <patilnikhils19@gmail.com>
 * Apr 15, 2017
 * MapperThree.java
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MapperThree extends Mapper<LongWritable, Text, Text, Text>{
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
	String page = value.toString();
	context.write(new Text("one"), new Text(page));
	}

}