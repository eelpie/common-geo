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
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRefuseToConstructLatLongWithLatitudeBelow90DegreesSouth() throws Exception {
		new LatLong(-90.01, 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRefuseToConstructLatLongWithLatitudeAbove90DegreesNorth() throws Exception {
		new LatLong(90.001, 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRefuseToConstructLatLongWithLongitudeMoreThan180DegressWest() throws Exception {
		new LatLong(51.1, -180.1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRefuseToConstructLatLongWithLongitudeMoreThan180DegressEast() throws Exception {
		new LatLong(51.1, 180.1);
	}
	
}
