package uk.co.eelpieconsulting.common.geo;

import static org.junit.Assert.*;

import org.junit.Test;

public class BearingLabelServiceTest {

	@Test
	public void canResolveBearingsIntoSixteenPointCompassSectors() throws Exception {		
		final BearingLabelService bearingLabelService = new BearingLabelService();
		
		assertEquals("North", bearingLabelService.bearingLabelFor(0));
		assertEquals("North", bearingLabelService.bearingLabelFor(11.0));
		
		assertEquals("North-northeast", bearingLabelService.bearingLabelFor(14.0));	
		assertEquals("Northeast", bearingLabelService.bearingLabelFor(45));
		
		assertEquals("East", bearingLabelService.bearingLabelFor(85));
		assertEquals("East", bearingLabelService.bearingLabelFor(98));

		assertEquals("East-southeast", bearingLabelService.bearingLabelFor(119));
		
		assertEquals("West", bearingLabelService.bearingLabelFor(260));
		assertEquals("West", bearingLabelService.bearingLabelFor(275));
		
		assertEquals("North", bearingLabelService.bearingLabelFor(359));
	}	
	
	
}
