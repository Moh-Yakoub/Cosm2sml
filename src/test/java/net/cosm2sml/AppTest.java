package net.cosm2sml;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import net.cosm2sml.SensorMLEncoder.Impl.SensorMLDocumentEncoderImpl;
import net.cosm2sml.sensorml.Capability;
import net.cosm2sml.sensorml.CosmSensorML;
import net.cosm2sml.util.Constants;
import net.opengis.gml.TimePeriodType;
import net.opengis.sensorML.x101.AbstractProcessType;
import net.opengis.sensorML.x101.IdentificationDocument.Identification.IdentifierList.Identifier;
import net.opengis.sensorML.x101.KeywordsDocument.Keywords;
import net.opengis.sensorML.x101.SensorMLDocument;
import net.opengis.sensorML.x101.PositionDocument.Position;
import net.opengis.sensorML.x101.SensorMLDocument.SensorML.Member;
import net.opengis.sensorML.x101.SystemType;
import net.opengis.sensorML.x101.ValidTimeDocument.ValidTime;
import net.opengis.swe.x101.VectorType.Coordinate;

import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class AppTest {

	public static CosmSensorML cosmSensor;

	@Before
	public void setUp() {
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
		// set up location
		cosmSensor.setLongitude("1.5");
		cosmSensor.setLatitude("3.5");
		cosmSensor.setAltitude("1.3");
	}

	@org.junit.Test
	public void encodeThenTest() {
		try {
			SensorMLDocument doc = new SensorMLDocumentEncoderImpl()
					.encode(cosmSensor);
			/*
			 * Test for keywords
			 */
			if (doc.getSensorML().getMemberArray().length != 1)
				fail("Members length doesn't match");

			Member member = doc.getSensorML().getMemberArray()[0];
			AbstractProcessType type = member.getProcess();

			if (type == null)
				fail("Null value for ProcessType");

			Keywords[] keywords = type.getKeywordsArray();
			if (keywords.length > 1)
				fail("Wrong count of keywords");

			Keywords keys = keywords[0];

			if (keys.getKeywordList().getKeywordArray().length != cosmSensor
					.getKeywords().size())
				fail("Keywords doesn't match");

			// Get Keywordslist

			String[] filekeywords = keys.getKeywordList().getKeywordArray();

			for (int i = 0; i < filekeywords.length; i++)
				if (!filekeywords[i].equals(cosmSensor.getKeywords().get(i)))
					fail("Unmatched keywords");

			System.out.println("Keywords test successful!");

			// Check the long name identifier

			if (type.getIdentificationArray().length != 1)
				fail("wrong identifiers");

			Identifier identifier = type.getIdentificationArray()[0]
					.getIdentifierList().getIdentifierArray()[0];

			// test identifier UniqueId
			if (identifier.getTerm().getDefinition() == Constants.UNIQUE_ID_SCHEMA)
				if (identifier.getTerm().getValue() != cosmSensor
						.getUnique_id())
					fail("Not equal UniqueId");

			System.out.println("Testing an identifier successful !");

			ValidTime time = type.getValidTime();
			if (time == null)
				fail("Invalid ValidTime !");

			TimePeriodType period = time.getTimePeriod();
			if (!period.getBeginPosition().getStringValue()
					.equals(cosmSensor.getCreated_at()))
				fail("Invalid validTime:Begin dates doesn't match");
			if (!period.getEndPosition().getStringValue()
					.equals(cosmSensor.getEnd_at()))
				fail("Invalid validTime:End dates doesn't match");

			System.out.println("Test valid times successfull !");

			SystemType system = (SystemType) type;

			Position p = system.getPosition();
			Coordinate[] coordinates = p.getPosition().getLocation()
					.getVector().getCoordinateArray();
			for (Coordinate c : coordinates) {
				if (c.getName().equals("altitude"))
					if (c.getQuantity().getValue() != Double
							.parseDouble(cosmSensor.getAltitude()))
						fail("Unmatching altitude");
			}
			System.out.println("Succesfull altitude test");
		} catch (Exception e) {
			fail("fail on parsing");
		}

	}
}
