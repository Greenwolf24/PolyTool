package io.github.greenwolf24.PolyTool.Mapping.KML;

// Added in version 1.4.0 of the PolyTool library.
// Class version: 1.1.0
// Last modified for Library version: 1.4.0

import io.github.greenwolf24.PolyTool.Color.SimpleColor;
import io.github.greenwolf24.PolyTool.Mapping.Position;
import io.github.greenwolf24.PolyTool.Mapping.Util;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class PathMaker
{
	private static String filePath = "";
	
	public PathMaker(String filePathOut)
	{
		filePath = filePathOut;
		if(!filePath.endsWith("/"))
		{
			filePath = filePath + "/";
		}
	}
	
	/*
	public static void main(String[] args)
	{
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(71, 50, 300));
		positions.add(new Position(75, 52, 3000));
		positions.add(new Position(77, 40, 6000));
		
		PathMaker pm = new PathMaker("data/Output");
		pm.makePathLine(positions, "Test",false);
		//makePathLine(positions, "Test");
	}
	//*/
	
	// This is what I call a very dirty method.
	// This is not at all the proper way to do this, but it works really effin well.
	private String makePathLine(ArrayList<Position> positions, boolean altitude)
	{
		String path = "";
		for(int i = 0; i < positions.size(); i++)
		{
			path += positions.get(i).getLongitude() + "," + positions.get(i).getLatitude();// + "," //+ positions.get(i).getAlt() + " ";
			if(altitude)
			{
				path += "," + positions.get(i).getAltitude();
			}
			path += " ";
		}
		return path;
	}
	
	public void makePathLine(ArrayList<Position> positions, String name) {makePathLine(positions, name,Util.allHaveAltitudes(positions), new SimpleColor(250,0,0));}
	public void makePathLine(ArrayList<Position> positions, String name, boolean altitude) {makePathLine(positions, name, altitude, new SimpleColor(255,0,0));}
	public void makePathLine(ArrayList<Position> positions, String name, SimpleColor color) {makePathLine(positions, name, Util.allHaveAltitudes(positions), color);}
	public void makePathLine(ArrayList<Position> positions, String name, boolean altitude, SimpleColor color)
	{
		String ret = hardCodeExampleFile(name, makePathLine(positions,altitude), altitude, color.kmlFormat());
		//ret.replace("FILENAME",name);
		//ret.replace("POSITIONS", makePathLine(positions));
		/*
		// get the example file
			No longer needed, Hard coded should work for now
		try
		{
			ArrayList<String> lines = new ArrayList<String>();
			File file = new File("data/Example.kml");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null)
			{
				lines.add(line);
			}
			br.close();
			for(int i = 0; i < lines.size(); i++)
			{
				if(lines.get(i).contains("FILENAME"))
				{
					lines.set(i, lines.get(i).replace("FILENAME", name));
				}
				if(lines.get(i).contains("POSITIONS"))
				{
					lines.set(i, lines.get(i).replace("POSITIONS", makePathLine(positions)));
				}
				ret += lines.get(i) + "\n";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//*/
		
		//System.out.println(ret);
		
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
		
		//return ret;
	}
	
	
	public void makeCircleAround(Position center,double radiusM,String name) {makeCircleAround(center, radiusM, name,center.hasAltitude,new SimpleColor(255,0,0));}
	public void makeCircleAround(Position center,double radiusM,String name,boolean altitude) {makeCircleAround(center, radiusM, name,altitude,new SimpleColor(255,0,0));}
	public void makeCircleAround(Position center,double radiusM,String name,SimpleColor color) {makeCircleAround(center, radiusM, name,center.hasAltitude,color);}
	public void makeCircleAround(Position center,double radiusM,String name,boolean altitude,SimpleColor color)
	{
		ArrayList<Position> points = Util.circleAroundPosition(center, radiusM);
		
		// I mean... theoretically there should be some way of having a truly connected circle...
		// but it looks like google is just sticking one point at the start and end
		// so... I will do that
		points.add(points.get(0));
		
		makePathLine(points,name,altitude,color);
	}
	
	
	private String hardCodeExampleFile(String name, String path)
	{
		return hardCodeExampleFile(name, path, true, "ff0000ff");
	}
	
	private String hardCodeExampleFile(String name, String path, boolean altitude)
	{
		return hardCodeExampleFile(name, path, altitude, "ff0000ff");
	}
	
	// This might be useful for any kind of use case where
	// the example file is not carried with the running class
	private String hardCodeExampleFile(String name,String positions,Boolean elevation,String color)
	{
		String ret =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" xmlns:kml=\"http://www.opengis.net/kml/2.2\" xmlns:atom=\"http://www.w3.org/2005/Atom\">\n" +
						"<Document>\n" +
						"\t<name>"+name+"</name>\n" +
						"\t<Style id=\"inline\">\n" +
						"\t\t<LineStyle>\n" +
						"\t\t\t<color>"+color+"</color>\n" +
						"\t\t\t<width>2</width>\n" +
						"\t\t</LineStyle>\n" +
						"\t\t<PolyStyle>\n" +
						"\t\t\t<fill>0</fill>\n" +
						"\t\t</PolyStyle>\n" +
						"\t</Style>\n" +
						"\t<Style id=\"inline0\">\n" +
						"\t\t<LineStyle>\n" +
						"\t\t\t<color>"+color+"</color>\n" +
						"\t\t\t<width>2</width>\n" +
						"\t\t</LineStyle>\n" +
						"\t\t<PolyStyle>\n" +
						"\t\t\t<fill>0</fill>\n" +
						"\t\t</PolyStyle>\n" +
						"\t</Style>\n" +
						"\t<StyleMap id=\"inline1\">\n" +
						"\t\t<Pair>\n" +
						"\t\t\t<key>normal</key>\n" +
						"\t\t\t<styleUrl>#inline</styleUrl>\n" +
						"\t\t</Pair>\n" +
						"\t\t<Pair>\n" +
						"\t\t\t<key>highlight</key>\n" +
						"\t\t\t<styleUrl>#inline0</styleUrl>\n" +
						"\t\t</Pair>\n" +
						"\t</StyleMap>\n" +
						"\t<Placemark>\n" +
						"\t\t<name>"+name+"</name>\n" +
						"\t\t<styleUrl>#inline1</styleUrl>\n" +
						"\t\t<LineString>\n" +
						"\t\t\t<tessellate>1</tessellate>\n";
		if(elevation)
		{
			ret = ret + "\t\t\t<altitudeMode>absolute</altitudeMode>\n";
		}
		
		ret = ret + "\t\t\t<coordinates>\n" +
				"\t\t\t\t"+positions+"\n" +
				"\t\t\t</coordinates>\n" +
				"\t\t</LineString>\n" +
				"\t</Placemark>\n" +
				"</Document>\n" +
				"</kml>\n";
		return ret;
	}
}
