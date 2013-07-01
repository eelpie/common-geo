package uk.co.eelpieconsulting.common.geo.model;

import java.io.Serializable;

public class LatLong implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private double latitude;
	private double longitude;
	
	public LatLong() {
	}
	
	public LatLong(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	@Override
	public String toString() {
		return "LatLong [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}
