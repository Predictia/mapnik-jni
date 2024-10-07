package es.predictia.pdts.mapnikjni;

import java.io.File;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test library location and loading
 * @author stella
 *
 */
public class TestLoadLibrary {
	@Test
	public void testPluginAndFontPaths() {
		Mapnik.initialize();

		System.err.println("Installed fonts dir=" + Mapnik.getInstalledFontsDir());
		System.err.println("Installed plugin dir=" + Mapnik.getInstalledInputPluginsDir());
		
		assertNotNull(Mapnik.getInstalledFontsDir());
		assertNotNull(Mapnik.getInstalledInputPluginsDir());
		
		assertTrue(new File(Mapnik.getInstalledFontsDir()).isDirectory());
		assertTrue(new File(Mapnik.getInstalledInputPluginsDir()).isDirectory());
	}

	@Test
	public void testLoadFromClassPath() {
		Mapnik.tryLoadLibrary(false);
		Mapnik.initialize();
		System.out.println(Mapnik.version());
	}
}
