package projet00;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WifiSpotLocation {
	private List<Network> database;

	public WifiSpotLocation(List<Network> macFiltered) {
		this.database = macFiltered;
		average(macFiltered);
	}
		/**
		 * Returns the k rows with the highest signal values.
		 * Implemented using Streams.
		 * https://github.com/erelsgl/ariel-oop-course/blob/master/YomHamishi/src/lesson7/WifiDatabase.java
		 */
		public List<Network> strongestSignal(int k) {
			return database
				.stream()
				.parallel()
				.sorted(Comparator.comparing(net -> -net.getSignal()))
				.limit(k)
				.collect(Collectors.toList());
	}
	/*Get average Network*/
	public Network average(List<Network> listMAC) {
		/*List of 3 sorted database by signal*/
		List<Network> data = strongestSignal(3);
		double avgAlt = 0, avgLon = 0, avgLat = 0;
		/*Sum the Alt, Lon and Lat from the list*/
		for (Network net : data) {
			avgAlt += net.getAlt();
			avgLon += net.getLon();
			avgLat += net.getLat();
		}
		/*Get the average*/
		avgAlt /= data.size();
		avgLon /= data.size();
		avgLat /= data.size();
		/*Get Network element*/
		return new Network(listMAC.get(0).getMac(), avgLat, avgLon, avgAlt);	
		}
}
