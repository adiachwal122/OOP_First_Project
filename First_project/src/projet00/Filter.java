package projet00;

import java.util.List;

/**
 * The abstract class Filter.
 */
public abstract class Filter {
	/** The file. *//*
	protected List<List<Network>> file;
	The final database
	protected List<Network> filteredFile;
	*/
/**
 * Instantiates a new filtered.
 */
/*public Filter() {
		this.file = null;
		this.filteredFile = null;
	}*/
/**
 * Instantiates a new filtered.
 */
/*public Filter(List<List<Network>> csvList) {
	this.file = csvList;
	this.filteredFile = null;
	System.out.println(filter());
}*/
/*
 * Filter function
 * After Building Filter By - object
 * run this function
 * @return String
 * */
public abstract String filter();
/*
 * comperable function
 * implement by each filter 
 * */
public abstract boolean comperable();
/*
 * get function
 * implement by each filter 
 * */
public abstract List<Network> getFilteredFile();

}


