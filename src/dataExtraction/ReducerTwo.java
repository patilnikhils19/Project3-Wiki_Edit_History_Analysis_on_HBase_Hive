/**
 * 
 */
package dataExtraction;

/**
 * @author Nikhil Patil <patilnikhils19@gmail.com>
 * Apr 13, 2017
 * ReducerTwo.java
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class ReducerTwo extends Reducer<Text, Text, Text, Text>{
	
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		for (Text value : values) {
			count++;
			String page = value.toString().replaceAll(",", "");
			String[] attributes = page.split(" ");
			String articleId = attributes[0];
			String revId = attributes[1];
			String articleTitle = attributes[2];
			String timeStamp = attributes[3];
			String userName= attributes[4];
			String editCount= attributes[5];
			String totalEditCount= attributes[6];
			context.write(new Text(articleId+","+key.toString()+","+revId+","+articleTitle+","+timeStamp+","+userName+","+editCount+","+count+","+totalEditCount), new Text(""));
			
		}

}
}