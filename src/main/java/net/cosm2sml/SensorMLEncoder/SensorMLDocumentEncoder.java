package net.cosm2sml.SensorMLEncoder;

import net.cosm2sml.sensorml.CosmSensorML;
import net.opengis.sensorML.x101.SensorMLDocument;

public interface SensorMLDocumentEncoder {
	public SensorMLDocument encode(CosmSensorML sensor)throws Exception;
}
