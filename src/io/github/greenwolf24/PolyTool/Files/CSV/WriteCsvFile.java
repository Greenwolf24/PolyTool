package io.github.greenwolf24.PolyTool.Files.CSV;

// Added in version 1.5.0 of the PolyTool library.
// Class version: 1.1.0
// Last modified for Library version: 1.5.1

import io.github.greenwolf24.PolyTool.Files.SimpleWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriteCsvFile
{

	public static void writeCSV(ArrayList<ArrayList<String>> csv,String file)
	{
		writeCSV(csv,new File(file));
	}
	public static void writeCSV(ArrayList<ArrayList<String>> csv,File file)
	{
		StringBuilder main = new StringBuilder();
		for(ArrayList<String> line : csv)
		{
			StringBuilder ls = new StringBuilder();
			for(int i = 0;i < line.size();i++)
			{
				//ls.append(s+",");
				if(line.get(i).contains(","))
				{
					String cell = "\"" + line.get(i) + "\"";
					ls.append(cell + ",");
				}
				else
				{
					ls.append(line.get(i) + ",");
				}
			}
			main.append(ls.toString().substring(0,ls.toString().length()-1));
			main.append("\n");
		}
		SimpleWriter.writeString(main.toString(),file);
	}

	public static void writeCSV(String[][] csv,String file)
	{
		writeCSV(csv,new File(file));
	}
	public static void writeCSV(String[][] csv, File file)
	{
		ArrayList<ArrayList<String>> vals = new ArrayList<>();

		for(String[] l : csv)
		{
			vals.add(new ArrayList<>(List.of(l)));
		}

		writeCSV(vals,file);

		//writeCSV((ArrayList<String[]>) Arrays.stream(csv).toList(), file);

		/*
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
		 */
	}
}
