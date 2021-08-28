package ZigZagAlgorithmGeneric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



public class test3 {
	
	
	
	public static List<Track> anomaly(List<Track> result) throws ParseException {
		
		List<Track> result2 = new ArrayList<Track>();

		ZigZagImpl2 algo = new ZigZagImpl2(result);
		
		for(int i = 0; i < result.size() - 2; i++ ) {
			boolean b = algo.isAnomaly(result.get(i), result.get(i + 1), result.get(i + 2), (long) 4320, 0.70);
			if(!b) {
				result2.add(result.get(i));
				System.out.println("added: " + result.get(i).getVolume());

			}else {
				System.out.println("skipped : " + " tarck: " + result.get(i + 1) + " tarck: " +  result.get(i + 2));
				result.remove(i+1);
			}
			
		}
		
		
		//check the last point
		boolean b2 = algo.isAnomaly(result.get(result.size() - 3), result.get(result.size() - 2), result.get(result.size() - 1), (long) 480, 0.70);

		if(!b2) {
			result2.add(result.get(result.size() - 2));
			System.out.println("added: " + result.get((result.size() - 3)).getVolume());

		}else {
			System.out.println("skipped : " + " tarck: " + result.get(result.size() - 3) + " tarck: " +  result.get(result.size() - 2));
			result.remove(result.size() - 2);
		}
		result2.add(result.get(result.size() - 1));
		
		return result2;
		
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		List<Track> result = new ArrayList<Track>();
		List<Track> result2 = new ArrayList<Track>();
		List<Track> result3 = new ArrayList<Track>();
		List<Track> result4 = new ArrayList<Track>();

		List<Track> test = new ArrayList<Track>();

		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		ArrayList<Track> list = mapper.readValue(Paths.get("data2.json").toFile(), mapper.getTypeFactory().constructCollectionType(List.class, Track.class));
		
		System.out.println(" -----------------  data brute : -----------------------");
		System.out.println("");
		
		for(Track i : list) {
		System.out.print(" volume : " + i.getVolume() + "\t");

		//System.out.print("Track: " +  " volume : " + i.getVolume()  + " date : " + i.getTime() + " --- type:  ---" + i.getType());
	}
	

		
		System.out.println(" -----------------  algo start filtring : -----------------------");
		ZigZagImpl2 algo = new ZigZagImpl2(list);
		
		
		//check if we removed the stable values 
		test = algo.StableValuesFilter(list);
		
		/*for(Track i : test) {
			System.out.print(" volume : " + i.getVolume() + "\t");

			//System.out.print("Track: " +  " volume : " + i.getVolume()  + " date : " + i.getTime() + " --- type:  ---" + i.getType());
		}*/
		
		//System.out.println(test.get(0).getTime());
		
		
		

		
		/*for(Track i : algo.tracks) {
			System.out.println("Track: " +  " volume : " + i.getVolume()  + " date : " + i.getTime() + " --- type:  ---" + i.getType());
		}*/

		
		
		
		result = algo.extremumsFinder(0.70, 4320);
		
		System.out.println(" ----------------- zigzag filtred data : -----------------------");

		for(Track i : result) {
		System.out.println("Track: " +  " volume : " + i.getVolume()  + " date : " + i.getTime() + " --- type:  ---" + i.getType());
	}

		
		
		//ZigZagImpl2 algo2 = new ZigZagImpl2(result);
		//result2 = algo2.extremumsFinder(0.80);

		System.out.println(" -----------------  result calculation: -----------------------");

		System.out.println(" -----------calculated volume:------:  " + algo.calculateVolume(result) );
		
		

		
		
		System.out.println("");
		
		/*for(Track i : result2) {
			//System.out.print(" volume : " + i.getVolume() + "\t");

			System.out.println("Track: " +  " volume : " + i.getVolume()  + " date : " + i.getTime() + " --- type:  ---" + i.getType());
		}*/
		
		
		
		/*for(int i = 0; i < result.size() - 2; i++ ) {
			boolean b = algo.isAnomaly(result.get(i), result.get(i + 1), result.get(i + 2), (long) 4320, 0.70);
			if(!b) {
				result2.add(result.get(i));
				System.out.println("added: " + result.get(i).getVolume());

			}else {
				System.out.println("skipped : " + " tarck: " + result.get(i + 1) + " tarck: " +  result.get(i + 2));
				result.remove(i+1);
			}
			
		}
		
		
		//check the last point
		boolean b2 = algo.isAnomaly(result.get(result.size() - 3), result.get(result.size() - 2), result.get(result.size() - 1), (long) 480, 0.70);

		if(!b2) {
			result2.add(result.get(result.size() - 2));
			System.out.println("added: " + result.get((result.size() - 3)).getVolume());

		}else {
			System.out.println("skipped : " + " tarck: " + result.get(result.size() - 3) + " tarck: " +  result.get(result.size() - 2));
			result.remove(result.size() - 2);
		}
		result2.add(result.get(result.size() - 1));
		
		System.out.println("last value :" + result.get(result.size() - 1).getVolume());*/

		//result2 = algo.anomaliesFilter(result, );

		/*for(Track i : result2) {
		System.out.println("Track: " +  " volume : " + i.getVolume()  + " date : " + i.getTime() + " --- type:  ---" + i.getType());
	}*/
		
		
		//result4= algo.anomalyFilter(result, (long) 480, 70);
		
		/*for(Track i : result4) {
		System.out.println("Track: " +  " volume : " + i.getVolume()  + " date : " + i.getTime() + " --- type:  ---" + i.getType());
	}*/
		
		
		
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(df);
		
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File("r.json"), result2);
       // mapper.writeValue(new File("book.json"), test);

		

	}

}
