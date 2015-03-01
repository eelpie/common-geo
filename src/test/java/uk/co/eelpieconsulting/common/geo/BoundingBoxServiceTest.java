package uk.co.eelpieconsulting.common.geo;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import uk.co.eelpieconsulting.common.geo.model.BoundingBox;
import uk.co.eelpieconsulting.common.geo.model.LatLong;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class BoundingBoxServiceTest {

	private final String twickenham = "51.4135,-0.3085,51.4093,-0.3091,51.4030,-0.3087,51.4006,-0.3093,51.4000,-0.3097,51.3984,-0.3120,51.3975,-0.3125,51.3937,-0.3177,51.3926,-0.3199,51.3915,-0.3246,51.3914,-0.3260,51.3918,-0.3278,51.3935,-0.3302,51.3959,-0.3323,51.3995,-0.3371,51.4030,-0.3405,51.4040,-0.3438,51.4059,-0.3471,51.4070,-0.3499,51.4098,-0.3551,51.4108,-0.3554,51.4119,-0.3592,51.4120,-0.3613,51.4116,-0.3648,51.4086,-0.3732,51.4079,-0.3779,51.4079,-0.3802,51.4085,-0.3834,51.4101,-0.3838,51.4104,-0.3867,51.4103,-0.3892,51.4107,-0.3897,51.4143,-0.3903,51.4143,-0.3861,51.4150,-0.3866,51.4177,-0.3868,51.4193,-0.3877,51.4201,-0.3867,51.4223,-0.3914,51.4233,-0.3929,51.4235,-0.3928,51.4232,-0.3911,51.4207,-0.3854,51.4227,-0.3841,51.4280,-0.3884,51.4282,-0.3871,51.4287,-0.3879,51.4324,-0.3829,51.4355,-0.3761,51.4363,-0.3779,51.4368,-0.3779,51.4416,-0.3669,51.4436,-0.3729,51.4430,-0.3740,51.4431,-0.3761,51.4425,-0.3771,51.4425,-0.3782,51.4432,-0.3796,51.4444,-0.3798,51.4450,-0.3829,51.4467,-0.3863,51.4482,-0.3863,51.4493,-0.3876,51.4499,-0.3846,51.4495,-0.3838,51.4563,-0.3753,51.4575,-0.3730,51.4570,-0.3727,51.4572,-0.3725,51.4569,-0.3685,51.4574,-0.3678,51.4578,-0.3651,51.4577,-0.3629,51.4582,-0.3598,51.4584,-0.3564,51.4582,-0.3535,51.4569,-0.3528,51.4568,-0.3487,51.4573,-0.3460,51.4579,-0.3451,51.4581,-0.3427,51.4590,-0.3417,51.4592,-0.3401,51.4573,-0.3406,51.4559,-0.3390,51.4561,-0.3386,51.4555,-0.3377,51.4547,-0.3374,51.4546,-0.3370,51.4557,-0.3363,51.4553,-0.3333,51.4573,-0.3314,51.4579,-0.3304,51.4580,-0.3284,51.4570,-0.3273,51.4580,-0.3256,51.4593,-0.3265,51.4598,-0.3262,51.4600,-0.3267,51.4608,-0.3264,51.4612,-0.3269,51.4617,-0.3267,51.4643,-0.3249,51.4652,-0.3238,51.4654,-0.3232,51.4654,-0.3208,51.4639,-0.3198,51.4605,-0.3144,51.4584,-0.3084,51.4566,-0.3057,51.4543,-0.3039,51.4521,-0.3033,51.4495,-0.3053,51.4479,-0.3093,51.4462,-0.3165,51.4460,-0.3201,51.4434,-0.3288,51.4416,-0.3307,51.4402,-0.3308,51.4365,-0.3285,51.4340,-0.3280,51.4321,-0.3261,51.4302,-0.3220,51.4288,-0.3158,51.4264,-0.3104,51.4246,-0.3080,51.4227,-0.3065,51.4212,-0.3060,51.4152,-0.3083,51.4135,-0.3085";
	private final String wimbledon = "51.4243,-0.1788,51.4226,-0.1778,51.4218,-0.1807,51.4224,-0.1809,51.4218,-0.1824,51.4201,-0.1813,51.4200,-0.1806,51.4195,-0.1806,51.4194,-0.1814,51.4165,-0.1808,51.4162,-0.1824,51.4146,-0.1837,51.4084,-0.1843,51.4039,-0.1891,51.4037,-0.1916,51.4020,-0.1946,51.3978,-0.1959,51.3975,-0.1956,51.3950,-0.1982,51.3950,-0.1990,51.3973,-0.2002,51.3957,-0.2007,51.3945,-0.2033,51.3943,-0.2075,51.3937,-0.2109,51.3939,-0.2127,51.3945,-0.2141,51.3925,-0.2163,51.3921,-0.2175,51.3925,-0.2179,51.3930,-0.2168,51.3931,-0.2176,51.3960,-0.2205,51.3959,-0.2213,51.3966,-0.2213,51.3954,-0.2263,51.3951,-0.2260,51.3945,-0.2275,51.3933,-0.2263,51.3928,-0.2268,51.3931,-0.2327,51.3925,-0.2322,51.3918,-0.2349,51.3905,-0.2347,51.3893,-0.2397,51.3912,-0.2401,51.3939,-0.2417,51.3947,-0.2446,51.3976,-0.2474,51.3992,-0.2466,51.4005,-0.2468,51.4017,-0.2452,51.4034,-0.2447,51.4071,-0.2454,51.4076,-0.2458,51.4079,-0.2471,51.4085,-0.2475,51.4096,-0.2471,51.4149,-0.2496,51.4185,-0.2496,51.4219,-0.2509,51.4228,-0.2517,51.4232,-0.2530,51.4257,-0.2523,51.4293,-0.2542,51.4308,-0.2533,51.4313,-0.2524,51.4326,-0.2511,51.4352,-0.2407,51.4382,-0.2311,51.4387,-0.2255,51.4386,-0.2245,51.4389,-0.2243,51.4384,-0.2204,51.4387,-0.2178,51.4383,-0.2165,51.4377,-0.2157,51.4378,-0.2125,51.4390,-0.2043,51.4415,-0.1900,51.4401,-0.1898,51.4389,-0.1907,51.4385,-0.1892,51.4353,-0.1901,51.4335,-0.1912,51.4319,-0.1903,51.4314,-0.1898,51.4316,-0.1891,51.4318,-0.1893,51.4321,-0.1874,51.4330,-0.1873,51.4332,-0.1858,51.4312,-0.1842,51.4276,-0.1846,51.4264,-0.1841,51.4247,-0.1819,51.4243,-0.1788";
	
	private final BoundingBoxService boundingBoxService = new BoundingBoxService();
	
	@Test
	public void canBuildBoundingBoxToEnclosePoints() throws Exception {
		final List<LatLong> points = Lists.newArrayList(new LatLong(10, 1), new LatLong(20, 2), new LatLong(30, 3));

		final BoundingBox boundingBox = boundingBoxService.getBoundingBoxFor(points);
		
		assertEquals(30, boundingBox.getTopLeft().getLatitude(), 0);
		assertEquals(3, boundingBox.getBottomRight().getLongitude(), 0);
		assertEquals(1, boundingBox.getTopLeft().getLongitude(), 0);		
		assertEquals(10, boundingBox.getBottomRight().getLatitude(), 0);
	}
	
	@Test
	public void canCreateBoundingBoxForComplexShapes() throws Exception {		
		BoundingBox twickenhamBoundingBox = boundingBoxService.getBoundingBoxFor(parsePoints(twickenham));
		assertEquals(51.4654, twickenhamBoundingBox.getTopLeft().getLatitude(), 0);
		assertEquals(-0.3929, twickenhamBoundingBox.getTopLeft().getLongitude(), 0);
		assertEquals(51.3914, twickenhamBoundingBox.getBottomRight().getLatitude(), 0);
		assertEquals(-0.3033, twickenhamBoundingBox.getBottomRight().getLongitude(), 0);	
		assertEquals(51.4284, twickenhamBoundingBox.getCenter().getLatitude(), 0.0001);
		assertEquals(-0.3481, twickenhamBoundingBox.getCenter().getLongitude(), 0.0001);
		
		BoundingBox wimbledonBoundingBox = boundingBoxService.getBoundingBoxFor(parsePoints(wimbledon));
		assertEquals(51.4154, wimbledonBoundingBox.getCenter().getLatitude(), 0.0001);
		assertEquals(-0.216, wimbledonBoundingBox.getCenter().getLongitude(), 0.0001);
	}
	
	private List<LatLong> parsePoints(CharSequence pointsString) {
		List<LatLong> points = Lists.newArrayList();
		Iterator<String> iterator = Splitter.on(",").split(pointsString).iterator();
		while(iterator.hasNext()) {
			points.add(new LatLong(Double.parseDouble(iterator.next()), Double.parseDouble(iterator.next())));
		}
		return points;
	}
	
}