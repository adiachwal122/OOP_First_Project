package projet00;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.KmlFactory;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class WriteKml.
 */
public class WriteKml {
	

	/**
	 * Instantiates a new write kml.
	 */
	public WriteKml() {

	}

	/**
	 * Instantiates a new write kml.
	 *
	 * @author adiel ,adi and yuda
	 * @param kmlList the kml list
	 * @param keyIndex {@link https://labs.micromata.de/projects/jak/kml-in-the-java-world.html}
	 * @return Kml file
	 */

	public WriteKml(List<String []> kmlList ,HashMap<String, Integer> keyIndex){
		/*try {
		// The all encapsulating kml element.
		Kml kml = KmlFactory.createKml();
		for (String[] list : kmlList) {
			if(list != null){
				//Document doc = KmlFactory.createDocument();
				
				// Create <Placemark> and set values.
				Placemark placemark = KmlFactory.createDocument().withName(list[keyIndex.get("SSID")]).createAndAddPlacemark();
				//doc.addToFeature(placemark);
				placemark.setName(list[keyIndex.get("SSID")]);
				placemark.setId(list[keyIndex.get("ID")]);
				placemark.setVisibility(true);
				placemark.setOpen(false);
				placemark.setDescription("MAC: " + list[keyIndex.get("MAC")] +
						"\nFrequncy: " + list[keyIndex.get("Frequncy")] + 
						"\nSignal: " + list[keyIndex.get("Signal")]);
				placemark.createAndSetTimeStamp().withWhen(list[keyIndex.get("Time")]);

				// Create <Point> and set values.
				Point point = KmlFactory.createPoint();
				point.setExtrude(false);
				//point.setAltitudeMode(AltitudeMode.fromValue(list[keyIndex.get("Alt")]));
				// Add <coordinates>9.444652669565212,51.30473589438118,0<coordinates>.
				point.getCoordinates().add(new Coordinate(list[keyIndex.get("Lon")] + ","
						+ list[keyIndex.get("Lat")] + ","
						+ list[keyIndex.get("Alt")]));
				
				placemark.setGeometry(point);      // <-- point is registered at placemark ownership.
				kml.setFeature(placemark);         // <-- placemark is registered at kml ownership.
			}
		}

		kml.marshal(new File("KmlFile.kml"));
		System.out.println("Kml created!!");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  */ 
		try {
			List<String []> listOfNet = kmlList;
			final Kml writekml = new Kml();
			Document document = writekml.createAndSetDocument();

			for (String[] list : listOfNet) {
				if(list != null)
					document.createAndAddDocument().withName(list[keyIndex.get("SSID")]).createAndAddPlacemark()
					.withName(list[keyIndex.get("SSID")]).withId(list[keyIndex.get("ID")]).withDescription("MAC: " 
							+ list[keyIndex.get("MAC")] +
							"\nFrequncy: " + list[keyIndex.get("Frequncy")] + 
							"\nSignal: " + list[keyIndex.get("Signal")])
					.withOpen(Boolean.TRUE).createAndSetPoint()
					.addToCoordinates(Double.parseDouble(list[keyIndex.get("Lon")])
							, Double.parseDouble(list[keyIndex.get("Lat")]) 
							, Double.parseDouble(list[keyIndex.get("Alt")]));
				document.createAndSetTimeStamp().withWhen(list[keyIndex.get("Time")]);
			}
			
			writekml.marshal(new File("KmlFile.kml"));
			System.out.println("Kml created!!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Unauthorized file.
	 */
	//Check if file type is unauthorized 
	public void unauthorizedFile(String unauthorizedFile) {
		System.err.println(unauthorizedFile+" - " +"Unauthorized File, please change the file to authorized file (Authorize csv)!"); 
	}
}
