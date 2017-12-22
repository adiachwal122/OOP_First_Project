package projet00;

import java.util.ArrayList;
import java.util.List;

public class FilterByThreeMACs extends Filter{
	/** The file. */
	protected List<List<Network>> file;
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String parameter_1, parameter_2,parameter_3;
	/**
	 * Instantiates a new filter by Three MACs.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList 
	 * @param parameter_1
	 * @param parameter_2
	 * @param parameter_3
	 */
	public FilterByThreeMACs(List<List<Network>> csvList ,String parameter_1 ,String parameter_2 ,String parameter_3) {
		this.file = csvList;
		this.filteredFile = new ArrayList<>();
		this.parameter_1 = parameter_1;
		this.parameter_2 = parameter_2;
		this.parameter_3 = parameter_3;
	}
	/*
	 *After constract Filter by Three MACs, run filter function 
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
	 *to the given MACs (String) 
	 *@return TRUE 
	 *else
	 *@return FALSE
	 */
	@Override
	public boolean comperable() {
		return wifiSpot.getMac().equals(parameter_1.trim()) ||
				wifiSpot.getMac().equals(parameter_2.trim()) ||
				wifiSpot.getMac().equals(parameter_3.trim());
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
