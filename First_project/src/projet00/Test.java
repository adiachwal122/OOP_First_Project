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
		/*ReadCsv gets csv file from WiggleWif only (path to file or folder)*/
		ReadCsv file = new ReadCsv("Test");
		/*WriteCsv gets List<List<Network>> ,which provided by ReadCsv file.*/
		WriteCsv write = new WriteCsv(file.getDatabase());
		/*Build csvFilter and then use one of the filters, but it's possible to use it directly*/
		FilterByMAC all = new FilterByMAC(write.getFileTable(),"6a:12:f5:f9:5e:71");
		/*The class gets List<String []> ,HashMap<String, Integer> which provided by Filter*/
		WriteKml kml = new WriteKml(all.getFilteredFile());
		/*Kml crated (with time stamp)*/

	}

}

