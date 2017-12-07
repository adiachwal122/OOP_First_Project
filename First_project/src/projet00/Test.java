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
		ReadCsv temp = new ReadCsv("Test\\WigleWifi_20171028203300.csv");
		WriteCsv test = new WriteCsv(temp.get_fileTable());
		csvFilter adi = new csvFilter();
		adi.filterByTime("final_csv.csv", "28/10/2017 20:15", "28/10/2017 20:17");
 		WriteKml kml = new WriteKml(adi.getFile(),adi.getKeyIndex());


	}

}

