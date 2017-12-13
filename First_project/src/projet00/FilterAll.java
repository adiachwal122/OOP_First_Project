package projet00;

import java.util.List;

public class FilterAll extends Filter{
	/** The file. */
	protected List<List<Network>> file;
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	
	public FilterAll(List<List<Network>> csvList) {
		this.file = csvList;
		this.filteredFile = null;
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
		return true;
	}
	@Override
	public List<Network> getFilteredFile() {
		return this.filteredFile;
	}
}
