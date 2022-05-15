package io.github.greenwolf24.SwissArmyKnife.Color;

// Added in version 1.0.0 of the SwissArmyKnife library.
// Class version: 1.0.0

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
		this(r, g, b, 255);
	}
	
	// This method is multi-purpose.
	// It can either be used to update the color directly, or be used to create a new color.
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
		if(alphaFirst)
		{
			sb.append(Integer.toHexString(Alpha));
		}
		sb.append(Integer.toHexString(Red));
		sb.append(Integer.toHexString(Green));
		sb.append(Integer.toHexString(Blue));
		if(!alphaFirst)
		{
			sb.append(Integer.toHexString(Alpha));
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
}
