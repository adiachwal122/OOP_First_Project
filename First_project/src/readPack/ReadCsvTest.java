package readPack;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadCsvTest.
 */
public class ReadCsvTest {

	/**
	 * Test read csv string.
	 */
	@Test
	public void testReadCsvString() {
		String file = "28.10" ;
		try {
			ReadCsv a = new ReadCsv(file);
			a.read();
			assertEquals(23, a.getDatabase().size());
		}
		catch (IOException e1) {
			fail("Invalid number of lines");
			e1.printStackTrace();
		}
	
		}

	/**
	 * Test file type.
	 */
	@Test
	public void testFileType() {
		
		String file = "28.10\\WigleWifi_20171028203300.csv" ;
		try {
			ReadCsv a = new ReadCsv(file);
			a.read();
			assertEquals("CSV", a.fileType(file));
		}
		catch (IOException e1) {
			fail("Invalid file type");
			e1.printStackTrace();
		}
	
	}

}
