package Test;

import java.util.ArrayList;
import java.util.List;

import ZigZagAlgorithm.IZigzagImpl;

public class Main {

	public static void main(String[] args) {
		//IZigzagImpl z = new IZigzagImpl();
		
		int ar[] = new int[11];
		
		
		
		for(int i = 0; i < 10; i++) {
			if(i == 3) i += 2;
			ar[i] = i;
		}
		
		
		for(int i: ar) {
			System.out.println(i);
		}
		
		
	}

}
