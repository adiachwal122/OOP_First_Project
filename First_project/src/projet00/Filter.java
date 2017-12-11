package projet00;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Filtered.
 */
public abstract class Filter {
	/** The file. */
	protected List<List<Network>> file;
	/*The final database*/
	protected List<Network> filteredFile;
	
	protected Network wifiSpot;
/**
 * Instantiates a new filtered.
 */
public Filter() {
		this.file = null;
		this.filteredFile = null;
	}
/**
 * Instantiates a new filtered.
 */
public Filter(List<List<Network>> csvList) {
	this.file = csvList;
	this.filteredFile = null;
	filter();
}
/*
 * Filter function
 * */
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

public abstract boolean comperable();

public abstract List<Network> getFilteredFile();

}

