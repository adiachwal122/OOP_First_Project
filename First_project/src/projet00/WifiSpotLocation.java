package projet00;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WifiSpotLocation {
	private List<Network> database;

	public WifiSpotLocation(List<Network> macFiltered) {
		this.database = macFiltered;
		
	}
		/**
		 * Returns the k rows with the highest y values.
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
		
}
