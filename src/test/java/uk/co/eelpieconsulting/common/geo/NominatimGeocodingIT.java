package uk.co.eelpieconsulting.common.geo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class NominatimGeocodingIT {

	private NominatimGeocodingService service;
	
	@Before
	public void setup() {
		service = new NominatimGeocodingService("tony@eelpieconsulting.co.uk", "http://nominatim.openstreetmap.org/");
	}
	
	@Test
	public void canResolveBuildingNameToLocation() {
		List<Place> results = service.resolvePlaceName("St James Presbyterian Church, Newtown, Wellington");
		final Place firstMatch = results.get(0);
		assertEquals("St James' Presbyterian Church, Adelaide Road, Newtown, Wellington, Wellington Region, 6021, New Zealand", firstMatch.getAddress());
		assertEquals(301919657, firstMatch.getOsmId().getId());
		assertEquals("place_of_worship", firstMatch.getOsmId().getType());
	}

	@Test
	public void canResolveAddressWithMultipleResults() throws Exception {
		List<Place> results = service.resolvePlaceName("Civic Square, Wellington");
		assertEquals(2, results.size());
	}
	
}
