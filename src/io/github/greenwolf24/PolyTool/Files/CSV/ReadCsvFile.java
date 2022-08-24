package io.github.greenwolf24.PolyTool.Files.CSV;

// Added in version 1.5.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.5.0

import io.github.greenwolf24.PolyTool.Files.SimpleReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadCsvFile
{
	public File csvFile;
	public String delimiter;
	
	// The purpose of this class is to provide a very simple wrapper for reading CSV files
	// The default DumbReader method will simply grab the file, and
	// return a 2d array of strings split by the chosen delimiter
	
	public ReadCsvFile(File file)
	{
		csvFile = file;
		// TODO!!! Default Delimiter does not exist in V1.5.0-T02
		delimiter = ",";
	}
	
	public ReadCsvFile(File file, String delimiter)
	{
		csvFile = file;
		this.delimiter = delimiter;
	}
	
	
	/* TODO
		Before releasing this
		make it so that as many of the actual calls are just wrappers
		The only real parser should be into an ArrayList<ArrayList<Object>>
		the wrappers will convert as needed
	
	 */
	
	public String[][] getAsStringsArray()
	{
		return getAsStringsArray(false);
	}
	
	//TODO
	// Implement a method of allowing quotes to prevent splitting
	// This can likely be taken from the method I am working on within my
	// AirNerdTools AirportInitializer
	public String[][] getAsStringsArray(boolean skipFirstLine)
	{
		ArrayList<String[]> records = new ArrayList<>();
		
		for(String line : SimpleReader.getAsStringLines(csvFile))
		{
			records.add(line.split(delimiter));
		}
		
		// this may cause issues if there is data later than the first line implies
		
		String[][] ret = new String[records.size()][records.get(0).length];
		
		for(int i = 0;i < records.size();i++)
		{
			if(!(skipFirstLine && i == 0))
			{
				ret[i] = records.get(i);
			}
		}
		
		return ret;
	}
	
	//TODO
	// This doesn't work
	// all outputs seem to have length of zero
	public ArrayList<ArrayList<String>> getAsStringsArrayList()
	{
		ArrayList<ArrayList<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(delimiter);
				records.add((ArrayList<String>) Arrays.asList(values));
			}
		}
		catch(Exception ex){}
		
		return records;
	}
}
