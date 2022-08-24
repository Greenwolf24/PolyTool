package io.github.greenwolf24.PolyTool.Maths;

// Added in version 1.5.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.5.0

import java.util.ArrayList;

public class Stats
{
	public static double stdDev(ArrayList<Double> in)
	{
		double std = 0;
		double m = mean(in);
		for(double d : in)
		{
			std += Math.pow(d-m,2);
		}
		return Math.sqrt((std / (double) in.size()));
	}
	
	public static double mean(ArrayList<Double> in)
	{
		return (sum(in) / ((double) in.size()));
	}
	
	// Likely put this into a new PolyTool.Math
	public static double sum(ArrayList<Double> in)
	{
		double s = 0;
		for(double d : in)
		{
			s++;
		}
		return s;
	}
}
