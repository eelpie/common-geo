package uk.co.eelpieconsulting.common.geo;

import uk.co.eelpieconsulting.common.geo.model.LatLong;
import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.OSRef;

public class OSRefConvertor {
	
	public LatLong toLatLong(double easting, double northing) {
		final OSRef ref = new OSRef(easting, northing);
		final LatLng latLng = ref.toLatLng();
		latLng.toWGS84();
		return new LatLong(latLng.getLatitude(), latLng.getLongitude());
	}

}
