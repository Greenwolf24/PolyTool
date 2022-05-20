package io.github.greenwolf24.PolyTool.Color.Ranges;

// Added in version 1.2.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.2.0

import io.github.greenwolf24.PolyTool.Color.SimpleColor;

public class RedGreenRange
{
	public static SimpleColor scale(double zeroToOne)
	{
		if(zeroToOne < 0.5)
		{
			zeroToOne *= 2.0;
			zeroToOne *= 255;
			return new SimpleColor(255,(int)zeroToOne,0);
		}
		else if(zeroToOne > 0.5)
		{
			zeroToOne *= 2.0 - 1.0;
			zeroToOne *= 255;
			return new SimpleColor(255 - (int)zeroToOne,255,0);
		}
		else
		{
			return new SimpleColor(255,255,0);
		}
	}
	
	public static SimpleColor scale(int zeroToOneHundred)
	{
		return scale((double)zeroToOneHundred / 100.0);
	}
	
	public static SimpleColor scalePercent(double percent)
	{
		return scale(percent / 100.0);
	}
}
