package es.predictia.pdts.mapnikjni;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestLayer {
	@BeforeClass
	public static void initMapnik() {
		Setup.initialize();
	}
	@AfterClass
	public static void tearDownMapnik() {
		Setup.tearDown();
	}
	

	@Test
	public void testCreate() {
		Layer layer=new Layer("test");
		assertEquals("test", layer.getName());
		layer.setName("test1");
		assertEquals("test1", layer.getName());
	}

	@Test
	public void testStyles() {
		Layer layer=new Layer("test");
		String[] styles;
		
		styles=layer.getStyles();
		assertEquals(0, styles.length);
		
		layer.setStyles(new String[] { "one", "two" });
		styles=layer.getStyles();
		assertEquals(2, styles.length);
		assertEquals("one", styles[0]);
		assertEquals("two", styles[1]);
	}

	@Test
	public void testMapLayers() {
		MapDefinition map=new MapDefinition();
		assertEquals(0, map.getLayerCount());
		
		Layer layer;
		
		layer=new Layer("test1");
		map.addLayer(layer);
		assertEquals(1, map.getLayerCount());
		assertEquals("test1", map.getLayer(0).getName());
		
		// layer is a shallow copy - verify
		layer.setName("blah");
		assertEquals("test1", map.getLayer(0).getName());
		
		// Update it
		map.setLayer(0, layer);
		assertEquals("blah", map.getLayer(0).getName());
		
		map.removeLayer(0);
		assertEquals(0, map.getLayerCount());
	}
	
	@Test
	public void testDatasource() {

		if (!isPostgisAvailable()) {
			return;
		}
		Layer layer=new Layer("test");
		assertNull(layer.getDatasource());
		
		Parameters params=getPostgisParameters();
		
		Datasource ds=DatasourceCache.create(params);
		layer.setDatasource(ds);
		assertNotNull(layer.getDatasource());
		
		params=ds.getParameters();
		assertEquals("postgis", params.get("type"));
		assertEquals("test", params.get("table"));
	}

	private Parameters getPostgisParameters() {
		var params = new Parameters();
		params.put("type", "postgis");
		params.put("host", System.getenv("PG_HOST"));
		params.put("port", System.getenv("PG_PORT"));
		params.put("user", System.getenv("PG_USER"));
		params.put("password", System.getenv("PG_PASSWORD"));
		params.put("dbname", System.getenv("PG_DBNAME"));
		params.put("table", "test");
		return params;
	}

	private boolean isPostgisAvailable() {
		// Check if PG_HOST, PG_PORT, PG_USER, PG_PASSWORD, PG_DBNAME are set
		String[] envVars = { "PG_HOST", "PG_PORT", "PG_USER", "PG_PASSWORD", "PG_DBNAME" };
		for (String envVar : envVars) {
			if (System.getenv(envVar) == null) {
				System.out.println("Skipping test because " + envVar + " is not set");
				return false;
			}
		}
		return true;
	}
}
