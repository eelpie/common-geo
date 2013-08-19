package uk.co.eelpieconsulting.common.geo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.co.eelpieconsulting.common.geo.model.OsmType;
import uk.co.eelpieconsulting.common.geo.model.Place;

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
		assertEquals("St James' Presbyterian Church, Adelaide Road, Newtown, Wellington, 6021, New Zealand/Aotearoa", firstMatch.getAddress());
		assertEquals(301919657, firstMatch.getOsmId().getId());
		assertEquals(OsmType.node, firstMatch.getOsmId().getType());
	}

	@Test
	public void canResolveAddressWithMultipleResults() throws Exception {
		List<Place> results = service.resolvePlaceName("Civic Square, Wellington");
		
		assertEquals(7, results.size());
	}
	
}
