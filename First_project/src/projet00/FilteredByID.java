package projet00;

import java.util.List;

public class FilteredByID extends Filter{
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String parameter;
	
	public FilteredByID(List<List<Network>> csvList ,String parameter) {
		super();
		this.parameter = parameter;
		filter();
	}

	@Override
	public boolean comperable() {
		return wifiSpot.getSsid().equals(parameter.trim());
	}
	@Override
	public List<Network> getFilteredFile() {
		return filteredFile;
	}
}
