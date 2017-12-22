package projet00;

import java.util.ArrayList;
import java.util.List;

public class FilterByMAC extends Filter{
	/** The file. */
	protected List<List<Network>> file;
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String parameter;
	/**
	 * Instantiates a new filter by MAC.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList 
	 * @param parameter
	 */
	public FilterByMAC(List<List<Network>> csvList ,String parameter) {
		this.file = csvList;
		this.filteredFile = new ArrayList<>();
		this.parameter = parameter;
	}
	/*
	 *After constract Filter by MAC, run filter function 
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
							this.filteredFile.add(wifiSpot);
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
	 *If the MAC of the object is equal
	 *to the given MAC (String) 
	 *@return TRUE 
	 *else
	 *@return FALSE
	 */
	@Override
	public boolean comperable() {
		return wifiSpot.getMac().equals(parameter.trim());
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
