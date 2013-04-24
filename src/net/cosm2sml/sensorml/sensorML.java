package net.cosm2sml.sensorml;

import java.util.List;

public class sensorML {
	/*
	 * Encapsulates a sensorML translation for Cosm Feed
	 * Can be used later to construct XML representation
	 */
	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}

	public String getLong_name() {
		return long_name;
	}

	public void setLong_name(String long_name) {
		this.long_name = long_name;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getEnd_at() {
		return end_at;
	}

	public void setEnd_at(String end_at) {
		this.end_at = end_at;
	}

	public List<Capability> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<Capability> capabilities) {
		this.capabilities = capabilities;
	}

	private String unique_id;
	private String long_name;
	private String short_name;
	private String created_at;
	private String end_at;
	private List<Capability> capabilities;
	private List<String> keywords;

	public sensorML(String unique_id, String long_name, String short_name,
			String created_at, String end_at, List<Capability> capabilities,
			List<String> keywords) {
		super();
		this.unique_id = unique_id;
		this.long_name = long_name;
		this.short_name = short_name;
		this.created_at = created_at;
		this.end_at = end_at;
		this.capabilities = capabilities;
		this.keywords = keywords;
	}

	// Other properties can be put on demand

}
