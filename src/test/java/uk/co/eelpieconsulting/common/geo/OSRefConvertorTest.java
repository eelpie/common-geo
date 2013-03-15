package uk.co.eelpieconsulting.common.geo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.co.eelpieconsulting.common.geo.OSRefConvertor;
import uk.co.eelpieconsulting.common.geo.model.LatLong;


public class OSRefConvertorTest {

	@Test
	public void canConvertEastingNorthingToLatLng() throws Exception {		
		final OSRefConvertor convertor = new OSRefConvertor();
		
		final LatLong latLong = convertor.toLatLong(516346, 173388);
		
		assertEquals(51.4475, latLong.getLatitude(), 0.0001);
		assertEquals(-0.3271, latLong.getLongitude(), 0.0001);
	}
	
}
