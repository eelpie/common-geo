package uk.co.eelpieconsulting.common.geo;

import java.io.IOException;
import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.NominatimClient;
import fr.dudie.nominatim.model.Address;
import fr.dudie.nominatim.model.AddressElement;

public class NominatimGeoResolveService implements GeoResolveService {
	
	private static Logger log = Logger.getLogger(NominatimGeoResolveService.class);

	private static final int RESOLVER_ZOOM_LEVEL = 17;
	
	private final Joiner commaJoiner = Joiner.on(", ").skipNulls();
	
	private final String nominatimEmail;
	private final String nominatimUrl;
	
	public NominatimGeoResolveService(String nominatimEmail, String nominatimUrl) {
		this.nominatimEmail = nominatimEmail;
		this.nominatimUrl = nominatimUrl;
	}
	
	@Override
	public String resolvePointName(double latitude, double longitude) {
		final NominatimClient nominatimClient = new JsonNominatimClient(nominatimUrl, new DefaultHttpClient(), nominatimEmail, null, false, false);
		try {
			return buildDisplayName(nominatimClient.getAddress(longitude, latitude, RESOLVER_ZOOM_LEVEL));
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
	
}
