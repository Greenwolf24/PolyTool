package io.github.greenwolf24.PolyTool.Files.CSV;

// Added in version 1.5.0 of the PolyTool library.
// Class version: 1.1.0
// Last modified for Library version: 1.5.1

import io.github.greenwolf24.PolyTool.Files.SimpleReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCsvFile
{
	public File csvFile;
	public String delimiter;
	
	// The purpose of this class is to provide a very simple wrapper for reading CSV files
	// The default DumbReader method will simply grab the file, and
	// return a 2d array of strings split by the chosen delimiter

	public ReadCsvFile(String file)
	{
		csvFile = new File(file);
		delimiter = ",";
	}
	public ReadCsvFile(File file)
	{
		csvFile = file;
		delimiter = ",";
	}

	public ReadCsvFile(String file, String delimiter)
	{
		csvFile = new File(file);
		this.delimiter = delimiter;
	}
	public ReadCsvFile(File file, String delimiter)
	{
		csvFile = file;
		this.delimiter = delimiter;
	}

	
	public String[][] getAsStringsArray()
	{
		return getAsStringsArray(false);
	}

	public String[][] getAsStringsArray(boolean skipFirstLine)
	{
		ArrayList<ArrayList<String>> records = new ArrayList<>();
		
		for(String line : SimpleReader.getAsStringLines(csvFile))
		{
			//records.add(line.split(delimiter));
			String[] linesplit = line.split(delimiter);
			ArrayList<String> input = new ArrayList<>();
			for(int i = 0;i < linesplit.length;i++)
			{
				if(linesplit[i].startsWith("\""))
				{
					String cell = linesplit[i];
					while(!cell.endsWith("\""))
					{
						i++;
						cell = cell + "," + linesplit[i];
					}
					cell = cell.substring(1,cell.length()-1);
					input.add(cell);
				}
				else
				{
					input.add(linesplit[i]);
				}
			}
			records.add(input);
		}


		// This is a clunky way of doing it, but it should work beautifully
		int offset = 0;
		if(skipFirstLine){offset++;}

		// this may cause issues if there is data later than the first line implies

		String[][] ret = new String[records.size()-offset][records.get(0).size()];
		
		for(int i = 0+offset;i < records.size();i++)
		{
			//if(!(skipFirstLine && i == 0))
			{
				String[] lr = new String[records.get(i).size()];
				for(int r = 0;r < records.get(i).size();r++)
				{
					lr[r] = records.get(i).get(r);
				}
				//ret[i] = (String[])records.get(i).toArray();
				ret[i-offset] = lr;
			}
		}
		
		return ret;
	}

	public ArrayList<ArrayList<String>> getAsStringsArrayList()
	{
		return getAsStringsArrayList(false);
	}
	public ArrayList<ArrayList<String>> getAsStringsArrayList(boolean skipFirstLine)
	{
		ArrayList<ArrayList<String>> records = new ArrayList<>();

		String[][] work = getAsStringsArray(skipFirstLine);
		for(String[] line : work)
		{
			ArrayList<String> linar = new ArrayList<>(List.of(line));
			//records.add((ArrayList<String>) Arrays.stream(line).toList());
			records.add(linar);
		}

		/*
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(delimiter);
				records.add((ArrayList<String>) Arrays.asList(values));
			}
		}
		catch(Exception ex){}
		 */

		return records;
	}
}
