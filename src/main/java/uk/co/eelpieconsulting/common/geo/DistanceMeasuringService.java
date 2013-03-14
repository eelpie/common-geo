package uk.co.eelpieconsulting.common.geo;

import geo.google.datamodel.GeoAltitude;
import geo.google.datamodel.GeoCoordinate;
import geo.google.datamodel.GeoUtils;

import org.apache.log4j.Logger;

public class DistanceMeasuringService {
	
	private static Logger log = Logger.getLogger(DistanceMeasuringService.class);
	
	public double getDistanceBetween(LatLong here, LatLong there) {
		final GeoCoordinate hereCoordinate = new GeoCoordinate(here.getLatitude(), here.getLongitude(), new GeoAltitude(0));
		final GeoCoordinate thereCoordinate = new GeoCoordinate(there.getLatitude(), there.getLongitude(), new GeoAltitude(0));
		final double distanceBetweenInKm = GeoUtils.distanceBetweenInKm(hereCoordinate, thereCoordinate);
		log.debug("Distance from " + here + " to " + there  + " is " + distanceBetweenInKm);
		return distanceBetweenInKm;
	}
	
}
