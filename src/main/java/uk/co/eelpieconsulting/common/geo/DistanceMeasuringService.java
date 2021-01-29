package uk.co.eelpieconsulting.common.geo;

import org.apache.log4j.Logger;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import uk.co.eelpieconsulting.common.geo.model.LatLong;

public class DistanceMeasuringService {

    private static Logger log = Logger.getLogger(DistanceMeasuringService.class);

    private final SpatialContext ctx = SpatialContext.GEO;

    public double getDistanceBetween(LatLong here, LatLong there) {
        Point h = ctx.makePoint(here.getLatitude(), here.getLongitude());
        Point t = ctx.makePoint(there.getLatitude(), there.getLongitude());

        double distanceBetweenHereAndThereRadians = ctx.calcDistance(h, t);
        double distanceBetweenHereAndThereInKilometres = DistanceUtils.degrees2Dist(distanceBetweenHereAndThereRadians, DistanceUtils.EARTH_MEAN_RADIUS_KM);

        log.debug("Distance from " + here + " to " + there + " is " + distanceBetweenHereAndThereInKilometres);
        return distanceBetweenHereAndThereInKilometres;
    }

}
