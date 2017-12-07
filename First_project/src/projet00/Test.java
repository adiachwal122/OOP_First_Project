package projet00;

//import java.io.File;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;


// TODO: Auto-generated Javadoc
/**
 * The Class Test.
 */
public class Test {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		ReadCsv temp = new ReadCsv("Test");
		WriteCsv test = new WriteCsv(temp.get_fileTable());
		csvFilter adi = new csvFilter();
		adi.All("final_csv.csv");
 		WriteKml kml = new WriteKml(adi.getFile(),adi.getKeyIndex());


	}

}

