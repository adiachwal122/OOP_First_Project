package projet00;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

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
		try {
			List<String []> listOfNet = kmlList;
			final Kml writekml = new Kml();
			Document document = writekml.createAndSetDocument();
			for (String[] list : listOfNet) {

				if(list != null) {
				Placemark placemark = document.createAndAddPlacemark()
					.withName(list[keyIndex.get("SSID")]).withOpen(Boolean.TRUE);
				placemark.withId(list[keyIndex.get("ID")]).withDescription("MAC: " 
							+ list[keyIndex.get("MAC")] +
							"\nFrequncy: " + list[keyIndex.get("Frequncy")] + 
							"\nSignal: " + list[keyIndex.get("Signal")]).createAndSetPoint()
					.addToCoordinates(Double.parseDouble(list[keyIndex.get("Lon")])
							, Double.parseDouble(list[keyIndex.get("Lat")]) 
							, Double.parseDouble(list[keyIndex.get("Alt")]));
				placemark.createAndSetTimeStamp().withWhen(timeStampFormate(list[keyIndex.get("Time")]));			
				}
				
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
	/*
	 * Change formate for TimeStamp 
	 * */
	public String timeStampFormate(String time) {
		String[] getTime = time.split("\\D");
		String newTimeFormate = getTime[2] + "-" + getTime[1] + "-" +getTime[0] + "T" + 
		getTime[3] + ":" + getTime[4] + ":00" + "Z";
		
		return newTimeFormate;
	}
	
	/**
	 * Unauthorized file.
	 */
	//Check if file type is unauthorized 
	public void unauthorizedFile(String unauthorizedFile) {
		System.err.println(unauthorizedFile+" - " +"Unauthorized File, please change the file to authorized file (Authorize csv)!"); 
	}
}
