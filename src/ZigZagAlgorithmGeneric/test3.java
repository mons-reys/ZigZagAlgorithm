package ZigZagAlgorithmGeneric;

import java.util.ArrayList;
import java.util.List;

public class test3 {

	public static void main(String[] args) {
		List<Track> tracks = new ArrayList<Track>();
		List<Track> result = new ArrayList<Track>();
		
		
		
		//double arr[] = {70,65,70,55,60,45,50,35,90,55,60,10,12,45,80,75,77,45};
		double arr[] = {70, 90, 12, 87, 50};

		tracks.add(new Track(5.00, 70.00));
		tracks.add(new Track(10.00, 90.00));
		tracks.add(new Track(11.00, 12.00));
		tracks.add(new Track(12.00, 87.00));
		tracks.add(new Track(19.00, 14.00));
		tracks.add(new Track(19.00, 30.00));
		tracks.add(new Track(19.00, 15.00));
		tracks.add(new Track(20.00, 50.00));

		
		
		

		ZigZagImpl2 algo = new ZigZagImpl2(tracks);

		for(Track i: tracks) {
			System.out.print(i.getVolume() + " : " + i.getType() + " \t");
		}
		
		
		result = algo.extremumsFinder(0.20);
		
		
		
		
		System.out.println("zigzag: ");
		
		for(Track i: result) {
			System.out.print(i.getVolume() + " : " + i.getType() + " \t");
		}
		
		
		System.out.println(" ");
		
		System.out.println("the size of the input: " + tracks.size() );
		System.out.println("the size of the result: " + result.size() );
		
		System.out.println("the final volume: " + algo.calculateVolume(result));

	}

}
