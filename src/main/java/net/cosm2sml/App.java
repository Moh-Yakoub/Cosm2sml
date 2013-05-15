package net.cosm2sml;

import java.util.ArrayList;
import java.util.List;

import net.cosm2sml.SensorMLEncoder.SensorMLDocumentEncoder;
import net.cosm2sml.SensorMLEncoder.Impl.SensorMLDocumentEncoderImpl;
import net.cosm2sml.sensorml.Capability;
import net.cosm2sml.sensorml.CosmSensorML;

public class App {
	public static void main(String[] args) throws Exception {
		/*
		 * CosmFeedHelperImpl helper = new CosmFeedHelperImpl(); CosmFeed feed =
		 * helper.parseFeed(126768); CosmSensorML ml = helper.decodeFeed(feed);
		 * // use ml
		 */
		Capability c = new Capability("urn:ogc:def:property:OGC:1.0:isActive", "boolean", "true");
		List<Capability> caps = new ArrayList<Capability>();
		caps.add(c);
		CosmSensorML ml = new CosmSensorML("urn:ogc:object:feature:testsensor",
				"A Test Sensor", "TEST", "2012", "2013",
			caps, new ArrayList<String>());
		ml.setLongitude("3.5");
		ml.setLatitude("1.4");
		ml.setAltitude("2.1");

		SensorMLDocumentEncoder encoder = new SensorMLDocumentEncoderImpl();
		System.out.println(encoder.encode(ml));
		
	}
}
