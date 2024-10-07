package es.predictia.pdts.mapnikjni;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestProjection {
	@BeforeClass
	public static void initMapnik() {
		Mapnik.initialize();
	}
	@AfterClass
	public static void tearDownMapnik() {
		Setup.tearDown();
	}


	@Test
	public void testForward() {

		if (!Projection.isProjSupported()) {
			System.err.println("Skipping testForward() because proj4 is not supported");
			return;
		}

		Projection prj=new Projection(Projection.LATLNG_PARAMS);
		Coord c=new Coord(10.0, 10.0);
		prj.forward(c);
		assertEquals(10.0, c.x, 0.001);
		assertEquals(10.0, c.y, 0.001);
	}
	
	@Test
	public void testInverse() {

		if (!Projection.isProjSupported()) {
			System.err.println("Skipping testForward() because proj4 is not supported");
			return;
		}
		Projection prj=new Projection(Projection.LATLNG_PARAMS);
		Coord c=new Coord(10.0,10.0);
		prj.inverse(c);
		assertEquals(10.0, c.x, 0.001);
		assertEquals(10.0, c.y, 0.001);
	}

	@Test
	public void testParams() {
		Projection prj=new Projection(Projection.LATLNG_PARAMS);
		assertEquals("unknown", prj.getParams());
	}

	@Test
	public void testOutOfRange() {

		if (!Projection.isProjSupported()) {
			System.err.println("Skipping testOutOfRange() because proj4 is not supported");
			return;
		}

		Projection prj=new Projection(Projection.SRS54030_PARAMS);
		Coord c=new Coord(-1000000000000.0, 10000000000.0);
		prj.inverse(c);
		System.out.println("c.x="+c.x);
	}
	
}
