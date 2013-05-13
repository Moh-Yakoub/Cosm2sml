package net.cosm2sml.helper;

import net.cosm2sml.CosmFeed;
import net.cosm2sml.sensorml.sensorML;

public interface CosmFeedHelper {
	/*
	 * parses a feed given by id
	 */
	public CosmFeed parseFeed(int id);
	public sensorML decodeFeed(CosmFeed feed);
}
