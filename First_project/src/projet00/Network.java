package projet00;

// TODO: Auto-generated Javadoc
/**
 * The Class Network.
 */
public class Network {

	
	private String ssid, mac ,id , time , lat, lon ,alt;
	
	
	private int frequncy, signal;
	

	/**
	 * Instantiates a new network.
	 */
	public Network() {
	}
	
	/**
	 * Instantiates a new network.
	 * @author adiel ,adi and yuda
	 * @param String ssid
	 * @param String n mac
	 * @param Int frequncy
	 * @param Int signal
	 * @param String n time
	 * @param String id
	 * @param String lat
	 * @param String lon
	 * @param String alt
	 * @param Ssid, Mac, Frequncy, Signal, Time, Id, Lat, Lon, Alt
	 */
	public Network(String nSsid, String nMac, int nFrequncy, int nSignal, 
			String nTime,String nId, String nLat, String nLon, String nAlt) {
		this.mac = nMac;
		this.ssid = nSsid;
		this.frequncy = nFrequncy;
		this.signal = nSignal;
		this.time = nTime ;
		this.id = nId;
		this.lat = nLat;
		this.lon = nLon;
		this.alt = nAlt;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * Gets the lat.
	 *
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}
	
	/**
	 * Gets the lon.
	 *
	 * @return the lon
	 */
	public String getLon() {
		return lon;
	}
	
	/**
	 * Gets the alt.
	 *
	 * @return the alt
	 */
	public String getAlt() {
		return alt;
	}
	
	/**
	 * Gets the mac.
	 *
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * Gets the ssid.
	 *
	 * @return the ssid
	 */
	public String getSsid() {
		return ssid;
	}

	/**
	 * Gets the frequncy.
	 *
	 * @return the frequncy
	 */
	public int getFrequncy() {
		return frequncy;
	}

	/**
	 * Gets the signal.
	 *
	 * @return the signal
	 */
	public int getSignal() {
		return signal;
	}

}
