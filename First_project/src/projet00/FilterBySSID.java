package projet00;

import java.util.List;

public class FilterBySSID  extends Filter{
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String parameter;
	
	public FilterBySSID(List<List<Network>> csvList ,String parameter) {
		super();
		this.parameter = parameter;
		filter();
	}

	@Override
	public boolean comperable() {
		return wifiSpot.getId().equals(parameter.trim());	
		}

	@Override
	public List<Network> getFilteredFile() {
		return filteredFile;
	}
}
