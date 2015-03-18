package uk.co.eelpieconsulting.common.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.co.eelpieconsulting.common.geo.model.LatLong;
import uk.co.eelpieconsulting.common.geo.model.OsmId;
import uk.co.eelpieconsulting.common.geo.model.OsmType;
import uk.co.eelpieconsulting.common.geo.model.Place;

public class NominatimGeocodingIT {

	private NominatimGeocodingService service;
	
	@Before
	public void setup() {
		service = new NominatimGeocodingService("tony@eelpieconsulting.co.uk", "http://heathfield.local/");
	}
	
	@Test
	public void canResolveBuildingNameToLocation() {
		List<Place> results = service.resolvePlaceName("St James Presbyterian Church, Newtown, Wellington");
		
		final Place firstMatch = results.get(0);
		assertEquals("St James' Presbyterian Church, Adelaide Road, Kowhai Park, Newtown, Wellington, Wellington City, Wellington, 6023, New Zealand/Aotearoa", firstMatch.getAddress());
		assertEquals(301919657, firstMatch.getOsmId().getId());
		assertEquals(OsmType.NODE, firstMatch.getOsmId().getType());
	}

	@Test
	public void canResolveAddressWithMultipleResults() throws Exception {
		List<Place> results = service.resolvePlaceName("Civic Square, Wellington");
		
		assertEquals(2, results.size());
	}
	
	@Test
	public void canLoadPlaceByOsmId() throws Exception {
		final OsmId basinReserve = new OsmId(96906280, OsmType.WAY);

		final Place loadedPlace = service.loadPlaceByOsmId(basinReserve);
		
		assertEquals(96906280, loadedPlace.getOsmId().getId());
		assertEquals(OsmType.WAY, loadedPlace.getOsmId().getType());
		assertTrue(loadedPlace.getAddress().startsWith("Basin Reserve"));
		
		
	}
	
	@Test
	public void canHandleNonLatinPlacenames() throws Exception {
		final OsmId admiraltyStation = new OsmId(248960443, OsmType.WAY);
		
		final Place loadedPlace = service.loadPlaceByOsmId(admiraltyStation);
		
		assertEquals("金鐘 Admiralty, 德立街 Drake Street, 金鐘 Admiralty, 中西區 Central and Western District, 香港島 Hong Kong Island, 香港特別行政區 Hong Kong, 中华人民共和国", loadedPlace.getAddress());
	}
	
	@Test
	public void canLoadPlaceByLongOsmId() throws Exception {
		final OsmId osmId = new OsmId(88273570L, OsmType.WAY);

		final Place loadedPlace = service.loadPlaceByOsmId(osmId);
		
		assertEquals(88273570L, loadedPlace.getOsmId().getId());
		assertEquals(OsmType.WAY, loadedPlace.getOsmId().getType());
		assertEquals("The Boatshed, Odlins Plaza, Te Aro, Wellington, Wellington City, Wellington, 6011, New Zealand/Aotearoa", loadedPlace.getAddress());
	}
	
	@Test
	public void canResolvePlacenameForLatLongPoint() throws Exception {
		final LatLong place = new LatLong(51.41815945187908, -0.2309974358582336);
		
		final String placeName = service.resolveNameForPoint(place);
		
		assertEquals("Copse Hill, Raynes Park, London Borough of Merton", placeName);
	}
	
}
