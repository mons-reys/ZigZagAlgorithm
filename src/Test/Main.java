package Test;

import java.util.ArrayList;
import java.util.List;

import ZigZagAlgorithm.IZigzagImpl;

public class Main {

	public static void main(String[] args) {
		IZigzagImpl z = new IZigzagImpl();
		
		double arr[] = { 10, 20, 15,17,16, 30, 14, 40, 12};
		
		List<Double> list = new ArrayList<Double>();
		List<Double> filtred =  new ArrayList<Double>();
		
		for(double i: arr) {
			list.add(i);
		}
		
		
		//get extremums from the list
		List<Double> extremums = z.extremumsFinder(list.size(), list);
		
		//filter the extremums based on the deviation percentage = 20%
		filtred = z.extremasFilter(extremums, 0.20);
		
		
		System.out.println("zigzag: ");
		for(double i: filtred) {
			System.out.println(i);
		}
	}

}
