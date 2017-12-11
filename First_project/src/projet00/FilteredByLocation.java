package projet00;

import java.util.List;

public class FilteredByLocation extends Filter{
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private double radius , latitude , longitude;
	
	public FilteredByLocation(List<List<Network>> csvList ,double radius ,double latitude, double longitude) {
		super();
		this.radius = radius;
		this.latitude = latitude;
		this.longitude = longitude;
		filter();
	}

	@Override
	public boolean comperable() {
		return radius >= CoordDistance(latitude, longitude ,wifiSpot);
	}
	/*Calculate distance between points*/
	double CoordDistance(double latitude, double longitude, Network point)
	{
	    return 6371 * Math.acos(
	        Math.sin(latitude) * Math.sin(Double.parseDouble(point.getLat()))
	        + Math.cos(latitude) * Math.cos(Double.parseDouble(point.getLat())) * Math.cos(Double.parseDouble(point.getLon()) - longitude));
	}
	@Override
	public List<Network> getFilteredFile() {
		return filteredFile;
	}

}
