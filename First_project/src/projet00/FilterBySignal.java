package projet00;

import java.util.List;

public class FilterBySignal extends Filter{
	/** The file. */
	protected List<List<Network>> file;
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String from , till;
	
	public FilterBySignal(List<List<Network>> csvList ,String from , String till) {
		this.file = csvList;
		this.filteredFile = null;
		this.from = from;
		this.till = till;
		System.out.println(filter());
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
		return wifiSpot.getSignal() >= Integer.parseInt(from) && wifiSpot.getSignal() <= Integer.parseInt(till);
	}
	@Override
	public List<Network> getFilteredFile() {
		return this.filteredFile;
	}
}
