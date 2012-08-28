package uk.co.eelpieconsulting.common.geo;

public class LatLong {

	private final double latitude;
	private final double longitude;
	
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
