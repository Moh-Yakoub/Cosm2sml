package net.cosm2sml.SensorMLEncoder.Impl;

import java.util.List;

import net.cosm2sml.SensorMLEncoder.SensorMLDocumentEncoder;
import net.cosm2sml.sensorml.Capability;
import net.cosm2sml.sensorml.CosmSensorML;
import net.cosm2sml.util.Constants;
import net.opengis.gml.TimePeriodType;
import net.opengis.sensorML.x101.SensorMLDocument;
import net.opengis.sensorML.x101.CapabilitiesDocument.Capabilities;
import net.opengis.sensorML.x101.IdentificationDocument.Identification;
import net.opengis.sensorML.x101.IdentificationDocument.Identification.IdentifierList;
import net.opengis.sensorML.x101.IdentificationDocument.Identification.IdentifierList.Identifier;
import net.opengis.sensorML.x101.KeywordsDocument.Keywords;
import net.opengis.sensorML.x101.KeywordsDocument.Keywords.KeywordList;
import net.opengis.sensorML.x101.SensorMLDocument.SensorML;
import net.opengis.sensorML.x101.TermDocument.Term;
import net.opengis.sensorML.x101.ValidTimeDocument.ValidTime;
import net.opengis.swe.x101.AbstractDataRecordType;

public class SensorMLDocumentEncoderImpl implements SensorMLDocumentEncoder {

	public SensorMLDocument encode(CosmSensorML ml) throws Exception {
		// TODO Auto-generated method stub
		SensorMLDocument document = SensorMLDocument.Factory.newInstance();
		SensorML sensor = document.addNewSensorML();
		/*
		 * Add keywords
		 */
		Keywords keywords = sensor.addNewKeywords();
		KeywordList keywords_list = keywords.addNewKeywordList();

		List<String> feed_keywords = ml.getKeywords();
		for (String s : feed_keywords)
			keywords_list.addKeyword(s);

		Identification identification = sensor.addNewIdentification();
		/*
		 * Identification list
		 */
		IdentifierList list = identification.addNewIdentifierList();
		Identifier longname = list.addNewIdentifier();
		Term t = longname.addNewTerm();
		t.setDefinition(Constants.LONG_NAME_SCHEMA);
		t.setValue(ml.getLong_name());

		/*
		 * Short name identifier
		 */
		Identifier shortname = list.addNewIdentifier();
		t = shortname.addNewTerm();
		t.setDefinition(Constants.SHORT_NAME_SCHEMA);
		t.setValue(ml.getShort_name());

		/*
		 * Add valid date
		 */
		ValidTime time = sensor.addNewValidTime();
		TimePeriodType period = time.addNewTimePeriod();
		period.addNewBeginPosition().setStringValue(ml.getCreated_at());
		period.addNewEndPosition().setStringValue(ml.getEnd_at());

		Capabilities capabilities = sensor.addNewCapabilities();
		List<Capability> cosmcapabilities = ml.getCapabilities();
		for (Capability c : cosmcapabilities) {
			AbstractDataRecordType type = capabilities
					.addNewAbstractDataRecord();
			type.addNewDescription().setStringValue(c.getName());
			//
			/*
			 * Other props to be added later
			 */

		}
		return SensorMLDocument.Factory.parse(document.toString());
	}

}
