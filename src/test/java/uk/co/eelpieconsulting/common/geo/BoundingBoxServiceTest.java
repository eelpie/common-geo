package uk.co.eelpieconsulting.common.geo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import uk.co.eelpieconsulting.common.geo.model.BoundingBox;
import uk.co.eelpieconsulting.common.geo.model.LatLong;

import com.google.common.collect.Lists;

public class BoundingBoxServiceTest {

	@Test
	public void canBuildBoundingBoxToEnclosePoints() throws Exception {
		final List<LatLong> points = Lists.newArrayList(new LatLong(10, 1), new LatLong(20, 2), new LatLong(30, 3));

		final BoundingBox boundingBox = new BoundingBoxService().getBoundingBoxFor(points);
		
		assertEquals(30, boundingBox.getTopLeft().getLatitude(), 0);
		assertEquals(3, boundingBox.getBottomRight().getLongitude(), 0);
		assertEquals(1, boundingBox.getTopLeft().getLongitude(), 0);		
		assertEquals(10, boundingBox.getBottomRight().getLatitude(), 0);
	}
	
}
