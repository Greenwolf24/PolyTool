package io.github.greenwolf24.PolyTool.Strings;

// Added in version 1.2.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.2.0

public class LeadingCharacter
{
	public static String leadChar(String str, String chr, int num)
	{
		// This method will add a character to the beginning of a string.
		// it will be added until the length of the string is equal to the num
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = str.length(); i < num; i++)
		{
			sb.append(chr);
		}
		
		return sb.toString() + str;
	}
	
	public static String leadChar(String str, char chr, int num)
	{
		// this is an alternate method of accessing the above method.
		return leadChar(str, String.valueOf(chr), num);
	}
}
