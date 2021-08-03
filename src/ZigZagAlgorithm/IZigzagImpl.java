package ZigZagAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class IZigzagImpl implements IZigzag {

	public List<Double> extremumsFinder(int size, List<Double> values){
		// Empty vector to store points of 
		// local maxima and minima 
		ArrayList<Double> mx = new ArrayList<Double>(); 
		ArrayList<Double> mn = new ArrayList<Double>(); 
		ArrayList<Double> extremus = new ArrayList<Double>();

		// Checking whether the first point is 
		// local maxima or minima or none 
		if (values.get(0) > values.get(1)) {
		   mx.add(values.get(0)); 
		   extremus.add(values.get(0));
		}
		
		else if (values.get(0) < values.get(1)) { 
		   mn.add(values.get(0)); 
		   extremus.add(values.get(0));
		}
		
		
		// Iterating over all points to check 
		// local maxima and local minima 
		for(int i = 1; i < size - 1; i++) 
		{ 
		   // Condition for local minima 
		   if ((values.get(i - 1) > values.get(i)) && (values.get(i) < values.get(i + 1))) {
		       mn.add(values.get(i)); 
		   	   extremus.add(values.get(i));
		   	   
		   }
		   // Condition for local maxima 
		   else if ((values.get(i - 1) < values.get(i)) && (values.get(i) > values.get(i + 1))) {
			   mx.add(values.get(i)); 
		   	   extremus.add(values.get(i));
		   }
		      
		   		
		} 

		// Checking whether the last point is 
		// local maxima or minima or none 
		if (values.get(size - 1) > values.get(size - 2)) {
		   mx.add(values.get(size - 1));
		   extremus.add(values.get(size - 1));
		}
		
		else if (values.get(size - 1) < values.get(size - 2)) {
		   mn.add(values.get(size - 1)); 
		   extremus.add(values.get(size - 1));
		}
		
		
		// Print all the local maxima and 
		// local minima indexes stored 
		if (mx.size() > 0) 
		{ 

			   System.out.print("Points of Loc maxima are : "); 
		   
		   for(double a : mx) 
		       System.out.print(a + " "); 
		   System.out.println(); 
		} 
		else
		   System.out.println("There are no points " + 
		                   "of Local Maxima "); 

		if (mn.size() > 0) 
		{ 
		   System.out.print("Points of Local " + 
		                   "minima are : "); 
		   for(double a : mn) 
		       System.out.print(a + " "); 
		   System.out.println(); 
		} 
		else
		   System.out.println("There are no points of " + 
		                   "Local Maxima "); 
		
		return extremus;
		} 
	
	public List<Double> extremasFilter(List<Double> values, double percentage) {
		List<Double> filtred = new ArrayList<Double>();
		double diff, diffPercentage;
		Double previous = values.get(0);
		
		//add the first endpoint
		filtred.add(values.get(0));
		
		
		
		for(int i = 1; i < values.size() - 1; i++) {
			//calculate the diff between two points
			diff = Math.abs(values.get(i) - previous);
			
			//percentage of the diff beased on the previous point
			diffPercentage = diff / previous;
			 
			if(diffPercentage > percentage) {
				previous = values.get(i);
				filtred.add(values.get(i));
			} else {
				 System.out.println("val skipped :  " + values.get(i)); 
			}
		}
		
		//add the last endpoint
		filtred.add(values.get(values.size() - 1));

		return filtred;
	}

}
