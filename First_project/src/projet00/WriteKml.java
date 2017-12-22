package projet00;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

/**
 * The Class WriteKml.
 * The class gets List<String []> ,HashMap<String, Integer> which provided by Filter
 * return KML file for Google Earth
 */
public class WriteKml implements Write{
	List<Network> listOfNet;
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
	 * @param keyIndex 
	 * {@link https://labs.micromata.de/projects/jak/kml-in-the-java-world.html}
	 */
	public WriteKml(List<Network> kmlList){
		this.listOfNet = kmlList;
		System.out.println(write());
	}
	/*Convert date from String to TimeStame Signature
	 * @param String time
	 * @return String 
	 * */
	public String timeStampFormate(String time) {
		String[] getTime = time.split("\\D");
		String newTimeFormate = getTime[2] + "-" + getTime[1] + "-" +getTime[0] + "T" + 
		getTime[3] + ":" + getTime[4] + ":" + getTime[5] + "Z";
		
		return newTimeFormate;
	}
	/*Implement Write class
	 * */
	@Override
	public String write() {
		try {
			Date timeDate = new Date();
			final Kml writekml = new Kml();
			Document document = writekml.createAndSetDocument();
<<<<<<< HEAD
			for (Network wifiSpot : listOfNet) {
				Placemark placemark = document.createAndAddPlacemark()
					.withName(wifiSpot.getSsid()).withOpen(Boolean.TRUE);
				placemark.withId(wifiSpot.getId()).withDescription("MAC: " + wifiSpot.getMac() +
							"\nFrequncy: " + wifiSpot.getFrequncy() + 
							"\nSignal: " + wifiSpot.getSignal())
				.createAndSetPoint().addToCoordinates(wifiSpot.getLon()
													, wifiSpot.getLat() 
													,wifiSpot.getAlt());
				placemark.createAndSetTimeStamp().withWhen(timeStampFormate(wifiSpot.getTime()));			
=======
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
				
>>>>>>> refs/remotes/origin/master
			}
			writekml.marshal(new File("KmlFile - " + timeDate.getTime() + ".kml"));
			return "Kml created!!";
		} catch (FileNotFoundException | NullPointerException e) {
			return e.getMessage();
		}
<<<<<<< HEAD
		
=======
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
>>>>>>> refs/remotes/origin/master
	}
}
