package projet00;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserLocation {
	/*Database - from user*/
	private List<Network> database;
	/*Filtered database by strongest signal*/
	private List<Network> filteredDatbase;
	
	HashMap<Network, Double> piMap = new HashMap<>();
	
	public UserLocation(List<Network> file) {
		this.database = file;
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
			.sorted(Comparator.comparing(net -> piMap.get(net)))
			.limit(k)
			.collect(Collectors.toList());
	} 
	
	public Network WeightedCenter() {
		
		return new Network();
	}
	
	private double diff(Network point , double input) {
		return (point.getSignal()== 0)? 100 : Math.max(Math.abs(input - point.getSignal()), 3);
	}
	
	private double weight(double diff, double input) {
		return 10_000/(Math.pow(diff, 0.4) * Math.pow(input, 0.4));
	}
	
	private double pi(double weight_1, double weight_2, double weight_3) {
		return weight_1*weight_2*weight_3;
	}
	
	private double weight(double pi_1, double pi_2, double pi_3) {
		return pi_1*pi_2*pi_3;
	}
	
}
