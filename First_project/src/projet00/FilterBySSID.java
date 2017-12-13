package projet00;

import java.util.List;

public class FilterBySSID  extends Filter{
	/** The file. */
	protected List<List<Network>> file;
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String parameter;
	
	public FilterBySSID(List<List<Network>> csvList ,String parameter) {
		this.file = csvList;
		this.filteredFile = null;
		this.parameter = parameter;
		filter();
	}
	
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
		return wifiSpot.getId().equals(parameter.trim());	
		}

	@Override
	public List<Network> getFilteredFile() {
		return this.filteredFile;
	}
}
