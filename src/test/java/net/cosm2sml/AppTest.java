package net.cosm2sml;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import net.cosm2sml.SensorMLEncoder.Impl.SensorMLDocumentEncoderImpl;
import net.cosm2sml.sensorml.Capability;
import net.cosm2sml.sensorml.CosmSensorML;
import net.opengis.sensorML.x101.AbstractProcessType;
import net.opengis.sensorML.x101.SensorMLDocument;
import net.opengis.sensorML.x101.SensorMLDocument.SensorML.Member;

import org.junit.Before;


/**
 * Unit test for simple App.
 */
public class AppTest {

	public static CosmSensorML cosmSensor;

	@Before
	public  void setUp() {
		/*
		 * Set up the CosmSensorML object
		 */
		// We start with a simple capability
		Capability capability = new Capability("status", "boolean", "true");
		List<Capability> capabilites = new ArrayList<Capability>();
		capabilites.add(capability);

		// We fill in keywords
		List<String> keywords = new ArrayList<String>();
		keywords.add("testkeyword");
		keywords.add("test");
		cosmSensor = new CosmSensorML("urn:ogc:object:feature:testsensor",
				"A Test Sensor", "TEST", "2013-15-5", "2013-16-5", capabilites,
				keywords);
	}

	@org.junit.Test
	public  void encodeThenTest() {
		try{
		SensorMLDocument doc = new SensorMLDocumentEncoderImpl().encode(cosmSensor);
		/*
		 * Test for keywords
		 */
		assert(doc.getSensorML().getMemberArray().length == 1);
		Member member = doc.getSensorML().getMemberArray()[0];
		AbstractProcessType type = member.getProcess();
		assert(type !=null);
		
		}catch(Exception e){
			fail("fail on parsing");
		}
		
	}
}
