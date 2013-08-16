package uk.co.eelpieconsulting.common.geo.model;

import java.io.Serializable;

public class Place implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
    private String address;
    private LatLong latLong;
    private OsmId osmId;
    
	public Place() {
	}
    
	public Place(String address, LatLong latLong, OsmId osmId) {
		this.address = address;
		this.latLong = latLong;
		this.osmId = osmId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public LatLong getLatLong() {
		return latLong;
	}
	public void setLatLong(LatLong latLong) {
		this.latLong = latLong;
	}
	
	public OsmId getOsmId() {
		return osmId;
	}
	public void setOsmId(OsmId osmId) {
		this.osmId = osmId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Place [address=" + address + ", latLong=" + latLong + ", osmId=" + osmId + "]";
	}
	
}
