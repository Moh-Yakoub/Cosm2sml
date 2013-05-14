package net.cosm2sml;


import net.cosm2sml.SensorMLEncoder.SensorMLDocumentEncoder;
import net.cosm2sml.SensorMLEncoder.Impl.SensorMLDocumentEncoderImpl;
import net.cosm2sml.helper.impl.CosmFeedHelperImpl;
import net.cosm2sml.sensorml.CosmSensorML;


public class App {
	public static void main(String[] args) throws Exception {
		CosmFeedHelperImpl helper = new CosmFeedHelperImpl();
		CosmFeed feed = helper.parseFeed(126768);
		CosmSensorML ml = helper.decodeFeed(feed);
		// use ml
		
		SensorMLDocumentEncoder encoder = new SensorMLDocumentEncoderImpl();
		System.out.println(encoder.encode(ml));

	}
}
