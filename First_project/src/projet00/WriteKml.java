package projet00;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Kml;

public class WriteKml {
	/**
	 * Learned from:
	 *  https://labs.micromata.de/projects/jak/kml-in-the-java-world.html
	 * Creat Kml file
	 * @param KmlPath
	 */

	public WriteKml() {

	}

	public WriteKml(List<String []> kmlList ,HashMap<String, Integer> keyIndex){

		try {
			final Kml writekml = new Kml();
			for (String[] list : kmlList) {
				if(list != null)
				writekml.createAndSetDocument().createAndAddPlacemark()
				.withName(list[keyIndex.get("SSID 1")]).withDescription("<![CDATA[ID: " + list[keyIndex.get("ID")]
						+ "\brMAC: " + list[keyIndex.get("MAC 1")] +
						"\brFrequncy: " + list[keyIndex.get("Frequncy 1")] + 
						"\brSignal: " + list[keyIndex.get("Signal 1")] + "]]>")
				.withOpen(Boolean.TRUE).createAndSetPoint()
				.addToCoordinates(Double.parseDouble(list[keyIndex.get("Lat")])
						, Double.parseDouble(list[keyIndex.get("Lon")]) 
						, Double.parseDouble(list[keyIndex.get("Alt")]));
			}
			
			writekml.marshal(new File("KmlFile.kml"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getCause());
		}catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getCause());
		}
	}
	
	//Check if file type is unauthorized 
	public void unauthorizedFile(String unauthorizedFile) {
		System.err.println(unauthorizedFile+" - " +"Unauthorized File, please change the file to authorized file (Authorize csv)!"); 
	}
}
