package io.github.greenwolf24.PolyTool.Maths.Graphing;

public class Triangle
{
	private XYZCoordinates p1;
	private XYZCoordinates p2;
	private XYZCoordinates p3;
	
	public Triangle(XYZCoordinates p1, XYZCoordinates p2, XYZCoordinates p3)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	public Triangle(XYCoordinates p1, XYCoordinates p2, XYCoordinates p3)
	{
		this.p1 = new XYZCoordinates(p1.x, p1.y, 0);
		this.p2 = new XYZCoordinates(p2.x, p2.y, 0);
		this.p3 = new XYZCoordinates(p3.x, p3.y, 0);
	}
	
	public XYZCoordinates incenter()
	{
		// returns the incenter of the triangle
		double a1 = Math.sqrt(Math.pow((p2.x - p3.x),2) + Math.pow((p2.y - p3.y), 2) + Math.pow((p2.z - p3.z), 2));
		double b1 = Math.sqrt(Math.pow((p1.x - p3.x),2) + Math.pow((p1.y - p3.y), 2) + Math.pow((p1.z - p3.z), 2));
		double c1 = Math.sqrt(Math.pow((p1.x - p2.x),2) + Math.pow((p1.y - p2.y), 2) + Math.pow((p1.z - p2.z), 2));
		
		double ix = (a1 * p1.x + b1 * p2.x + c1 * p3.x) / (a1 + b1 + c1);
		double iy = (a1 * p1.y + b1 * p2.y + c1 * p3.y) / (a1 + b1 + c1);
		double iz = (a1 * p1.z + b1 * p2.z + c1 * p3.z) / (a1 + b1 + c1);
		
		return new XYZCoordinates(ix, iy, iz);
	}
}
