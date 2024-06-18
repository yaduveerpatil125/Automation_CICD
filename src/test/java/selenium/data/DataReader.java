package selenium.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		//read the json file as String input
		String jsonContent = FileUtils.readFileToString(new File("src\\test\\java\\selenium\\data\\loginCredentials.json"),
				StandardCharsets.UTF_8);
		
		//convert String to Hashmap using Jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		
		//the below command gives us List of hashMaps
		List<HashMap<String,String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>() {
		});
		
		return data;

			
		}
		
		
	}
