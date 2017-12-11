package projet00;

import java.util.List;

public class FilterAll extends Filter{
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	
	public FilterAll(List<List<Network>> csvList) {
		super();
	}
	
	@Override
	public boolean comperable() {
		return true;
	}
	@Override
	public List<Network> getFilteredFile() {
		return filteredFile;
	}
}
