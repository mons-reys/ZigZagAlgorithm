package ZigZagAlgorithmGeneric;

import java.util.ArrayList;
import java.util.List;

public class ZigZagImpl2 implements IZigzag2<Track>{
	
	List<Track> tracks;
	
	

	public ZigZagImpl2(List<Track> tracks) {
		this.tracks = tracks;
	}



	public List<Track> extremumsFinder(Long size, double percentage) {
		
		List<Track> extremums = new ArrayList<Track>();
		
		//add the first point 
		extremums.add(tracks.get(0));
		
		//check the points 
		for(int i = 1; i < size - 1; i++) {
			
			//check if true extremum
			if(this.isTrueExtremum((double) tracks.get(i - 1).getVolume(),  (double) tracks.get(i).getVolume(), percentage)) {
				//check if a max or min 
				boolean isMax = this.isMaximum((double) tracks.get(i - 1).getVolume(),  (double) tracks.get(i).getVolume() ,  (double) tracks.get(i + 1).getVolume());
				boolean isMin = this.isMinimum((double) tracks.get(i - 1).getVolume(),  (double) tracks.get(i).getVolume() ,  (double) tracks.get(i + 1).getVolume());
				if(isMax || isMin ){
					extremums.add(tracks.get(i));
				}
			}
		}
		
		//add the last point 
		extremums.add(tracks.get((int) (size - 1)));
		
		
		return extremums;
	}

	
	
	//generic need to fix here
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
}
