package projet00;

import java.io.IOException;


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

