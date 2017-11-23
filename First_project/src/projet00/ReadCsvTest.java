package projet00;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class ReadCsvTest {

	@Test
	public void testReadCsvString() {
		String file = "28.10" ;
		try {
			ReadCsv a = new ReadCsv(file);
			assertEquals(23, a.get_fileTable().size());
		}
		catch (IOException e1) {
			fail("Invalid number of lines");
			e1.printStackTrace();
		}
	
		}

	@Test
	public void testFileType() {
		
		String file = "28.10\\WigleWifi_20171028203300.csv" ;
		try {
			ReadCsv a = new ReadCsv(file);
			
			assertEquals("CSV", a.fileType(file));
		}
		catch (IOException e1) {
			fail("Invalid file type");
			e1.printStackTrace();
		}
	
	}

}
