package projet00;

import java.util.List;

public class FilteredByLocation extends Filter{
	/** The file. */
	protected List<List<Network>> file;
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private double radius , latitude , longitude;
	
	public FilteredByLocation(List<List<Network>> csvList ,double radius ,double latitude, double longitude) {
		this.file = csvList;
		this.filteredFile = null;
		this.radius = radius;
		this.latitude = latitude;
		this.longitude = longitude;
		filter();
	}
	@Override
	public String filter() {
		if(!this.file.isEmpty()) {
			for (List<Network> runList: this.file) {
				if(runList.size() >= 1) {
					for (Network network : runList) {
						wifiSpot = network;
						if(comperable()) {
							this.filteredFile.add(network);
						}
					}
				}	
			}
			return "Filtered Succeed!";
		}else {
			return "Database is empty!";
			}
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
		return this.filteredFile;
	}

}
