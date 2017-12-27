package projet00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RemoveDuplicate extends Filter{
	/*The file, The final database*/
	protected List<List<Network>> file, filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*HashMap of Object*/
	private HashMap<String, Integer> keyOfMAC;
	private int size=0;
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
		List<Network> tempList = new ArrayList<>();
		if(!this.file.isEmpty()) {
			for (List<Network> runList: this.file) {
				if(runList.size() >= 1) {
					for (Network network : runList) {
						wifiSpot = network;
						if(comperable()) {
							tempList.add(network);
							keyOfMAC.put(network.getMac(), 1);
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

	@Override
	public boolean comperable() {
		if(keyOfMAC.isEmpty()||!keyOfMAC.containsKey(wifiSpot.getMac())) return true;
		else return false;
		
	}

	@Override
	public List<List<Network>> getFilteredFile() {
		return this.filteredFile;
	}
	public int getSize() {
		return this.size;
	}
}
