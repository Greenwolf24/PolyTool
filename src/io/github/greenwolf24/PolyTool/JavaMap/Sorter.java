package io.github.greenwolf24.PolyTool.JavaMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

public class Sorter
{
	public static <E> ArrayList<E> sort(LinkedHashMap<E, Double> map)
	{
		// What we have is a map of objects to integers.
		// We want to sort the objects by their integer values.
		// The end result is an arraylist of objects sorted by their integer values.
		Set<E> keysSet = map.keySet();
		Collection<Double> valuesCol = map.values();
		ArrayList<E> keys = new ArrayList<>(keysSet);
		ArrayList<Double> values = new ArrayList<>(valuesCol);
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
