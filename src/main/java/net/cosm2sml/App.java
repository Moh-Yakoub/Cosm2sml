package net.cosm2sml;

import java.util.List;

import net.cosm2sml.helper.impl.CosmFeedHelperImpl;
import net.cosm2sml.sensorml.sensorML;
import net.cosm2sml.util.Constants;
import net.opengis.sensorML.x101.IdentificationDocument.Identification;
import net.opengis.sensorML.x101.IdentificationDocument.Identification.IdentifierList;
import net.opengis.sensorML.x101.IdentificationDocument.Identification.IdentifierList.Identifier;
import net.opengis.sensorML.x101.KeywordsDocument.Keywords;
import net.opengis.sensorML.x101.KeywordsDocument.Keywords.KeywordList;
import net.opengis.sensorML.x101.SensorMLDocument;
import net.opengis.sensorML.x101.SensorMLDocument.SensorML;
import net.opengis.sensorML.x101.TermDocument;
import net.opengis.sensorML.x101.TermDocument.Term;
import net.opengis.sensorML.x101.impl.SensorMLDocumentImpl;
import net.opengis.sensorML.x101.impl.SensorMLDocumentImpl.SensorMLImpl;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		CosmFeedHelperImpl helper = new CosmFeedHelperImpl();
		CosmFeed feed = helper.parseFeed(126768);
		sensorML ml = helper.decodeFeed(feed);
		// use ml

		SensorML sensor = SensorML.Factory.newInstance();
		/*
		 * Add keywords
		 */
		Keywords keywords = sensor.addNewKeywords();
		KeywordList keywords_list = keywords.addNewKeywordList();

		List<String> feed_keywords = ml.getKeywords();
		for(String s:feed_keywords)
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

		System.out.println(sensor.toString());

	}
}
