package net.cosm2sml;

import java.util.List;

/*
 * This class encapsulates the feed bean ,  it's set to public to
 * make it easy for Gson to parse into java beans directly;
 */
public class CosmFeed {
	public int id;
	public String title;
	public List<String> tags;
	public String description;
	public String status;
	public String updated;
	public String created;
	public String creator;
	public String feed;
	public List<CosmDataStream> datastreams;

}
