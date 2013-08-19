package uk.co.eelpieconsulting.common.geo.model;

import java.io.Serializable;

public class OsmId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	private OsmType type;
	
	public OsmId() {
	}

	public OsmId(long id, OsmType type) {
		this.id = id;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OsmType getType() {
		return type;
	}

	public void setType(OsmType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "OsmId [id=" + id + ", type=" + type + "]";
	}
	
}
