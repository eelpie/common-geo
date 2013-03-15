package uk.co.eelpieconsulting.common.geo;

import java.util.List;

import uk.co.eelpieconsulting.common.geo.model.LatLong;
import uk.co.eelpieconsulting.common.geo.model.OsmId;
import uk.co.eelpieconsulting.common.geo.model.Place;

public interface GeoCodingService {

	public List<Place> resolvePlaceName(String placeName);
	public Place loadPlaceByOsmId(OsmId osmId);
	public String resolveNameForPoint(LatLong point);

}