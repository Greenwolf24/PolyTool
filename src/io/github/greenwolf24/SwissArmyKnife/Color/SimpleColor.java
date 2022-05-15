package io.github.greenwolf24.SwissArmyKnife.Color;

// Added in version 1.0.0 of the SwissArmyKnife library.
// Class version: 1.1.0
// Last modified for Library version: 1.1.0

public class SimpleColor
{
	public int Red;
	public int Green;
	public int Blue;
	public int Alpha;
	
	public SimpleColor(int r, int g, int b, int a)
	{
		Red = r;
		Green = g;
		Blue = b;
		Alpha = a;
	}
	
	public SimpleColor(int r, int g, int b)
	{
		Red = r;
		Green = g;
		Blue = b;
		Alpha = 255;
	}
	
	// This method is multi-purpose.
	// It updates the color directly, but can also be used externally to create a new color.
	// this creates flexibility for whatever use-case you may have.
	public String nextColor()
	{
		// this method will first update the color and then return the string
		// the result may or may not be used by the caller, but we don't necessarily care
		
		if(Red == 255 && Green < 255 && Blue == 0)
		{
			Green++;
		}
		else if(Red > 0 && Green == 255 && Blue == 0)
		{
			Red--;
		}
		else if(Red == 0 && Green == 255 && Blue < 255)
		{
			Blue++;
		}
		else if(Red == 0 && Green > 0 && Blue == 255)
		{
			Green--;
		}
		else if(Red < 255 && Green == 0 && Blue == 255)
		{
			Red++;
		}
		else if(Red == 255 && Green == 0 && Blue > 0)
		{
			Blue--;
		}
		
		return toString();
	}
	
	public String toString()
	{
		return toString(false);
	}
	
	public String toString(boolean alphaFirst)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("#");
		
		// it is possible that the hex string will be a single digit, so we need to pad it
		
		if(alphaFirst)
		{
			if(Alpha < 16)
			{
				sb.append("0");
			}
			sb.append(Integer.toHexString(Alpha));
		}
		if(Red < 16)
		{
			sb.append("0");
		}
		sb.append(Integer.toHexString(Red));
		if(Green < 16)
		{
			sb.append("0");
		}
		sb.append(Integer.toHexString(Green));
		if(Blue < 16)
		{
			sb.append("0");
		}
		sb.append(Integer.toHexString(Blue));
		if(!alphaFirst)
		{
			if(Alpha < 16)
			{
				sb.append("0");
			}
			sb.append(Integer.toHexString(Alpha));
		}
		//sb.append(Integer.toHexString(Red));
		//sb.append(Integer.toHexString(Green));
		//sb.append(Integer.toHexString(Blue));
		//if(!alphaFirst)
		{
		//	sb.append(Integer.toHexString(Alpha));
		}
		return sb.toString();
	}
	
	public String rawString()
	{
		return rawString(false);
	}
	
	public String rawString(boolean alphaFirst)
	{
		// this is a simple call to toString() without the #
		return toString(alphaFirst).substring(1);
	}
	
	public String kmlFormat()
	{
		// KML format is formated as: AA BB GG RR
		// AA is the alpha value, BB is the blue value, GG is the green value, RR is the red value
		
		StringBuilder sb = new StringBuilder();
		
		// it is possible that the hex string will be a single digit, so we need to pad it
		if(Alpha < 16)
		{
			sb.append("0");
		}
		sb.append(Integer.toHexString(Alpha));
		
		if(Blue < 16)
		{
			sb.append("0");
		}
		sb.append(Integer.toHexString(Blue));
		
		if(Green < 16)
		{
			sb.append("0");
		}
		sb.append(Integer.toHexString(Green));
		
		if(Red < 16)
		{
			sb.append("0");
		}
		sb.append(Integer.toHexString(Red));
		
		return sb.toString();
	}
}
