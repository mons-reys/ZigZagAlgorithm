package jacksonTest;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ZigZagAlgorithmGeneric.Tra;
import ZigZagAlgorithmGeneric.Track;



public class Test {

	public static void main(String[] args) throws IOException, ParseException {
		
		
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


		List<Tra> myObjects = mapper.readValue(Paths.get("hh.json").toFile(), mapper.getTypeFactory().constructCollectionType(List.class, Tra.class));
		
		
	
	
	

    
		for(Tra i : myObjects) {
			System.out.println(i.getVolume());
			System.out.println(i.getDate());
		}

	}

}
