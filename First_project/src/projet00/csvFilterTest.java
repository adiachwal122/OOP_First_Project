package projet00;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class csvFilterTest.
 */
public class csvFilterTest {

	/**
	 * Test filter by MA cor SSID.
	 */
	@Test
	public void testFilterByMACorSSID() {
		csvFilter a = new csvFilter();
		try {
			a.filterByMACorSSID("testFilterByMac.csv", "MAC", "cc:b2:55:e9:cb:fc");
			assertEquals("cc:b2:55:e9:cb:fc" , a.getFile().get(0)[a.getKeyIndex().get("MAC")]);

		} catch (NumberFormatException e) {
			fail ("Invalid filter by MAC");
		}catch (IOException e) {
			fail ("Invalid filter by MAC");
		}
	}

	/**
	 * Test all.
	 */
	@Test
	public void testAll() {
		csvFilter a = new csvFilter();

		try {
			a.All("testAll.csv");

			List<String []> file = a.getFile();			
			assertEquals(10, file.size());
		} catch (IOException e) {
			fail ("Invalid number of lines");
			e.printStackTrace();
		}

	}

	/**
	 * Test filter by ID.
	 */
	@Test
	public void testFilterByID() {
		csvFilter a = new csvFilter();
		try {
			a.filterByID("testFilterByMac.csv", "ID", "ONEPLUS A3003");
			assertEquals("ONEPLUS A3003" , a.getFile().get(0)[a.getKeyIndex().get("ID")]);

		} catch (NumberFormatException e) {
			fail ("Invalid filter by ID");
		}catch (IOException e) {
			fail ("Invalid filter by ID");
		}
	}
	
	/**
	 * Test filter by time.
	 */
	@Test
	public void testFilterByTime() {
		csvFilter a = new csvFilter();

		try {
			a.filterByTime("final_csv.csv", "28/10/2017 20:14", "28/10/2017 20:16");

			List<String []> file = a.getFile();			
			assertEquals(30, file.size());
		} catch (IOException e) {
			fail ("Invalid number of lines");
			e.printStackTrace();
		}

	}
}
