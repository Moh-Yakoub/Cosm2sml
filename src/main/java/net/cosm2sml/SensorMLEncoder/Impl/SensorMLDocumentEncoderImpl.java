package net.cosm2sml.SensorMLEncoder.Impl;

import java.util.List;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;

import net.cosm2sml.SensorMLEncoder.SensorMLDocumentEncoder;
import net.cosm2sml.sensorml.Capability;
import net.cosm2sml.sensorml.CosmSensorML;
import net.cosm2sml.util.Constants;
import net.opengis.gml.BooleanPropertyType;
import net.opengis.gml.StringOrRefType;
import net.opengis.gml.TimePeriodType;
import net.opengis.gml.impl.BooleanDocumentImpl;
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
import net.opengis.sensorML.x101.impl.CapabilitiesDocumentImpl;
import net.opengis.swe.x101.AbstractDataRecordType;
import net.opengis.swe.x101.BooleanDocument;
import net.opengis.swe.x101.DataComponentPropertyType;
import net.opengis.swe.x101.DataRecordDocument;
import net.opengis.swe.x101.DataRecordType;
import net.opengis.swe.x101.RecordType;
import net.opengis.swe.x101.impl.AbstractDataRecordTypeImpl;
import net.opengis.swe.x101.impl.DataRecordDocumentImpl;
import net.opengis.swe.x101.impl.DataRecordPropertyTypeImpl;
import net.opengis.swe.x101.impl.DataRecordTypeImpl;
import net.opengis.swe.x101.impl.RecordTypeImpl;

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

		Term t;

		/*
		 * UniqueID identifier
		 */
		if (ml.getUnique_id() != null) {
			Identifier uniqueid = list.addNewIdentifier();
			uniqueid.setName("uniqueID");
			t = uniqueid.addNewTerm();
			t.setDefinition(Constants.UNIQUE_ID_SCHEMA);
			t.setValue(ml.getUnique_id());
		}

		Identifier longname = list.addNewIdentifier();
		longname.setName("longName");
		t = longname.addNewTerm();
		t.setDefinition(Constants.LONG_NAME_SCHEMA);
		t.setValue(ml.getLong_name());

		/*
		 * Short name identifier
		 */
		if (ml.getShort_name() != null) {
			Identifier shortname = list.addNewIdentifier();
			shortname.setName("shortName");
			t = shortname.addNewTerm();
			t.setDefinition(Constants.SHORT_NAME_SCHEMA);
			t.setValue(ml.getShort_name());
		}

		/*
		 * Add valid date
		 */
		ValidTime time = sensor.addNewValidTime();
		TimePeriodType period = time.addNewTimePeriod();
		period.addNewBeginPosition().setStringValue(ml.getCreated_at());
		period.addNewEndPosition().setStringValue(ml.getEnd_at());

		Capabilities capabilities = sensor.addNewCapabilities();
	
		List<Capability> cosmcapabilities = ml.getCapabilities();
		DataRecordType recordtype = DataRecordType.Factory.newInstance();
		/*
		 * Set description
		 */
		StringOrRefType description = StringOrRefType.Factory.newInstance();
		description.setStringValue(Constants.RECORD_TYPE_SCHEMA);
		recordtype.setDescription(description);
		for (Capability c : cosmcapabilities) {
			DataComponentPropertyType field = recordtype.addNewField();
			if(c.getType().equals("boolean")){
				BooleanDocument.Boolean bool = field.addNewBoolean();
				
				bool.setDefinition(c.getName());
				bool.setValue(Boolean.parseBoolean(c.getValue()));
			}
			
		}
		capabilities.setAbstractDataRecord(recordtype);
		
		return SensorMLDocument.Factory.parse(document.toString());
	}

}
