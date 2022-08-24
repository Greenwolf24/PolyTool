package io.github.greenwolf24.PolyTool.Maths.Graphing;

// Added in version 1.3.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.3.0

public class XYZCoordinates extends XYCoordinates
{
	public double z;
	
	public XYZCoordinates(double x, double y, double z)
	{
		super(x, y);
		this.z = z;
	}
	
	public XYCoordinates getXY()
	{
		return new XYCoordinates(x, y);
	}
	
	public double DistanceTo(XYZCoordinates other)
	{
		return Math.sqrt(super.distanceTo(getXY()) + Math.pow(z - other.z, 2));
	}
}
