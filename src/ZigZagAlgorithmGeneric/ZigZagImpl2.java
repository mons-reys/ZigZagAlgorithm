package ZigZagAlgorithmGeneric;

import java.util.ArrayList;
import java.util.List;

public class ZigZagImpl2 implements IZigzag2<Track>{
	
	List<Track> tracks;
	Long size;
	
	

	public ZigZagImpl2(List<Track> tracks) {
		this.tracks = tracks;
		this.size = (long) tracks.size();
	}



	public List<Track> extremumsFinder(double percentage) {
		
		List<Track> extremums = new ArrayList<Track>();
		List<Track> FiltredExtremums = new ArrayList<Track>();


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
		for(int i = 1; i < size - 1; i++) {
			
			//check if true extremum
			if(this.isTrueExtremum((double) tracks.get(i - 1).getVolume(),  (double) tracks.get(i).getVolume(), percentage)) {
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
				}
			}
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
		
		
		return FiltredExtremums;
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
	
	



	
	
	public List<Track> filter(List<Track> tracks){
		List<Track> newItems = new ArrayList<Track>();

		for(int i = 0; i< tracks.size();)
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



	@Override
	public double calculateVolume(List<Track> tracks) {
		//index of the first min
		int firstMinIndex;
		
		
		double vol = tracks.get(0).getVolume() - tracks.get(tracks.size() - 1).getVolume();
		
		if(tracks.get(0).getType() == TrackType.min) firstMinIndex = 0;
		else firstMinIndex = 1;
		
		for(int i = firstMinIndex; i < tracks.size() - 1; i+=2) {
			vol += Math.abs(tracks.get(i).getVolume() - tracks.get(i + 1).getVolume());
		}
		
		return vol;
	}




	
}
