package databasePack;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainDB {
	private List<List<Network>> _database;

	public MainDB() {
		_database = new ArrayList<>();
	}
	
	public MainDB(List<List<Network>> database) {
		this._database = new ArrayList<>();
		this._database.addAll(database);
		removeEmpty();
		sort();
	}
	
	public boolean add(List<List<Network>> data) {
		if(data.get(0).get(0) instanceof Network) {
			_database.addAll(data);
			removeEmpty();
			sort();
			return true;
		}else return false;
	}

	public boolean addList(List<Network> data) {
		if(data.get(0) instanceof Network) {
			_database.add(data);
			removeEmpty();
			sort();
			return true;
		}else return false;
	}
	
	public void removeAll() {
		if(isEmpty()) {
			_database.removeAll(_database);
		}
	}	
	
	public void remove(int i) {
		if(!isEmpty()) {
			_database.remove(i);
	
		}
	}
	
	private void sort() {
		//Sort by Date
			Collections.sort(_database,new Comparator<List<Network>>() {
				public int compare(List<Network> wifiLine1, List<Network> wifiLine2) {
					try {
						DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						Date wifi1Time =  df1.parse(wifiLine1.get(0).getTime().trim());
						Date wifi2Time =  df1.parse(wifiLine2.get(0).getTime().trim());
						if(wifi2Time.before(wifi1Time))
							return -1;
						if(wifi1Time.equals(wifi2Time))
							return 0;
						return 1;
					}catch (ParseException e) {
						e.getCause();
						return 0;
					}
				}
			});		
	}
	
	private void removeEmpty() {
		if(!isEmpty()) {
			for (List<Network> list : _database) {
				if(list.isEmpty()) _database.remove(list);
			}
		}
	}
	
	public boolean hasMAC(String name) {
		if(!isEmpty()) {
			for (List<Network> list : _database) {
				for (Network network : list) {
					if (network.getMac().equalsIgnoreCase(name.trim())) return true;
				}
			}
		}
		return false;
		
	}
	
	public boolean hasID(String name) {
		if(!isEmpty()) {
			for (List<Network> list : _database) {
				for (Network network : list) {
					if (network.getId().equalsIgnoreCase(name.trim())) return true;
				}
			}
		}
		return false;
	}
	
	public boolean isEmpty() {
		return _database.isEmpty();
	}
	
}
