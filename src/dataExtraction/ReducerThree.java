/**
 * 
 */
package dataExtraction;

/**
 * @author Nikhil Patil <patilnikhils19@gmail.com>
 * Apr 15, 2017
 * ReducerThree.java
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class ReducerThree extends Reducer<Text, Text, Text, Text>{
	
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		for (Text value : values) {
			String line = value.toString();
			count++;
			context.write(new Text(count+","+line), new Text(""));			
		}

}
}