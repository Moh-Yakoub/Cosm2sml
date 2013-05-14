package net.cosm2sml.helper;

import net.cosm2sml.CosmFeed;
import net.cosm2sml.sensorml.CosmSensorML;

public interface CosmFeedHelper {
	/*
	 * parses a feed given by id
	 */
	public CosmFeed parseFeed(int id);
	public CosmSensorML decodeFeed(CosmFeed feed);
}
