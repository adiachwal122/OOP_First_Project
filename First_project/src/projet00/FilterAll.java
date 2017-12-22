package projet00;

import java.util.List;

public class FilterAll extends Filter{
	/** The file. */
	protected List<List<Network>> file;
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/**
	 * Instantiates a new filter by SSID.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList 
	 */
	public FilterAll(List<List<Network>> csvList) {
		this.file = csvList;
		this.filteredFile = null;
	}
	/*
	 *After constract Filter by ID, run filter function 
	 *@return String (Succed of Fail)
	 */
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
	 *@return TRUE 
	 */
	@Override
	public boolean comperable() {
		return true;
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
