package projet00;

public class Network {

	private String ssid, mac ,id , time , lat, lon ,alt;
	private int frequncy, signal;
	

	public Network() {
	}
	
	/*
	 * @author adiel ,adi and yuda
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

	public String getId() {
		return id;
	}
	public String getTime() {
		return time;
	}
	public String getLat() {
		return lat;
	}
	public String getLon() {
		return lon;
	}
	public String getAlt() {
		return alt;
	}
	public String getMac() {
		return mac;
	}

	public String getSsid() {
		return ssid;
	}

	public int getFrequncy() {
		return frequncy;
	}

	public int getSignal() {
		return signal;
	}

}
