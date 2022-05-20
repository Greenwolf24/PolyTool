package io.github.greenwolf24.PolyTool.Color;

// Added in version 1.0.0 of the SwissArmyKnife library.
// Class version: 1.2.0
// Last modified for Library version: 1.2.0

import io.github.greenwolf24.PolyTool.Strings.LeadingCharacter;

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
	
	// This method is deprecated. Use the public SimpleColor nextColor() method instead.
	// It updates the color directly, but can also be used externally to create a new color.
	// this creates flexibility for whatever use-case you may have.
	@Deprecated
	public String nextColorUpdate()
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
	//*/
	
	public SimpleColor nextColor()
	{
		if(Red == 255 && Green < 255 && Blue == 0)
		{
			return new SimpleColor(Red, Green + 1, Blue, Alpha);
		}
		else if(Red > 0 && Green == 255 && Blue == 0)
		{
			return new SimpleColor(Red - 1, Green, Blue, Alpha);
		}
		else if(Red == 0 && Green == 255 && Blue < 255)
		{
			return new SimpleColor(Red, Green, Blue + 1, Alpha);
		}
		else if(Red == 0 && Green > 0 && Blue == 255)
		{
			return new SimpleColor(Red, Green - 1, Blue, Alpha);
		}
		else if(Red < 255 && Green == 0 && Blue == 255)
		{
			return new SimpleColor(Red + 1, Green, Blue, Alpha);
		}
		else if(Red == 255 && Green == 0 && Blue > 0)
		{
			return new SimpleColor(Red, Green, Blue - 1, Alpha);
		}
		
		// theoretically, we should never reach this point
		// but if we do, we'll just return the same color
		return this;
	}
	
	public String toString()
	{
		return formattedString("#RRGGBBAA");
	}
	
	public String toString(boolean alphaFirst)
	{
		if(alphaFirst)
		{
			return formattedString("#AARRGGBB");
		}
		return formattedString("#RRGGBBAA");
	}
	
	public String rawString()
	{
		return formattedString("RRGGBBAA");
	}
	
	public String rawString(boolean alphaFirst)
	{
		// this is a simple call to toString() without the #
		if(alphaFirst)
		{
			formattedString("AARRGGBB");
		}
		return rawString();
		//return toString(alphaFirst).substring(1);
	}
	
	public String kmlFormat()
	{
		// KML format is formated as: AA BB GG RR
		// AA is the alpha value, BB is the blue value, GG is the green value, RR is the red value
		return formattedString("AABBGGRR");
	}
	
	public String formattedString(String format)
	{
		// this is a new universal format for colors
		
		// the format is a string that can contain any character, but the following are key characters:
		// if the letters are lowercase, then the output will be the integer value of the color
		// if the letters are uppercase, then the output will be the hex value of the color
		// also if the letters are uppercase, then a leading 0 will be added if the value is less than 16
		
		// if there are 2 letters, then the value will be padded with leading zeros if necessary
		
		// AA is the alpha value, BB is the blue value, GG is the green value, RR is the red value
		
		String ret = format;
		
		ret = ret.replace("aaa", LeadingCharacter.leadChar(Integer.toString(Alpha),'0',3));
		ret = ret.replace("aa", LeadingCharacter.leadChar(Integer.toString(Alpha),'0',2));
		ret = ret.replace("a", LeadingCharacter.leadChar(Integer.toString(Alpha),'0',1));
		
		ret = ret.replace("bbb", LeadingCharacter.leadChar(Integer.toString(Blue),'0',3));
		ret = ret.replace("bb", LeadingCharacter.leadChar(Integer.toString(Blue),'0',2));
		ret = ret.replace("b", LeadingCharacter.leadChar(Integer.toString(Blue),'0',1));
		
		ret = ret.replace("ggg", LeadingCharacter.leadChar(Integer.toString(Green),'0',3));
		ret = ret.replace("gg", LeadingCharacter.leadChar(Integer.toString(Green),'0',2));
		ret = ret.replace("g", LeadingCharacter.leadChar(Integer.toString(Green),'0',1));
		
		ret = ret.replace("rrr", LeadingCharacter.leadChar(Integer.toString(Red),'0',3));
		ret = ret.replace("rr", LeadingCharacter.leadChar(Integer.toString(Red),'0',2));
		ret = ret.replace("r", LeadingCharacter.leadChar(Integer.toString(Red),'0',1));
		
		ret = ret.replace("AA", LeadingCharacter.leadChar(Integer.toHexString(Alpha),'0',2));
		ret = ret.replace("A", LeadingCharacter.leadChar(Integer.toHexString(Alpha),'0',1));
		
		ret = ret.replace("BB", LeadingCharacter.leadChar(Integer.toHexString(Blue),'0',2));
		ret = ret.replace("B", LeadingCharacter.leadChar(Integer.toHexString(Blue),'0',1));
		
		ret = ret.replace("GG", LeadingCharacter.leadChar(Integer.toHexString(Green),'0',2));
		ret = ret.replace("G", LeadingCharacter.leadChar(Integer.toHexString(Green),'0',1));
		
		ret = ret.replace("RR", LeadingCharacter.leadChar(Integer.toHexString(Red),'0',2));
		ret = ret.replace("R", LeadingCharacter.leadChar(Integer.toHexString(Red),'0',1));
		
		return ret;
	}
	
	public java.awt.Color toAwtColor(SimpleColor color)
	{
		return new java.awt.Color(color.Red, color.Green, color.Blue, color.Alpha);
	}
	
	public java.awt.Color getAwtColor()
	{
		return toAwtColor(this);
	}
	
	public SimpleColor fromAwtColor(java.awt.Color color)
	{
		return new SimpleColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}
}
