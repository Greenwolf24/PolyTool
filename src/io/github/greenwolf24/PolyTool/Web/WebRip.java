package io.github.greenwolf24.PolyTool.Web;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// Added in version 1.2.0 of the PolyTool library.
// Class version: 1.1.0
// Last modified for Library version: 1.4.0

public class WebRip
{
	// This class is to make simple requests to websites.
	// The usecase this was intended for was to get JSON API data.
	// This is not intended to be a general purpose class,
	// as I have not tested what it would do with other websites,
	// My theory is that it would just rip the HTML from the website, but again... not tested.
	// This simple just grabs the website fed in, and returns one string of all the data.
	// I definitely ripped this from somewhere a long time ago, but I don't remember where. Probably somewhere on StackOverflow.
	private static String getAsString(String inURL,int wait)
	{
		String url = inURL;
		URL u;
		InputStream is = null;
		DataInputStream dis;
		String s;
		String fin = "";
		try
		{
			u = new URL(url);
			HttpURLConnection uConned = (HttpURLConnection) u.openConnection();
			uConned.setRequestMethod("GET");
			uConned.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:73.0) Gecko/20100101 Firefox/73.0");
			uConned.connect();
			
			if(wait > 0)
			{
				// Wait for the website to load.
					// Wait for the website to load.
					// This is a hacky way to do this, but it works.
					// Wait 1 second, then try again.
					try
					{
						Thread.sleep(1000 * wait);
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
			}
			
			//System.out.println("Response code: " + uConned.getResponseCode());
			//System.out.println("Response Message\n++++++++++++++++++++++++++\n" + uConned.getResponseMessage());
			is = uConned.getInputStream();
			dis = new DataInputStream(new BufferedInputStream(is));
			while((s = dis.readLine()) != null)
			{
				//System.out.println(s);
				fin = fin + s;
			}
			return fin;
		} catch(MalformedURLException mue)
		{
			System.err.println("Ouch - a MalformedURLException happened.");
			mue.printStackTrace();
			return null;
		} catch(IOException ioe)
		{
			System.err.println("Oops- an IOException happened.");
			ioe.printStackTrace();
			return null;
		} finally
		{
			try
			{
				is.close();
			} catch(Exception e)
			{
			}
		}
	}
	
	// it appears as if the "wait" I introduced doesn't make a difference
	// it is likely that it is a partial fuck-up on my end
	// I have made this wrapper method the primary access method and privatised the wait method
	// If I or anyone else can make the page load after a wait in the main method,
	// make it public, and it will be accessible.
	public static String getAsString(String inURL)
	{
		return getAsString(inURL,0);
	}
}
