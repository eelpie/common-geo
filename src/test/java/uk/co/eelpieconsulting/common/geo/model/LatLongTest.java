package uk.co.eelpieconsulting.common.geo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class LatLongTest {
	
	@Test
	public void latLongsForTheSamePlaceShouldBeConsideredEqual() throws Exception {
		final LatLong here = new LatLong(1, 2);
		final LatLong alsoHere = new LatLong(1, 2);
		
		assertEquals(here, alsoHere);
	}
	
	@Test
	public void latLongsForDifferentPlacesShouldNotBeConsideredEquals() throws Exception {
		final LatLong here = new LatLong(1, 2);
		final LatLong there = new LatLong(-1, -2);
		
		assertFalse(here.equals(there));
	}
	
}
