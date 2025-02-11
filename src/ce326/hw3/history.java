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

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'_'HH:mm:ss");
		time = dateFormat.format(new Date());	// get the time each time
		
		// Write JSON to a file, keep the name in a string
		// dont need to keep these informations in the json just add date time level and winner in the title of the file json
		String JsonName = "connect4/"+time.toString()+" L: "+difficulty +" W: "+winner +".json";
		
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
