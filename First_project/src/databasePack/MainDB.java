package databasePack;

import java.util.ArrayList;
import java.util.List;

public class MainDB {
	private List<List<Network>> _database;

	public MainDB() {
		_database = new ArrayList<>();
	}
	
	public MainDB(List<List<Network>> database) {
		this._database = new ArrayList<>();
		this._database.addAll(database);
	}
	
	public boolean add(List<List<Network>> data) {
		if(data.get(0).get(0) instanceof Network) {
			_database.addAll(data);
			return true;
		}else return false;
	}

	public boolean addList(List<Network> data) {
		if(data.get(0) instanceof Network) {
			_database.add(data);
			return true;
		}else return false;
	}
	
	
}
