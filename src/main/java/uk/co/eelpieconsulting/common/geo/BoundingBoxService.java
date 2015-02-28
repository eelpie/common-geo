package uk.co.eelpieconsulting.common.geo;

import java.util.List;

import uk.co.eelpieconsulting.common.geo.model.BoundingBox;
import uk.co.eelpieconsulting.common.geo.model.LatLong;

public class BoundingBoxService {

	public BoundingBox getBoundingBoxFor(List<LatLong> points) {
		Double latLow = null;
		Double latHigh = null;
		Double lonLow = null;
		Double lonHigh = null;

		for (LatLong point : points) {
			latLow = latLow == null || point.getLatitude() < latLow ? point.getLatitude() : latLow;
			latHigh = latHigh == null || point.getLatitude() > latHigh ? point.getLatitude() : latHigh;
			lonLow = lonLow == null || point.getLongitude() < lonLow ? point.getLongitude() : lonLow;
			lonHigh = lonHigh == null || point.getLongitude() > lonHigh ? point.getLongitude() : lonHigh;
		}
		
		LatLong topLeft = new LatLong(latHigh, lonLow);
		LatLong bottomRight = new LatLong(latLow, lonHigh);
		return new BoundingBox(topLeft, bottomRight);
	}

}
