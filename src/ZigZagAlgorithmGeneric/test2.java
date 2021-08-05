package ZigZagAlgorithmGeneric;

import java.util.ArrayList;
import java.util.List;



public class test2 {


	public static void main(String[] args) {
		ArrayList<item> items = new ArrayList<item>();
		
		
		items.add(new item(TrackType.max, 4));
		items.add(new item(TrackType.max, 7));
		items.add(new item(TrackType.max, 3));
		items.add(new item(TrackType.min, 2));
		items.add(new item(TrackType.min, 5));
		items.add(new item(TrackType.max, 8));
		items.add(new item(TrackType.max, 7));
	
		
		

		for(int i = 0; i<items.size(); )
		{
		    item pick = items.get(i);
		    for(i += 1; i < items.size() && pick.type == items.get(i).type; i++)
		    {
		        if((pick.value > items.get(i).value) == (pick.type == TrackType.min))
		            pick = items.get(i);
		    }
		    newItems.add(pick);
		}
		        
		for(item i: items) {
			System.out.print(i.getValue() + " \t ");
		}

		System.out.println(" \t ");
		for(item i: newItems) {
			System.out.print(i.getValue() + " \t ");
		}
	}

}
