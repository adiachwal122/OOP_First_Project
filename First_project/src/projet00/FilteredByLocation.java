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
	/**
	 * Instantiates a new filter by location.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList 
	 * @param radius
	 * @param latitude
	 * @param longitude
	 */
	public FilteredByLocation(List<List<Network>> csvList ,double radius ,double latitude, double longitude) {
		this.file = csvList;
		this.filteredFile = null;
		this.radius = radius;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	/*
	 *After constract Filter by location, run filter function 
	 *@return String (Succed of Fail)
	 * */
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
	/*
	 *Comperable type
	 *If the distance between te object to
	 *the given point is smaller then radius 
	 *@return TRUE 
	 *else
	 *@return FALSE
	 * */
	@Override
	public boolean comperable() {
		return radius >= CoordDistance(latitude, longitude ,wifiSpot);
	}
	/*Calculate distance between two points
	 * @return double
	 * */
	double CoordDistance(double latitude, double longitude, Network point)
	{
	    return 6371 * Math.acos(
	        Math.sin(latitude) * Math.sin(point.getLat())
	        + Math.cos(latitude) * Math.cos(point.getLat()) * Math.cos(point.getLon() - longitude));
	}
	/*
	 * Filtered database
	 * @return List<Network>
	 */
	@Override
	public List<Network> getFilteredFile() {
		return this.filteredFile;
	}

}
