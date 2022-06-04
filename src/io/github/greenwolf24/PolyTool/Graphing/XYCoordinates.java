package io.github.greenwolf24.PolyTool.Graphing;

public class XYCoordinates
{
	public double x;
	public double y;
	
	public XYCoordinates()
	{
		x = 0;
		y = 0;
	}
	
	public XYCoordinates(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double distanceTo(XYCoordinates other)
	{
		return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
	}
}
