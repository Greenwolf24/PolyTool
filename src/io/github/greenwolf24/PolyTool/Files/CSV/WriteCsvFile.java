package io.github.greenwolf24.PolyTool.Files.CSV;

// Added in version 1.5.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.5.0

import io.github.greenwolf24.PolyTool.Files.SimpleWriter;

import java.io.File;
import java.util.ArrayList;

public class WriteCsvFile
{
	//TODO
	// This is a legit copy-paste exact same code between the two
	// These should be converted to wrappers around a single function
	public static void writeCSV(ArrayList<String[]> csv,File file)
	{
		StringBuilder main = new StringBuilder();
		for(String[] line : csv)
		{
			StringBuilder ls = new StringBuilder();
			for(String s : line)
			{
				ls.append(s+",");
			}
			main.append(ls.toString().substring(0,ls.toString().length()-1));
			main.append("\n");
		}
		SimpleWriter.writeString(main.toString(),file);
	}
	public static void writeCSV(String[][] csv, File file)
	{
		StringBuilder main = new StringBuilder();
		for(String[] line : csv)
		{
			StringBuilder ls = new StringBuilder();
			for(String s : line)
			{
				ls.append(s+",");
			}
			main.append(ls.toString().substring(0,ls.toString().length()-1));
			main.append("\n");
		}
		SimpleWriter.writeString(main.toString(),file);
	}
}
