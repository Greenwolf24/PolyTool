package io.github.greenwolf24.PolyTool.Vector;

import java.util.ArrayList;

public class XYZVector
{
	public double i;
	public double j;
	public double k;
	
	public XYZVector(double i, double j, double k)
	{
		this.i = i;
		this.j = j;
		this.k = k;
	}
	
	public XYZVector()
	{
		this(0, 0, 0);
	}
	
	public XYZVector sum(XYZVector v)
	{
		return new XYZVector(i + v.i, j + v.j, k + v.k);
	}
	
	public double amplitude()
	{
		return Math.sqrt(i * i + j * j + k * k);
	}
	
	public static double amplitude(XYZVector v)
	{
		return Math.sqrt(v.i * v.i + v.j * v.j + v.k * v.k);
	}
	
	public XYZVector multiply(double d)
	{
		return new XYZVector(i * d, j * d, k * d);
	}
	
	public static XYZVector multiply(XYZVector v, double d)
	{
		return new XYZVector(v.i * d, v.j * d, v.k * d);
	}
	
	public XYZVector average(XYZVector[] vs)
	{
		double i = 0;
		double j = 0;
		double k = 0;
		
		for(XYZVector v : vs)
		{
			i += v.i;
			j += v.j;
			k += v.k;
		}
		
		return new XYZVector(i / vs.length, j / vs.length, k / vs.length);
	}
	
	// This function is experimental. I do not know if it works *exactly* the way that I want it to, but I
	// ran a couple of tests, and it seems to be working.
	public static XYZVector centralVector(XYZVector[] vs,double[] distances)
	{
		int indexShortestDistance = 0;
		double shortestDistance = Double.MAX_VALUE;
		for(int i = 0; i < distances.length; i++)
		{
			if(distances[i] < shortestDistance)
			{
				shortestDistance = distances[i];
				indexShortestDistance = i;
			}
		}
		ArrayList<Double> weights = new ArrayList<Double>();
		double weightSum = 0;
		for(int i = 0; i < distances.length; i++)
		{
			weights.add(shortestDistance / distances[i]);
			weightSum += weights.get(i);
		}
		double i = 0;
		double j = 0;
		double k = 0;
		for(int i2 = 0; i2 < vs.length; i2++)
		{
			i += vs[i2].i * weights.get(i2);
			j += vs[i2].j * weights.get(i2);
			k += vs[i2].k * weights.get(i2);
		}
		return new XYZVector(i / weightSum, j / weightSum, k / weightSum);
	}
	
	public String toString()
	{
		return "<" + i + ", " + j + ", " + k + ">";
	}
}
