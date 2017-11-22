package projet00;

//import java.io.File;
import java.io.File;
import java.io.IOException;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;


public class Test {

	public static void main(String[] args) throws IOException {
//		FileToCsv testFile = new FileToCsv("C:\\Users\\user\\workspace\\Matala1\\28.10\\WigleWifi_20171028203300.csv");
//		//testFile.getOrder("WigleWifi_20171031013535.csv");
//
//		CsvToKml file = new CsvToKml();
//		file.filterBy("final_csv.csv", "Signal", "-69");
		/*final Kml kml = new Kml();
		kml.createAndSetPlacemark()
		   .withName("London, UK").withOpen(Boolean.TRUE)
		   .createAndSetPoint().addToCoordinates(-0.126236, 51.500152);
		kml.marshal(new File("HelloKml.kml"));*/
		
		final Kml kml = Kml.unmarshal(new File("HelloKml.kml"));
		final Placemark placemark = (Placemark) kml.getFeature();
		Point point = (Point) placemark.getGeometry();
		List<Coordinate> coordinates = point.getCoordinates();
		for (Coordinate coordinate : coordinates) {
		    System.out.println(coordinate.getLatitude());
		    System.out.println(coordinate.getLongitude());
		    System.out.println(coordinate.getAltitude());
		}

		/*	Kml kml = new Kml();
		Document kml = kml.createAndAddDocument();*/




	}

}

