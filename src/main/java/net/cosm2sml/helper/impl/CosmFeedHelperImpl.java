package net.cosm2sml.helper.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import net.cosm2sml.CosmDataStream;
import net.cosm2sml.CosmFeed;
import net.cosm2sml.helper.CosmFeedHelper;
import net.cosm2sml.sensorml.Capability;
import net.cosm2sml.sensorml.CosmSensorML;
import net.cosm2sml.util.Constants;

public class CosmFeedHelperImpl implements CosmFeedHelper {

	public CosmFeed parseFeed(int id) {
		// TODO Auto-generated method stub
		try {
			HttpGet get = new HttpGet("http://api.cosm.com/v2/feeds/" + id
					+ "?key=" + Constants.API_KEY);
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(get);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String s = "";
			String k = "";
			while ((s = br.readLine()) != null)
				k += s;
			if (k.length() == 0)
				return null;
			else {
				Gson gson = new Gson();
				CosmFeed feed = gson.fromJson(k, CosmFeed.class);
				return feed;

			}

		} catch (Exception e) {
			return null;
		}
	}

	public CosmSensorML decodeFeed(CosmFeed feed) {
		// TODO Auto-generated method stub
		int id = feed.id;
		String longname = feed.description;
		String shortname = feed.id+"";
		
		//keywords is the list of tags;
		
		String created_at = feed.created;
		String end_at = "";
		
		//
		
		List<Capability> capabilities = new ArrayList<Capability>();
		//
		for(CosmDataStream stream : feed.datastreams)
			capabilities.add(new Capability(stream.id,"String",stream.current_value));
		
		//add status
		
		capabilities.add(new Capability("status","boolean",feed.status));
		
		CosmSensorML ml = new CosmSensorML(id+"",longname,shortname,created_at,end_at, capabilities, feed.tags);
		return ml;
		
	}

}
