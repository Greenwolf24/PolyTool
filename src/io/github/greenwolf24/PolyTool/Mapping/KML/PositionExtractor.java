package io.github.greenwolf24.PolyTool.Mapping.KML;

// Added in version 1.5.0 of the PolyTool library.
// Class version: 1.0.0
// Last modified for Library version: 1.5.0

import io.github.greenwolf24.PolyTool.Mapping.Position;

import javax.annotation.processing.FilerException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class PositionExtractor
{
	// This class is to serve multiple functions of extracting coordinates
	// from a KML file. Example would be to dump a list of Position from a KML path
	
	public static ArrayList<Position> dirtyPathExtract(String KMLString)
	{
		// This is a very dirty method of attempting to extract coordinates
		// This will work by just finding the <coordinates> and </coordinates>,
		// taking the line between them, splitting it by spaces,
		// then parse each section properly
		// This is not a universal method, and likely will not work
		// for all KML type files
		
		ArrayList<Position> points = new ArrayList<>();
		boolean useAltitude = KMLString.contains("<altitudeMode>absolute</altitudeMode>");
		
		int coordStartIndex = KMLString.indexOf("<coordinates>");
		int coordEndIndex   = KMLString.indexOf("</coordinates>");
		
		String workLine = KMLString.substring(coordStartIndex,coordEndIndex);
		workLine = workLine.replaceAll("\t","");
		workLine = workLine.replaceAll("\n","");
		workLine = workLine.replaceAll("<","");
		workLine = workLine.replaceAll(">","");
		workLine = workLine.replaceAll("/","");
		workLine = workLine.replaceAll("coordinates","");
		
		for(String coord : workLine.split(" "))
		{
			double lon = Double.parseDouble(coord.split(",")[0]);
			double lat = Double.parseDouble(coord.split(",")[1]);
			double alt = Double.parseDouble(coord.split(",")[2]);
			
			Position pos = new Position(lat,lon);
			
			if(useAltitude)
			{
				pos.setAltitude(alt);
			}
			
			points.add(pos);
		}
		
		return points;
	}
	
	public static ArrayList<Position> dirtyPathFileExtract(File file) throws IOException
	{
		StringBuilder builder = new StringBuilder();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
			String currentLine;
			while ((currentLine = br.readLine()) != null)
			{
				builder.append(currentLine).append("\n");
			}
		}
		
		return dirtyPathExtract(builder.toString());
	}
}
