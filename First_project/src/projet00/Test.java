package projet00;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		/*ReadCsv gets csv file from WiggleWif only (path to file or folder)*/
		ReadCsvForTest file = new ReadCsvForTest("Test\\database\\_comb_all_BM2_.csv");
		file.read();
		ReadCsvForTest fileTest = new ReadCsvForTest("Test\\input\\_comb_no_gps_ts1.csv");
		fileTest.read();
		/*WriteCsv gets List<List<Network>> ,which provided by ReadCsv file.*/
		/*WriteCsv write = new WriteCsv(file.getDatabase());
		write.write();*/
		/*Build csvFilter and then use one of the filters, but it's possible to use it directly*/
		List<List<Network>> database = fileTest.getDatabase();
		List<List<Network>> data = new ArrayList<>();
		RemoveDuplicate duplicate = new RemoveDuplicate(database);
		System.out.println(duplicate.filter());
		database = duplicate.getFilteredFile();
		List<Network> tempLine = new ArrayList<>();
		for (List<Network> list : database) {
			for (Network network : list) {
				if(network.getMac()!=null) {
					FilterByMAC mac = new FilterByMAC(file.getDatabase(),network.getMac());
					WifiSpotLocation point = new WifiSpotLocation(mac.getFilteredFile());
					tempLine.add(point.WeightedAverage());
					data.add(tempLine);
				}
			}
		}
		WriteCsvToTest check = new WriteCsvToTest(data);
		System.out.println(check.write());
		/*The class gets List<String []> ,HashMap<String, Integer> which provided by Filter*/
		/*WriteKml kml = new WriteKml(mac.getFilteredFile());*/
		/*Kml crated (with time stamp)*/
		/*System.out.println(kml.write());*/

	}

}

