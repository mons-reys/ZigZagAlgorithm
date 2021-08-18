package ZigZagAlgorithmGeneric;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class test {

	public static void main(String[] args) throws InterruptedException {
		List<Track> tracks = new ArrayList<Track>();
		List<Track> result = new ArrayList<Track>();
		
		
		/*double arr[] = { 4, 20, 15,17,16, 30, 14, 40, 12};

		for(double i: arr) {
			Track t = new Track(i, i);
			tracks.add(t);
		}
		
		
		ZigZagImpl2 algo = new ZigZagImpl2(tracks);
		
		result = algo.extremumsFinder((long) tracks.size(), 0.10);
		
		System.out.println("zigzag: ");
		for(Track i: result) {
			System.out.print(i.getVolume() + " : " + i.getType() + " \t");
		}
		*/
		
		
		Random rand = new Random();

		List<Track> rande = new ArrayList<Track>();

		
		for(int i = 0; i < 100000; i++) {
			double randomNum = rand.nextInt((5 - 0) + 1) + 0;
			
			double randomNum2 = rand.nextInt((100 - 0) + 1) + 0;
			
			double randomOfTwoInts = new Random().nextBoolean() ? randomNum : randomNum2;

			

		}
		
		
		System.out.print(" ");
		System.out.print(" ");
		System.out.println(" ");
		System.out.print(" ");
		System.out.print(" ");
		
		
		for(Track i: rande) {
			System.out.print(i.getVolume() + " \t");
		}
		
		

		
		
		
		long startTime = System.nanoTime();
		 

		ZigZagImpl2 algo = new ZigZagImpl2(rande);

		
		
		result = algo.extremumsFinder(0.20);
		
		System.out.println("zigzag: ");
		
		
		System.out.println(" ");
		
		System.out.println("the size of the input: " + rande.size() );
		System.out.println("the size of the result: " + result.size() );
		
		System.out.println("the final volume: " + algo.calculateVolume(result));
 
        // sleep for 5 seconds
        TimeUnit.SECONDS.sleep(5);
 
        /* � The code being measured ends � */
 
        long endTime = System.nanoTime();
 
        // get the difference between the two nano time valuess
        long timeElapsed = endTime - startTime;
 
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		

		

		

	}

}
