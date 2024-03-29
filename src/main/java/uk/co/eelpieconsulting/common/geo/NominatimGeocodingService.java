package uk.co.eelpieconsulting.common.geo;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.NominatimClient;
import fr.dudie.nominatim.client.request.NominatimReverseRequest;
import fr.dudie.nominatim.model.Address;
import fr.dudie.nominatim.model.AddressElement;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.eelpieconsulting.common.geo.model.LatLong;
import uk.co.eelpieconsulting.common.geo.model.OsmId;
import uk.co.eelpieconsulting.common.geo.model.OsmType;
import uk.co.eelpieconsulting.common.geo.model.Place;

import java.io.IOException;
import java.util.List;


public class NominatimGeocodingService implements GeoCodingService {
	
	private final static Logger log = LogManager.getLogger(NominatimGeocodingService.class);

	private static final int RESOLVER_ZOOM_LEVEL = 17;
	
	private final Joiner commaJoiner = Joiner.on(", ").skipNulls();
	
	private final String nominatimUser;
	private final String nominatimUrl;
	
	public NominatimGeocodingService(String nominatimUser, String nominatimUrl) {
		this.nominatimUser = nominatimUser;
		this.nominatimUrl = nominatimUrl;
	}
	
	@Override
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
	
	@Override
	public Place loadPlaceByOsmId(OsmId osmId) {
		log.info("Resolving OSM id with Nominatim: " + osmId);
		try {
			final NominatimClient nominatimClient = getNominatimClient();			
			final String nominatimOsmType = osmId.getType().toString().toUpperCase();
			
			final NominatimReverseRequest query = new NominatimReverseRequest();
			query.setQuery(fr.dudie.nominatim.client.request.paramhelper.OsmType.valueOf(nominatimOsmType) , osmId.getId());
			
			final Address address = nominatimClient.getAddress(query);
			
			final boolean isCorrectlyResolvedAddress = address != null && address.getOsmId() != null && address.getOsmType() != null;
			return isCorrectlyResolvedAddress ? buildPlaceFromNominatimAddress(address) : null;
			
		} catch (IOException e) {
			log.error(e);
			return null;
		}
	}
	
	@Override
	public String resolveNameForPoint(LatLong point) {
		final NominatimClient nominatimClient = getNominatimClient();
		try {
			final Address addressOfPoint = nominatimClient.getAddress(point.getLongitude(), point.getLatitude(), RESOLVER_ZOOM_LEVEL);
			if (addressOfPoint != null) {
				return buildDisplayName(addressOfPoint);
			}
			return null;
			
		} catch (IOException e) {
			log.error("Error while resolving location", e);
			throw new RuntimeException(e);
		}
	}
	
	private String buildDisplayName(final Address address) {
		boolean finished = false;		
		final List<String> addressComponentsToDisplay = Lists.newArrayList();
		for (AddressElement addressElement : address.getAddressElements()) {			
			if (!finished) {
				addressComponentsToDisplay.add(addressElement.getValue());
			}
			if (addressElement.getKey().equals("city")) {
				finished = true;
			}
		}	
		return commaJoiner.join(addressComponentsToDisplay);
	}
	
	private NominatimClient getNominatimClient() {
		return new JsonNominatimClient(nominatimUrl, new DefaultHttpClient(), nominatimUser);
	}
	
	private Place buildPlaceFromNominatimAddress(Address result) {
		return new Place(result.getDisplayName(), new LatLong(result.getLatitude(), result.getLongitude()), 
				new OsmId(Long.parseLong(result.getOsmId()), OsmType.valueOf(result.getOsmType().toUpperCase())));
	}
	
}
