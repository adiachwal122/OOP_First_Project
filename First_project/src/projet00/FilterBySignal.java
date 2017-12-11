package projet00;

import java.util.List;

public class FilterBySignal extends Filter{
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String from , till;
	
	public FilterBySignal(List<List<Network>> csvList ,String from , String till) {
		super();
		this.from = from;
		this.till = till;
		filter();
	}

	@Override
	public boolean comperable() {
		return wifiSpot.getSignal() >= Integer.parseInt(from) && wifiSpot.getSignal() <= Integer.parseInt(till);
	}
	@Override
	public List<Network> getFilteredFile() {
		return filteredFile;
	}
}
