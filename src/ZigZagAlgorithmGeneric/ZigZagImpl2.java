package ZigZagAlgorithmGeneric;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZigZagImpl2 implements IZigzag2<Track>{
	
	List<Track> tracks;
	Long size;
	

	public ZigZagImpl2(List<Track> tracks) {

		
		this.tracks = this.StableValuesFilter(tracks);
		this.size = (long) this.tracks.size();
		System.out.println("size od: " + size);
	}



	public List<Track> extremumsFinder(double percentage, double anomliesDifferenceOfMinutes) throws ParseException {
		
		List<Track> extremums = new ArrayList<Track>();
		List<Track> FiltredExtremums = new ArrayList<Track>();
		List<Track> FiltredAnomalies = new ArrayList<Track>();
		
		
		List<Track> anomalies = new ArrayList<Track>();



		// Checking whether the first point is 
				// local maxima or minima or none 
				if (tracks.get(0).getVolume() > tracks.get(1).getVolume()) {
					tracks.get(0).setType(TrackType.max);
				}
				else { 
					tracks.get(0).setType(TrackType.min);
				}
				
				extremums.add(tracks.get(0));

	
		
		
		//check the points 
		for(int i = 1; i < this.size - 1; i++) {
			boolean v =(this.isAnomaly(tracks.get(0), tracks.get(1), tracks.get(2), anomliesDifferenceOfMinutes, percentage));

			//if(!(i+2 < size - 1) || ! this.isAnomaly(tracks.get(i), tracks.get(i + 1), tracks.get(i + 2), (long) 1, percentage) ) {
				//check if true extremum
			double val = (double) tracks.get(i).getVolume();
			
				boolean percentageCheckTrue = this.isTrueExtremum((double) extremums.get(extremums.size() - 1).getVolume(),  (double) tracks.get(i).getVolume(), percentage);
			
				if(percentageCheckTrue) {
					//check if a max or min 
					boolean isMax = this.isMaximum((double) tracks.get(i - 1).getVolume(),  (double) tracks.get(i).getVolume() ,  (double) tracks.get(i + 1).getVolume());
					boolean isMin = this.isMinimum((double) tracks.get(i - 1).getVolume(),  (double) tracks.get(i).getVolume() ,  (double) tracks.get(i + 1).getVolume());
					
					if(isMax){
						//set the type as max
						tracks.get(i).setType(TrackType.max);
						extremums.add(tracks.get(i));
					}else if(isMin) {
						//set the type as min
						tracks.get(i).setType(TrackType.min);
						extremums.add(tracks.get(i));
					}else {
						System.out.println("skipped : " + tracks.get(i).getVolume() + " date : " + tracks.get(i).getTime());
					}
				}else {
					if(((double) extremums.get(extremums.size() - 1).getVolume() < (double) tracks.get(i).getVolume()) &&  extremums.get(extremums.size() - 1).getType() == (TrackType.max)) {
						//set the type as max
						tracks.get(i).setType(TrackType.max);
						extremums.add(tracks.get(i));
					}else if(((double) extremums.get(extremums.size() - 1).getVolume() > (double) tracks.get(i).getVolume()) &&  extremums.get(extremums.size() - 1).getType() == (TrackType.min)) {
						//set the type as min
						tracks.get(i).setType(TrackType.min);
						extremums.add(tracks.get(i));
					}
				}
			
				
				
			/*}else {
			
				//detect the anomly 
				if(this.isAnomaly(tracks.get(i), tracks.get(i + 1), tracks.get(i + 2), (long) 360, percentage)) {
					//we add the 3 points to the anomalies list
					anomalies.add(tracks.get(i));
					anomalies.add(tracks.get(i + 1));
					anomalies.add(tracks.get(i + 2));
					
					System.out.println("i am at the point : " + tracks.get(i).getVolume() + "index : " + (i +2));

					
					//we skip 2 tracks;
					//System.out.println("skipped as anomaly: " + tracks.get(i).getVolume() + " : " + tracks.get(i).getType() + " AND " + tracks.get(i  +1 ).getVolume() + " : " + tracks.get(i +1 ).getType() + " AND " + tracks.get(i + 2).getVolume() + " : " + tracks.get(i + 2).getType());
					 i += 1;
			}
			
			}*/
		
	}
		
		
		
		//add the last point 
		// local maxima or minima or none 
		if (tracks.get((int) (size - 1)).getVolume() > tracks.get((int) (size - 2)).getVolume()) {
			tracks.get((int) (size - 1)).setType(TrackType.max);
		}
		else{ 
			tracks.get((int) (size - 1)).setType(TrackType.min);
		}
		extremums.add(tracks.get((int) (size - 1)));
		
		//filter the extremums
		FiltredExtremums = this.filter(extremums);
		
		//anomalies filter 
		FiltredAnomalies = this.anomaliesFilter(FiltredExtremums, anomliesDifferenceOfMinutes, percentage);
		
	
		
		return  FiltredAnomalies;
	}

	
	
	public boolean isTrueExtremum(double previous, double current , double percentage) {
		double diff, diffPercentage;
		
		//calculate the diff between two points
		diff = Math.abs(current - previous);
		
		//percentage of the diff beased on the previous point
		diffPercentage = diff / previous;
		 
		if(diffPercentage > percentage) return true;
		else return false;
	}




	
	public boolean isMaximum(double previous, double current, double next) {
		 // Condition for local maxima
		  if ((previous < current) && (current > next)) return true;
		  else return false;
	}


	
	public boolean isMinimum(double previous, double current, double next) {
		// Condition for local minima
		  if ((previous > current) && (current < next)) return true;
		  else return false;
	}
	
	



	
	//filter the list to a suit of min max only 
	public List<Track> filter(List<Track> tracks){
		List<Track> newItems = new ArrayList<Track>();

		newItems.add(tracks.get(0));
		for(int i = 1; i< tracks.size();)
		{
		    Track pick = tracks.get(i);
		    for(i += 1; i < tracks.size() && pick.getType() == tracks.get(i).getType(); i++)
		    {
		        if((pick.getVolume() > tracks.get(i).getVolume()) == (pick.getType() == TrackType.min))
		            pick = tracks.get(i);
		    }
		    newItems.add(pick);
		}
		        

		return newItems;
		
	}
	
	
	
	//method to filter stable values and replace them with one value
	public List<Track> StableValuesFilter(List<Track> tracks){
		
		List<Track> newItems = new ArrayList<Track>();

		for(int i = 0; i< tracks.size();)
		{
			
			Track pick = tracks.get(i);

		    for(i += 1; i < tracks.size() && pick.getVolume().equals(tracks.get(i).getVolume()); i++)
		    {
		        if((pick.getVolume() != tracks.get(i).getVolume()))
		            pick = tracks.get(i);
		    }
		    newItems.add(pick);
		}
        
		

		return newItems;
	}
	
	
	

	
	//anomaly detector
	
	
	/*
	 * here we have 3 tracks: current, next1, next2
	 * 1- we compare the time between current and next2 ( must be small)
	 * 2- we compare the precentage of volume between current and next2 (must be small)
	 * 3- we compare the percentage of volume between current and next1 also next1 and next2 (must be big)
	 * if it's the case we return an anomaly
	 * */
	
	public boolean isAnomaly(Track current, Track next1, Track next2, double anomliesDifferenceOfMinutes, double percentage) throws ParseException {
		//-------calculate the time between two tracks--------
		
		 // SimpleDateFormat converts the
        // string format to date object
		long duration  = current.getTime().getTime() - next2.getTime().getTime();

		
		double differenceInMinutes = Math.abs(TimeUnit.MILLISECONDS.toMinutes(duration));
		
		

		//System.out.println("diff: " + differenceInMinutes);

        // Calucalte time difference
        // in milliseconds
        //double differenceInMilliSeconds
        //    = Math.abs(d2.getTime() - d1.getTime());
        
        
        // Calculating the difference in milliseconds
       // double differenceInMilliSeconds
        //    = Math.abs(current.getTime() - next2.getTime());
  
  
        // Calculating the difference in Minutes
       // double differenceInMinutes
       //     = (differenceInMilliSeconds / (60 * 1000)) % 60;
  
		//-------calculate the time between two tracks--------

		if(differenceInMinutes < anomliesDifferenceOfMinutes) {
			
			//diff between current and next2 must be samll than the percentage we give in the param
			boolean percentageBetweenPreviousAndNext = this.isTrueExtremum(current.getVolume(), next2.getVolume(), percentage);
			
			if(! percentageBetweenPreviousAndNext){
				//must be greater than the percentage in the param
				boolean percentageBetweenCurrentAndNext1 = this.isTrueExtremum(current.getVolume(), next1.getVolume(), percentage);
				boolean percentageBetweenNext1AndNext2 = this.isTrueExtremum(next1.getVolume(), next2.getVolume(), percentage);

				boolean c = percentageBetweenCurrentAndNext1 && percentageBetweenNext1AndNext2;
				if(percentageBetweenCurrentAndNext1 && percentageBetweenNext1AndNext2) return true; 
				else return false;
			}
		}
		
		return false;
		
	}
	
	
	public  List<Track> anomaliesFilter(List<Track> tracks, double anomliesDifferenceOfMinutes, double percentage) throws ParseException {
		
		List<Track> result = new ArrayList<Track>();

		
		for(int i = 0; i < tracks.size() - 2; i++ ) {
			
			boolean isAnomaly = this.isAnomaly(tracks.get(i), tracks.get(i + 1), tracks.get(i + 2), anomliesDifferenceOfMinutes, 0.70);
			
			if(!isAnomaly) {
				result.add(tracks.get(i));
				System.out.println("added: " + tracks.get(i).getVolume());

			}else {
				System.out.println("skipped : " + " tarck: " + tracks.get(i + 1) + " tarck: " +  tracks.get(i + 2));
				tracks.remove(i+1);
			}
			
		}
		
		
		//check the last point
		boolean isAnomaly = this.isAnomaly(tracks.get(tracks.size() - 3), tracks.get(tracks.size() - 2), tracks.get(tracks.size() - 1), anomliesDifferenceOfMinutes, 0.70);

		if(!isAnomaly) {
			result.add(tracks.get(tracks.size() - 2));
			System.out.println("added: " + tracks.get((tracks.size() - 3)).getVolume());

		}else {
			System.out.println("skipped : " + " tarck: " + tracks.get(tracks.size() - 3) + " tarck: " +  tracks.get(tracks.size() - 2));
			tracks.remove(tracks.size() - 2);
		}
		result.add(tracks.get(tracks.size() - 1));
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public double calculateVolume(List<Track> tracks) {
		//index of the first min
		int firstMinIndex = 0;
		int lastMaxIndex = tracks.size() - 1;
		
		
		
		double vol = tracks.get(0).getVolume() - tracks.get(tracks.size() - 1).getVolume();
		
		/*if(tracks.get(0).getType() == TrackType.min) {
			if(tracks.get(1).getType()== tracks.get(0).getType()) firstMinIndex = 1;
			else firstMinIndex = 2;
		}else firstMinIndex = 0;*/
		
		
		//find the first index 
		//in case of max max we change the first max to min and start from 0
		if(tracks.get(0).getType() == TrackType.max && tracks.get(1).getType() == TrackType.max) {
			tracks.get(0).setType(TrackType.min);
			firstMinIndex = 0;
			
		}else if(tracks.get(0).getType() == TrackType.min && tracks.get(1).getType() == TrackType.max) {
			firstMinIndex = 0;
		}else {
			firstMinIndex = 1;
		}
		
		//find the last index 
		if(tracks.get(lastMaxIndex).getType() == TrackType.min) lastMaxIndex = tracks.size() - 2;
			
			
		System.out.print("firstMinIndex: " + firstMinIndex + " : " + tracks.get(firstMinIndex).getVolume());
		System.out.println("");
		System.out.print("lastMaxIndex: " + lastMaxIndex + " : " + tracks.get(lastMaxIndex).getVolume());
		System.out.println("");
		for(Track i : tracks) {
			System.out.print("type : " + i.getType() + " volume : " + i.getVolume() + "\n");

		}
		
		for(int i = firstMinIndex; i < lastMaxIndex ; i+=2) {
			vol += Math.abs(tracks.get(i).getVolume() - tracks.get(i + 1).getVolume());
		}
		
		return vol;
	}




	
}
