/**
 * 
 */
package dataExtraction;


/**
 * @author Nikhil Patil <patilnikhils19@gmail.com>
 * Apr 13, 2017
 * Reducerfirst.java
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reducerfirst extends Reducer<Text, Text, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		Map<String, Integer> data = new HashMap<String, Integer>();
		int count = 0;
		for (Text value : values) {
			count++;
			String line = value.toString();
			data.put(line, count);
		}
		for(String lineData : data.keySet() ){
			
			context.write(new Text(key+" "+lineData+" "+data.get(lineData)+" "+count), new Text(""));
			
		}
	}
}
