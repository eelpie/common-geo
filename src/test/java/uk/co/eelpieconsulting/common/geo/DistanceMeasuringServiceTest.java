package uk.co.eelpieconsulting.common.geo;

import org.junit.Test;
import uk.co.eelpieconsulting.common.geo.model.LatLong;

import static org.junit.Assert.assertEquals;

public class DistanceMeasuringServiceTest {

    @Test
    public void canMeasureDistanceBetweenTwoLatLongs() {
        LatLong here = new LatLong(51.1, -0.3);
        LatLong there = new LatLong(51.0, -0.4);

        double distanceInKilometers = new DistanceMeasuringService().getDistanceBetween(here, there);

        assertEquals(15.72, distanceInKilometers, 0.1);
    }
}
