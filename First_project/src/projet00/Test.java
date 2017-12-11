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
		WriteCsv write = new WriteCsv(file.get_fileTable());
		/*Build csvFilter and then use one of the filters, but it's possible to use it directly*/
		FilterByDate all= new FilterByDate(write.get_fileTable(),"27/10/2017  16:18:59" ,"27/10/2017  16:19:04");
		/*The class gets List<String []> ,HashMap<String, Integer> which provided by Filter*/
		WriteKml kml = new WriteKml(all.getFilteredFile());
		kml.write();
		/*Kml crated (with time stamp)*/

	}

}

