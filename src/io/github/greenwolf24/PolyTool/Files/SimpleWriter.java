package io.github.greenwolf24.PolyTool.Files;

// Added in version 1.5.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.5.0

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleWriter
{
	public static void writeString(String str,String file)
	{
		writeString(str,new File(file));
	}
	public static void writeString(String str, File file)
	{
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(str);
			bw.close();
		}catch(IOException ioException){}
	}
}
