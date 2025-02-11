package ce326.hw3;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.*;

public class history 
{
	public void writeHistory(JSONObject jsonObject, JSONArray movementsArray , String winner, String time, int level)
	{
		jsonObject.put("movements", movementsArray);
		String difficulty;
		
		if(level == 1)
			difficulty = "Trivial";
		else if (level == 3)
			difficulty = "Medium";
		else if(level == 5)
			difficulty = "Hard";
		else
			difficulty = "Unknown";
		
		//jsonObject.put("Winner ", "AI");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'_'HH:mm:ss");
		time = dateFormat.format(new Date());
		
		// Write JSON to a file
		String JsonName = "connect4/"+time.toString()+" L: "+difficulty +" W: "+winner +".json";
//		String JsonName = "output/"+time.toString()+"_L:Hard" +"_W:AI"+".json";
		
		try (FileWriter fileWriter = new FileWriter(JsonName)) 
		{
		    fileWriter.write(jsonObject.toString(4)); // Indentation of 4 spaces
		} 
		catch (IOException e4) 
		{
		    e4.printStackTrace();
		}
	}
}
