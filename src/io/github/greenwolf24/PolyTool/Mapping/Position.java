package io.github.greenwolf24.PolyTool.Mapping;

// Added in version 1.3.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.3.0

public class Position
{
	public double Latitude;
	public double Longitude;
	
	public double Altitude;
	// While it is highly recommended to use meters for this, it may be possible to use other units.
	// Some functions *will* require the Altitude to be in meters, however.
	
	public boolean hasAltitude;
	
	public Position()
	{
		Latitude = 0;
		Longitude = 0;
		Altitude = 0;
		hasAltitude = false;
	}
	
	public Position(double lat, double lon, double alt)
	{
		Latitude = lat;
		Longitude = lon;
		Altitude = alt;
		hasAltitude = true;
	}
	
	public Position(double lat, double lon)
	{
		Latitude = lat;
		Longitude = lon;
		Altitude = 0;
		hasAltitude = false;
	}
	
	public void setAltitude(double alt)
	{
		Altitude = alt;
		hasAltitude = true;
	}
	
	public String toString()
	{
		if(hasAltitude)
			return "(" + Latitude + ", " + Longitude + ", " + Altitude + ")";
		else
			return "(" + Latitude + ", " + Longitude + ")";
	}
	
	public double getLatitude()
	{
		return Latitude;
	}
	
	public double getLongitude()
	{
		return Longitude;
	}
	
	public double getAltitude()
	{
		return Altitude;
	}
	
	public boolean hasAltitude()
	{
		return hasAltitude;
	}
	
	public Position clone()
	{
		return new Position(Latitude, Longitude, Altitude);
	}
	
	public double distanceTo(Position other)
	{
		return io.github.greenwolf24.PolyTool.Mapping.Util.distanceBetween(this, other);
	}
}
