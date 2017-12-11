package projet00;

import java.util.List;

public class FilterByMAC extends Filter{
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String parameter;
	
	public FilterByMAC(List<List<Network>> csvList ,String parameter) {
		super();
		this.parameter = parameter;
		filter();
	}

	@Override
	public boolean comperable() {
		return wifiSpot.getMac().equals(parameter.trim());
	}

	@Override
	public List<Network> getFilteredFile() {
		return filteredFile;
	}

}
