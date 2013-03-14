package uk.co.eelpieconsulting.common.geo;

import java.io.IOException;
import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.google.common.collect.Lists;

import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.NominatimClient;
import fr.dudie.nominatim.model.Address;

public class NominatimGeocodingService {
	
	private static Logger log = Logger.getLogger(NominatimGeocodingService.class);

	private final String nominatimUser;
	private final String nominatimUrl;
	
	public NominatimGeocodingService(String nominatimUser, String nominatimUrl) {
		this.nominatimUser = nominatimUser;
		this.nominatimUrl = nominatimUrl;
	}

	public List<Place> resolvePlaceName(String placeName) {
		log.info("Resolving address with Nominatim: " + placeName);
		final NominatimClient nominatimClient = getNominatimClient();
		try {
			final List<Address> results = nominatimClient.search(placeName);
			if (!results.isEmpty()) {
				List<Place> geocodes = Lists.newArrayList();
				for (Address result : results) {
					geocodes.add(buildPlaceFromNominatimAddress(result));
				}
				return geocodes;
			}
			
		} catch (IOException e) {
			log.warn("Exception while attempting to resolve '" + placeName + "': " + e.getMessage());
		}
		return null;
	}
	
	public Place loadPlaceByOsmId(OsmId osmId) {
		log.info("Resolving OSM id with Nominatim: " + osmId);
		try {
			final NominatimClient nominatimClient = getNominatimClient();
			Address address = nominatimClient.getAddress(osmId.getType(), osmId.getId());
			return address != null ? buildPlaceFromNominatimAddress(address) : null;
			
		} catch (IOException e) {
			log.error(e);
			return null;
		}
	}
	
	private NominatimClient getNominatimClient() {
		final NominatimClient nominatimClient = new JsonNominatimClient(nominatimUrl, new DefaultHttpClient(), nominatimUser, null, false, false);
		return nominatimClient;
	}
	
	private Place buildPlaceFromNominatimAddress(Address result) {
		return new Place(result.getDisplayName(), new LatLong(result.getLatitude(), result.getLongitude()), new OsmId(Long.parseLong(result.getOsmId()), result.getOsmType()));
	}
	
}
