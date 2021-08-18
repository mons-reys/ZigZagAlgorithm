package jacksonTest;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ZigZagAlgorithmGeneric.Tra;
import ZigZagAlgorithmGeneric.Track;
import ZigZagAlgorithmGeneric.TrackType;
import ZigZagAlgorithmGeneric.item;



public class Test {
	
	
	public static List<Double> filter(List<Double> tracks){
		
		List<Double> newItems = new ArrayList<Double>();

		for(int i = 0; i< tracks.size();)
		{
			
			Double pick = tracks.get(i);
	    	System.out.println("val :" + pick);

		    for(i += 1; i < tracks.size() && pick.equals(tracks.get(i)); i++)
		    {
		    	System.out.println("val 2:" + pick);
		        if((pick != tracks.get(i)))
		            pick = tracks.get(i);
		    }
		    newItems.add(pick);
		}
        
		

		return newItems;
	}

	public static void main(String[] args) throws IOException, ParseException {
		
		
		List<Double> list = new ArrayList<Double>();
		List<Double> result = new ArrayList<Double>();

		double arr[] = { 4, 20, 20,20, 16, 30, 30, 30, 30, 20, 12};

		for(double i: arr) {
			
			list.add(i);
		}
		
		result = filter(list);
		
		for(double i: result) {
			System.out.print(i + " \t ");
		}
		
		
		
		
		
	
	}

}
