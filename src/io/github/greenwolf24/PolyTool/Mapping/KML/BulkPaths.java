package io.github.greenwolf24.PolyTool.Mapping.KML;

// Added in version 1.5.0 of the PolyTool library.
// Class version: 0.5.0
// Last modified for Library version: 1.5.0

//import io.github.greenwolf24.PolyTool.Color.Ranges.FlightRadar24AltitudeRange;
import io.github.greenwolf24.PolyTool.Mapping.Position;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class BulkPaths
{
	//TODO
	// note: this is in beta, implementations using this section will likely change in future updates
	
	private static String filePath = "";
	
	public BulkPaths(String filePathOut)
	{
		filePath = filePathOut;
		if(!filePath.endsWith("/"))
		{
			filePath = filePath + "/";
		}
	}
	
	public void fullMake(ArrayList<ArrayList<Position>> paths,String name,String kmlColor)
	{
		String top0 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
				"  <Document>\n" +
				"    <name>NAME</name>";
		
		//ArrayList<String> folders = new ArrayList<>();
		
		String bottom = "  </Document>\n" +
				"</kml>";
		
		StringBuilder sb = new StringBuilder();
		sb.append(top0.replace("NAME",name));
		
		for(ArrayList<Position> positions : paths)
		{
			sb.append(folderLongPath(positions,name,kmlColor));
		}
		
		sb.append(bottom);
		
		String ret = sb.toString();
		
		try
		{
			File file = new File(filePath + name + ".kml");
			//file.createNewFile();
			FileWriter fw = new FileWriter(file);
			fw.write(ret);
			fw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//return sb.toString();
	}
	
	private String onePlacemark(Position position1, Position position2, String name,String kmlColor)
	{
		String base = "<Placemark>\n" +
				"        <Style>\n" +
				"          <LineStyle>\n" +
				"            <color>COLOR</color>\n" + // AA RR GG BB, no #
				"            <width>2</width>\n" +
				"          </LineStyle>\n" +
				"        </Style>\n" +
				"        <MultiGeometry>\n" +
				"          <LineString>\n" +
				"            <tessellate>1</tessellate>\n" +
				"            <altitudeMode>MODE</altitudeMode>\n" + // orig: absolute
				"            <coordinates>COORDS</coordinates>\n" + // orig: -80.269379,25.913589,38.1 -80.263947,25.913862,121.92    Lat,Lon,AltMeters
				"          </LineString>\n" +
				"        </MultiGeometry>\n" +
				"        <name>NAME</name>\n" + // Orig: P-50
				"      </Placemark>";
		
		//if(position1.OnGround && position2.OnGround)
		{
			base = base.replace("MODE","clampToGround");
			base = base.replace("COLOR",kmlColor);
			// because we are on the ground, we can fill the altitude as 0
			base = base.replace("COORDS",position1.Longitude + "," + position1.Latitude + ",0 " + position2.Longitude + "," + position2.Latitude + ",0");
			base = base.replace("NAME",name);
		}
		
		return base;
	}
	
	private String folderLongPath(ArrayList<Position> positions, String name,String kmlColor)
	{
		
		String top1 = "<Folder>\n" +
				"      <name>Trail</name>";
		
		String bottom = " </Folder>\n";
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(top1);
		for(int i = 0; i < positions.size() - 1; i++)
		{
			Position position1 = positions.get(i);
			Position position2 = positions.get(i + 1);
			
			sb.append(onePlacemark(position1, position2, "P-" + i, kmlColor));
		}
		
		sb.append(bottom);
		
		return sb.toString();
	}
}
