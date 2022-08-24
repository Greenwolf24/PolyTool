package io.github.greenwolf24.PolyTool.Files;

// Added in version 1.5.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.5.0

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class SimpleReader
{
	public static ArrayList<String> getAsStringLines(String file)
	{
		return getAsStringLines(new File(file));
	}
	public static ArrayList<String> getAsStringLines(File file)
	{
		ArrayList<String> lines = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			String line;
			while((line = br.readLine()) != null)
			{
				lines.add(line);
			}
		}
		catch(Exception ex){return null;}
		return lines;
	}
	
	public static String getAsString(String file)
	{
		return getAsString(new File(file));
	}
	public static String getAsString(File file)
	{
		StringBuilder sb = new StringBuilder();
		for(String line : getAsStringLines(file))
		{
			sb.append(line);
		}
		return sb.toString();
	}
}
