package projet00;

import java.util.ArrayList;
import java.util.List;

public class FilterByMAC extends Filter{
	/** The file. The final database*/
	protected List<List<Network>> file, filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String parameter;
	private int size=0;
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
		List<Network> tempList = new ArrayList<>();
		if(!this.file.isEmpty()) {
			for (List<Network> runList: this.file) {
				if(runList.size() >= 1) {
					for (Network network : runList) {
						wifiSpot = network;
						if(comperable()) {
							tempList.add(network);
						}
					}
					if(tempList.size() >=1) {
						size += tempList.size();
						this.filteredFile.add(tempList);
						tempList.clear();
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
	public List<List<Network>> getFilteredFile() {
		return this.filteredFile;
	}
	public int getSize() {
		return size;
	}
}
