
//import java.io.File;
import java.io.IOException;


public class Test {

	public static void main(String[] args) throws IOException {
		FileToCsv testFile = new FileToCsv("C:\\Users\\user\\workspace\\Matala1\\28.10\\WigleWifi_20171028203300.csv");
		//testFile.getOrder("WigleWifi_20171031013535.csv");

		CsvToKml file = new CsvToKml();
		file.filterBy("final_csv.csv", "Signal", "-69");


		/*	Kml kml = new Kml();
		Document kml = kml.createAndAddDocument();*/




	}

}

