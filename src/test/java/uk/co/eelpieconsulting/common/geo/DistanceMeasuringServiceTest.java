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

        assertEquals(13.1, distanceInKilometers, 0.1);
    }

    @Test
    public void worksCorrectlyForLargeLatLongs() {
        LatLong here = new LatLong(-41.2924, 174.7787);
        LatLong there = new LatLong(-42.1, 174.7787);

        double distanceInKilometers = new DistanceMeasuringService().getDistanceBetween(here, there);

        assertEquals(89.9, distanceInKilometers, 0.1);
    }

}
