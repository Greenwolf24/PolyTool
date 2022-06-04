package io.github.greenwolf24.PolyTool.Color.Ranges;

// Added in version 1.3.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.3.0.

import io.github.greenwolf24.PolyTool.Color.SimpleColor;

public class VolantaAltitudeRange
{
	// this is very similar in purpose to the FlightRadar24AltitudeRange class,
	// but this one is used for the Volanta color scheme.
	
	// this one seems like it will be a lot easier to implement, as
	// it appears as volanta is using a very simple scale.
	// as such, it is much more akin to the RedGreenRange class in structure.
	
	// it appears that anything above 40,000 feet is considered the same color.
	// this is only by eye-balling the color scheme, though, so it may be wrong.
	private static double maxAltitude = 40000;
	
	private static SimpleColor scale(double zeroToOne)
	{
		if(zeroToOne > 1)
		{
			return new SimpleColor(92,64,255);
		}
		if(zeroToOne < 0)
		{
			return new SimpleColor(0,255,0);
		}
		
		// the range of red is 0 at 0, and 92 at 1.
		double red = zeroToOne * 92;
		
		// the range of green is 255 at 0, and 64 at 1.
		double green = ((1 - zeroToOne) * (255 - 64)) + 64;
		
		// the range of blue is 0 at 0, and 255 at 1.
		double blue = zeroToOne * 255;
		
		// if any of the values are greater than 255, they are set to 255.
		if(red > 255) red = 255;
		if(green > 255) green = 255;
		if(blue > 255) blue = 255;
		
		// if any of the values are less than 0, they are set to 0.
		// this theoretically shouldn't happen, but it's here just in case.
		if(red < 0) red = 0;
		if(green < 0) green = 0;
		if(blue < 0) blue = 0;
		
		return new SimpleColor((int)red, (int)green, (int)blue);
	}
	
	public static SimpleColor getColor(int altitudeFeet)
	{
		return scale((double)altitudeFeet / (double)maxAltitude);
	}
	
	public static SimpleColor scalePercent(double altitudeFeet)
	{
		return getColor((int) altitudeFeet);
	}
}
