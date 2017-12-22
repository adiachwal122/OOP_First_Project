package projet00;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FilterByDate extends Filter{
	/** The file. */
	protected List<List<Network>> file;
	/*The final database*/
	protected List<Network> filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String start , end;
	/**
	 * Instantiates a new filter by Date.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList 
	 * @param start
	 * @param end
	 */
	public FilterByDate(List<List<Network>> csvList , String start , String end) {
		super();
		this.start = start;
		this.end = end;
	}
	/*
	 *After constract Filter by Date, run filter function 
	 *@return String (Succed of Fail)
	 */
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
	/*
	 *Comperable type
	 *If the date of each object is between
	 *the given range (start to end) 
	 *@return TRUE 
	 *else
	 *@return FALSE
	 * */
	@Override
	public boolean comperable() {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date wifiDate = df.parse(wifiSpot.getTime().trim());
			Date startTime =  df.parse(start);
			Date endTime = df.parse(end);
			return wifiDate.after(startTime) && wifiDate.before(endTime);
		} catch (ParseException e) {
			System.out.println("Problem occurred while data format!");
			e.printStackTrace();
			return false;
		}
	}
	/*
	 * Filtered database
	 * @return List<Network>
	 */
	@Override
	public List<Network> getFilteredFile() {
		return this.filteredFile;
	}
}
