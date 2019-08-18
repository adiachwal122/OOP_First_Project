package filtersPack;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import databasePack.Network;

public class FilterByDate extends Filter{
	/** The file. The final database*/
	protected List<List<Network>> file, filteredFile;
	/*Network object*/
	protected Network wifiSpot;
	/*Parameter from user*/
	private String start , end;
	private int size=0;
	/**
	 * Instantiates a new filter by Date.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList 
	 * @param start
	 * @param end
	 */
	public FilterByDate(List<List<Network>> csvList , String start , String end) {
		this.file = new ArrayList<>(csvList); 
		this.filteredFile = new ArrayList<>();
		this.start = start;
		this.end = end;
	}
	/*
	 *After constract Filter by Date, run filter function 
	 *@return String (Succed of Fail)
	 */
	@Override
	public String filter() {
		List<Network> tempList;
		if(!this.file.isEmpty()) {
			for (List<Network> runList: this.file) {
				if(runList.size() >= 1) {
					tempList = new ArrayList<>();
					for (Network network : runList) {
						wifiSpot = new Network(network);
						if(comperable()) {
							tempList.add(network);
						}
					}
					if(tempList.size() >=1) {
						size += tempList.size();
						this.filteredFile.add(tempList);
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
	public List<List<Network>> getFilteredFile() {
		return this.filteredFile;
	}
	/*Gets number of elements after filter*/
	public int getSize() {
		return size;
	}
}
