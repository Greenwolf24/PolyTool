package io.github.greenwolf24.SwissArmyKnife.Color;

import java.util.LinkedHashMap;

// Added in version 1.0.0 of the SwissArmyKnife library.
// Class version: 1.0.0

public class FlightRadar24ColorRange
{
	// The color range
	// The key is the altitude in feet
	// The value is the simple color
	private LinkedHashMap<Integer,SimpleColor> colorMap;
	
	public FlightRadar24ColorRange()
	{
		// this is only an approximation of what FlightRadar24 does
		// I found a key in meters, but it appears like the actual check is done in feet
		
		colorMap = new LinkedHashMap<Integer,SimpleColor>();
		colorMap.put(42000, new SimpleColor(0xff, 0x00, 0x00));
		colorMap.put(41000, new SimpleColor(0xff, 0x00, 0xe4));
		colorMap.put(39000, new SimpleColor(0xd8, 0x00, 0xff));
		colorMap.put(37000, new SimpleColor(0xae, 0x00, 0xff));
		colorMap.put(35000, new SimpleColor(0x96, 0x00, 0xff));
		colorMap.put(33000, new SimpleColor(0x7f, 0x00, 0xff));
		colorMap.put(31000, new SimpleColor(0x60, 0x00, 0xff));
		colorMap.put(29000, new SimpleColor(0x4e, 0x00, 0xff));
		colorMap.put(27500, new SimpleColor(0x36, 0x00, 0xff));
		colorMap.put(26000, new SimpleColor(0x24, 0x00, 0xff));
		colorMap.put(24000, new SimpleColor(0x12, 0x00, 0xff));
		colorMap.put(22000, new SimpleColor(0x00, 0x1e, 0xff));
		colorMap.put(19000, new SimpleColor(0x00, 0x3c, 0xff));
		colorMap.put(17000, new SimpleColor(0x00, 0x78, 0xff));
		colorMap.put(14500, new SimpleColor(0x00, 0x96, 0xff));
		colorMap.put(13000, new SimpleColor(0x00, 0xa8, 0xff));
		colorMap.put(12000, new SimpleColor(0x00, 0xc0, 0xff));
		colorMap.put(11000, new SimpleColor(0x00, 0xea, 0xff));
		colorMap.put(9000, new SimpleColor(0x00, 0xff, 0xe4));
		colorMap.put(8000, new SimpleColor(0x00, 0xff, 0xd2));
		colorMap.put(6000, new SimpleColor(0x00, 0xff, 0x9c));
		colorMap.put(4800, new SimpleColor(0x00, 0xff, 0x72));
		colorMap.put(3800, new SimpleColor(0x00, 0xff, 0x36));
		colorMap.put(3200, new SimpleColor(0x00, 0xff, 0x0c));
		colorMap.put(2500, new SimpleColor(0x1e, 0xff, 0x00));
		colorMap.put(1900, new SimpleColor(0x42, 0xff, 0x00));
		colorMap.put(1200, new SimpleColor(0xcc, 0xff, 0x00));
		colorMap.put(700, new SimpleColor(0xf0, 0xff, 0x00));
		colorMap.put(400, new SimpleColor(0xff, 0xea, 0x00));
		colorMap.put(100, new SimpleColor(0xff, 0xe0, 0x62));
	}
	
	public SimpleColor getColor(int altitudeFeet)
	{
		SimpleColor color = new SimpleColor(0xff, 0xff, 0xff);
		
		for(int key : colorMap.keySet())
		{
			if(altitudeFeet <= key)
			{
				color = colorMap.get(key);
				break;
			}
		}
		
		return color;
	}
}
