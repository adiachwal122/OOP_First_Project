package projet00;

import java.util.ArrayList;
import java.util.List;

public class FilterBySignal extends Filter{
	/** The file. The final database*/
	protected List<List<Network>> file, filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String from , till;
	private int size=0;
	/**
	 * Instantiates a new filter by signal.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList 
	 * @param from
	 * @param till
	 */
	public FilterBySignal(List<List<Network>> csvList ,String from , String till) {
		this.file = csvList;
		this.filteredFile = new ArrayList<>();
		this.from = from;
		this.till = till;
	}
	/*
	 *After constract Filter by Signal, run filter function 
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
	 *If the signal of each object is between
	 *the given range (from to untill) 
	 *@return TRUE 
	 *else
	 *@return FALSE
	 * */
	@Override
	public boolean comperable() {
		return wifiSpot.getSignal() >= Integer.parseInt(from) && wifiSpot.getSignal() <= Integer.parseInt(till);
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
