package projet00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RemoveDuplicate extends Filter{
	/*The file*/
	protected List<List<Network>> file;
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*HashMap of Object*/
	private HashMap<String, Integer> keyOfMAC;
	/*
	 * Get list, of list of Object (Network) and copy the object ,without repeted MAC
	 * @author adiel, adi and yuda
	 * @param List<List<Network>> List
	 *  */
	public RemoveDuplicate(List<List<Network>> csvList) {
		this.file = csvList;
		this.filteredFile = new ArrayList<>();
		keyOfMAC = new HashMap<>();
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
		if(keyOfMAC.isEmpty()||!keyOfMAC.containsKey(wifiSpot.getMac())) return true;
		else return false;
		
	}

	@Override
	public List<Network> getFilteredFile() {
		return this.filteredFile;
	}

}
