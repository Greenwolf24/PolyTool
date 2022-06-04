package io.github.greenwolf24.PolyTool.Mapping;

// Added in version 1.3.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.3.0

import io.github.greenwolf24.PolyTool.Graphing.Triangle;
import io.github.greenwolf24.PolyTool.Graphing.XYZCoordinates;

import java.util.ArrayList;

public class Util
{
	public static double distanceBetween(Position p1, Position p2)
	{
		// Get the distance between the two points in meters
		// Note: this does not account for altitude
		// p1 is the first point, p2 is the second point
		// These are both Global Lat/Lon coordinates, so we can use the Haversine formula
		// The formula is:
		// d = 2 * asin(sqrt((sin((lat1 - lat2) / 2))^2 + cos(lat1) * cos(lat2) * (sin((lon1 - lon2) / 2))^2))
		
		double lat1 = Math.toRadians(p1.getLatitude());
		double lat2 = Math.toRadians(p2.getLatitude());
		double lon1 = Math.toRadians(p1.getLongitude());
		double lon2 = Math.toRadians(p2.getLongitude());
		
		double d = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin((lat1 - lat2) / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((lon1 - lon2) / 2), 2)));
		d = d * 6371000; // D is in meters
		
		return d;
	}
	
	public static double bearingFrom(Position p1, Position p2)
	{
		// Get the bearing from p1 to p2
		// p1 is the first point, p2 is the second point
		// These are both Global Lat/Lon coordinates
		// the formula is:
		// theta = atan2(sin(lon2 - lon1) * cos(lat2), cos(lat1) * sin(lat2) - sin(lat1) * cos(lat2) * cos(lon2 - lon1))
		
		double lat1 = Math.toRadians(p1.getLatitude());
		double lat2 = Math.toRadians(p2.getLatitude());
		double lon1 = Math.toRadians(p1.getLongitude());
		double lon2 = Math.toRadians(p2.getLongitude());
		
		double theta = Math.atan2(Math.sin(lon2 - lon1) * Math.cos(lat2), Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
		theta = Math.toDegrees(theta);
		theta = (theta + 360) % 360; // theta is in degrees
		
		return theta;
	}
	
	public static Position midPoint(Position p1, Position p2)
	{
		// Get the midpoint between p1 and p2
		// This does account for altitude, but for any sort of long distance, I can't imagine it being useful
		// p1 is the first point, p2 is the second point
		// These are both Global Lat/Lon coordinates
		// the formula is:
		// DeltaLambda = lon2 - lon1
		// Bx = cos(lat2) * cos(DeltaLambda)
		// By = cos(lat2) * sin(DeltaLambda)
		// latMid = atan2(sin(lat1) + sin(lat2), sqrt((cos(lat1) + Bx)^2 + By^2))
		// lonMid = lon1 + atan2(By, cos(lat1) + Bx)
		
		double lat1 = Math.toRadians(p1.getLatitude());
		double lat2 = Math.toRadians(p2.getLatitude());
		double lon1 = Math.toRadians(p1.getLongitude());
		double lon2 = Math.toRadians(p2.getLongitude());
		
		double DeltaLambda = lon2 - lon1;
		double Bx = Math.cos(lat2) * Math.cos(DeltaLambda);
		double By = Math.cos(lat2) * Math.sin(DeltaLambda);
		double latMid = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt(Math.pow(Math.cos(lat1) + Bx, 2) + Math.pow(By, 2)));
		double lonMid = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);
		
		Position ret = new Position(Math.toDegrees(latMid), Math.toDegrees(lonMid));
		
		if(p1.hasAltitude && p2.hasAltitude)
		{
			ret.setAltitude((p1.getAltitude() + p2.getAltitude()) / 2);
		}
		
		return ret;
	}
	
	public static XYZCoordinates toCartesianCoordinates(Position p)
	{
		// Convert a position to cartesian coordinates
		// This does account for altitude, even if it's not used in the original position
		double lat = Math.toRadians(p.getLatitude());
		double lon = Math.toRadians(p.getLongitude());
		double alt = p.getAltitude() + 6371000; // 6371000 is the radius of the earth in meters
		
		double x = Math.cos(lat) * Math.cos(lon) * alt;
		double y = Math.cos(lat) * Math.sin(lon) * alt;
		double z = Math.sin(lat) * alt;
		
		return new XYZCoordinates(x, y, z);
	}
	
	public static Position fromCartesianCoordinates(double x, double y, double z)
	{
		// Convert cartesian coordinates to a position
		// While this function will account for altitude, it does not mean that the coordinates point to an altitude
		// We will compute the altitude anyway
		
		double lat = Math.atan2(z, Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
		double lon = Math.atan2(y, x);
		double alt = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) - 6371000;
		
		return new Position(Math.toDegrees(lat), Math.toDegrees(lon), alt);
	}
	
	public static Position fromCartesianCoordinates(XYZCoordinates c)
	{
		return fromCartesianCoordinates(c.x, c.y, c.z);
	}
	
	public static Position incenter(Position p1, Position p2, Position p3)
	{
		// Get the position of the incenter of a triangle
		// This is a pretty long line, and it can be split up into sections
		return fromCartesianCoordinates(new Triangle(toCartesianCoordinates(p1), toCartesianCoordinates(p2), toCartesianCoordinates(p3)).incenter());
	}
	
	public static ArrayList<Position> center(ArrayList<Position> positions)
	{
		if(positions.size() > 3)
		{
			// if we are in here, we have more than 3 positions
			// I have not found the proper math for this yet
			// This is a temporary solution which will split the positions into multiple groups
			Position center = incenter(positions.get(0), positions.get(1), positions.get(2));
			positions.remove(0);
			positions.remove(0);
			positions.remove(0);
			ArrayList<Position> center2 = center(positions);
			ArrayList<Position> ret = new ArrayList<Position>();
			ret.add(center);
			ret.addAll(center2);
			return ret;
		}
		if(positions.size() == 3)
		{
			Position center = incenter(positions.get(0), positions.get(1), positions.get(2));
			ArrayList<Position> ret = new ArrayList<Position>();
			ret.add(center);
			return ret;
		}
		if(positions.size() == 2)
		{
			Position center = midPoint(positions.get(0), positions.get(1));
			ArrayList<Position> ret = new ArrayList<Position>();
			ret.add(center);
			return ret;
		}
		if(positions.size() == 1)
		{
			return positions;
		}
		if(positions.size() == 0)
		{
			// the compiler is complaining that at this point in the code, it will always be true
			// so the if statement is redundant... however, it is here to make it easier to debug
			// in any case yet unforeseen
			return new ArrayList<Position>();
		}
		// Theoretically, we should never get here
		// But the compiler doesn't know that
		// Good news, if we do get here, the runtime will nearly instantly crash and tell us
		return null;
	}
}
