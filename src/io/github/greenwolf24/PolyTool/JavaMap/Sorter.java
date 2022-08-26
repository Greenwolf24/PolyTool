package io.github.greenwolf24.PolyTool.JavaMap;

// Added in version UNKWN of the PolyTool library.
// Class version: 1.0.1
// Last modified for Library version: 1.5.1

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Sorter
{
	public static <E> ArrayList<E> sort(LinkedHashMap<E, Double> map)
	{
		// What we have is a map of objects to integers.
		// We want to sort the objects b
		// y their integer values.
		// The end result is an arraylist of objects sorted by their integer values.
		ArrayList<E> keys = new ArrayList<>(map.keySet());
		ArrayList<Double> values = new ArrayList<>(map.values());
		ArrayList<E> sorted = new ArrayList<>();
		
		while(keys.size() > 0)
		{
			double smallest = Double.MAX_VALUE;
			int smallestIndex = -1;
			
			for(int i = 0; i < values.size(); i++)
			{
				if(values.get(i) < smallest)
				{
					smallest = values.get(i);
					smallestIndex = i;
				}
			}
			
			sorted.add(keys.get(smallestIndex));
			keys.remove(smallestIndex);
			values.remove(smallestIndex);
		}
		
		return sorted;
	}
}
