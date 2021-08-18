package jacksonTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ZigZagAlgorithmGeneric.Track;
import ZigZagAlgorithmGeneric.ZigZagImpl2;



public class test3 {
	
	public static void main(String[] args) throws IOException, ParseException {
		List<Track> result = new ArrayList<Track>();

		List<Track> test = new ArrayList<Track>();

		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		ArrayList<Track> list = mapper.readValue(Paths.get("j.json").toFile(), mapper.getTypeFactory().constructCollectionType(List.class, Track.class));
		
		

		ZigZagImpl2 algo = new ZigZagImpl2(list);
		
		test = algo.StableValuesFilter(list);
		
		

		
		/*for(Track i : algo.tracks) {
			System.out.println("Track: " +  " volume : " + i.getVolume()  + " date : " + i.getTime() + " --- type:  ---" + i.getType());
		}*/

		
		
		
		result = algo.extremumsFinder(0.50);
		
		
		System.out.println(algo.calculateVolume(result));
		
		
		System.out.println("size: " + list.size());

		/*for(Track i : test) {
			System.out.print(" volume : " + i.getVolume() + "\t");

			//System.out.print("Track: " +  " volume : " + i.getVolume()  + " date : " + i.getTime() + " --- type:  ---" + i.getType());
		}*/
		
		System.out.println("");
		
		for(Track i : result) {
			//System.out.print(" volume : " + i.getVolume() + "\t");

			System.out.println("Track: " +  " volume : " + i.getVolume()  + " date : " + i.getTime() + " --- type:  ---" + i.getType());
		}


		

	}

}
