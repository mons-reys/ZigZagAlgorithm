package ZigZagAlgorithmGeneric;

import java.util.ArrayList;
import java.util.List;

public class test {

	public static void main(String[] args) {
		List<Track> tracks = new ArrayList<Track>();
		List<Track> result = new ArrayList<Track>();
		
		
		double arr[] = { 10, 20, 15,17,16, 30, 14, 40, 12};

		for(double i: arr) {
			Track t = new Track(i, i);
			tracks.add(t);
		}
		
		
		ZigZagImpl2 algo = new ZigZagImpl2(tracks);
		
		result = algo.extremumsFinder((long) tracks.size(), 0.09);
		
		System.out.println("zigzag: ");
		for(Track i: result) {
			System.out.println(i.getVolume());
		}

	}

}
