package projet00;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.Templates;

public class csvFilter {
	private HashMap<String, Integer> keyIndex;
	private List<String []> file;

	public csvFilter() {
		this.keyIndex = null;
		this.file = null;
	}

	/**
	 * @author adiel ,adi and yuda
	 * @category Filter by MAC and SSID
	 * @param file , MACorSSID , parameter
	 * @return list
	 */
	public void filterByMACorSSID(String file, String MACorSSID, String parameter) throws NumberFormatException, IOException {

		try {

			//Read file
			FileReader readFile = new FileReader(file);
			BufferedReader fileOpen = new BufferedReader(readFile);

			this.file = new ArrayList <String[]> ();

			//Title index
			this.keyIndex = new HashMap<String,Integer>();

			String line = fileOpen.readLine();

			String[] tempArr = line.split(" , ");
			String[] temp = new String[9];
			int counter = 0;

			//{MAC : 0 , SSID : 1 , AuthMode : 2 ....}, get title index
			for (String csvTtitle : tempArr) {
				this.keyIndex.put(csvTtitle.trim(), counter++);
			} 

			line = fileOpen.readLine();
			//Print by filter
			while(line != null) {
				tempArr = line.split(" , ");
				if(Integer.parseInt( tempArr[keyIndex.get("WiFi networks")] ) > 1){
					//Print more then one wifi spot
					for (int i = 1; i <= Integer.parseInt( tempArr[keyIndex.get("WiFi networks")] ); i++) {
						if(tempArr[keyIndex.get(MACorSSID.trim() + " " + i)] != null 
								&& tempArr[keyIndex.get(MACorSSID.trim() + " " + i)].trim().equalsIgnoreCase(parameter.trim())) {
							temp = new String[]{ tempArr[keyIndex.get("Time")], tempArr[keyIndex.get("ID")], 
									tempArr[keyIndex.get("Lat")], tempArr[keyIndex.get("Lon")], tempArr[keyIndex.get("Alt")],
									tempArr[keyIndex.get("SSID " + i)], tempArr[keyIndex.get("MAC " + i)], 
									tempArr[keyIndex.get("Frequncy " + i)], tempArr[keyIndex.get("Signal " + i)]};
						}
					}
				}else {
					//Only one wifi spot
					if(tempArr[keyIndex.get(MACorSSID.trim() + " " + 1)] != null 
							&& tempArr[keyIndex.get(MACorSSID.trim() + " " + 1)].trim().equalsIgnoreCase(parameter.trim())) {
						temp = new String[]{ tempArr[keyIndex.get("Time")], tempArr[keyIndex.get("ID")], 
								tempArr[keyIndex.get("Lat")], tempArr[keyIndex.get("Lon")], tempArr[keyIndex.get("Alt")],
								tempArr[keyIndex.get("SSID " + 1)], tempArr[keyIndex.get("MAC " + 1)], 
								tempArr[keyIndex.get("Frequncy " + 1)], tempArr[keyIndex.get("Signal " + 1)]};
					}
				}
				line = fileOpen.readLine();
				if (temp != null) this.file.add(temp);
			}
			fileOpen.close();
		}catch (IOException e) {
			System.err.println(e.getMessage() + "\nProblem occurred while reading the file");

		}
	}

	/**
	 * @author adiel ,adi and yuda
	 * @category Print all file
	 * @param file 
	 * @return list
	 */
	public void All(String file) throws IOException {
		try {
			//Read file
			FileReader readFile = new FileReader(file);
			BufferedReader fileOpen = new BufferedReader(readFile);

			//Title index
			this.keyIndex = new HashMap<String,Integer>();
			this.file = new  ArrayList <String []> ();
			String line = fileOpen.readLine();

			String[] tempArr = line.split(" , ");
			String[] temp = new String[9];
			int counter = 0;

			//{MAC : 0 , SSID : 1 , AuthMode : 2 ....}, get title index
			for (String title : tempArr) {
				keyIndex.put(title.trim(), counter++);
			} 

			line = fileOpen.readLine();
			while(line != null) {
				tempArr = line.split(" , ");

				//Prints wifi spot
				for (int i = 1; i <= Integer.parseInt( tempArr[keyIndex.get("WiFi networks")] ); i++) {
					temp = new String[]{ tempArr[keyIndex.get("Time")], tempArr[keyIndex.get("ID")], 
							tempArr[keyIndex.get("Lat")], tempArr[keyIndex.get("Lon")], tempArr[keyIndex.get("Alt")],
							tempArr[keyIndex.get("SSID " + i)], tempArr[keyIndex.get("MAC " + i)], 
							tempArr[keyIndex.get("Frequncy " + i)], tempArr[keyIndex.get("Signal " + i)]};
					if (temp != null) this.file.add(temp);
					line = fileOpen.readLine();
				}
			}
			fileOpen.close();
		}
		catch(IOException e){
			System.err.println(e.getMessage());
			System.err.println("Kml file was not created!");
		}
	}

	/**
	 * @author adiel ,adi and yuda
	 * @category Filter by ID 
	 * @param file , ID , parameter
	 * @return list
	 */
	public void filterByID(String file, String ID, String parameter) throws NumberFormatException, IOException {
		try {
			//Read file
			FileReader readFile = new FileReader(file);
			BufferedReader fileOpen = new BufferedReader(readFile);

			//Title index
			HashMap<String, Integer>keyIndex = new HashMap<String,Integer>();
			this.file = new  ArrayList <String []> ();

			String line = fileOpen.readLine();

			String[] tempArr = line.split(" , ");
			String[] temp = new String[9];
			int counter = 0;

			//{MAC : 0 , SSID : 1 , AuthMode : 2 ....}, get title index
			for (String csvTtitle : tempArr) {
				keyIndex.put(csvTtitle.trim(), counter++);
			} 

			line = fileOpen.readLine();
			//Print file by ID
			while(line != null) {
				tempArr = line.split(" , ");
					//Prints wifi spot
					if(tempArr[keyIndex.get(ID.trim())] != null 
							&& tempArr[keyIndex.get(ID.trim())].trim().equalsIgnoreCase(parameter.trim())) {
						for (int i = 1; i <= Integer.parseInt( tempArr[keyIndex.get("WiFi networks")] ); i++) {
							temp = new String[]{ tempArr[keyIndex.get("Time")], tempArr[keyIndex.get("ID")], 
									tempArr[keyIndex.get("Lat")], tempArr[keyIndex.get("Lon")], tempArr[keyIndex.get("Alt")],
									tempArr[keyIndex.get("SSID " + i)], tempArr[keyIndex.get("MAC " + i)], 
									tempArr[keyIndex.get("Frequncy " + i)], tempArr[keyIndex.get("Signal " + i)]};
							if (temp != null) this.file.add(temp);
						}
					}
				line = fileOpen.readLine();
			}
			fileOpen.close();
		}catch (IOException e) {
			System.err.println(e.getMessage() + "\nProblem occurred while reading the file");

		}
	}
	
	/**
	 * @author adiel ,adi and yuda
	 * @category Filter by Time 
	 * @param file , start , end
	 * @return list
	 */
	public void filterByTime(String file, String start, String end)throws IOException{
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date startTime =  df.parse(start.trim());
			Date endTime = df.parse(end.trim());

			//Read file
			FileReader readFile = new FileReader(file);
			BufferedReader fileOpen = new BufferedReader(readFile);

			//Title index
			HashMap<String, Integer>keyIndex = new HashMap<String,Integer>();
			this.file = new  ArrayList <String []> ();

			String line = fileOpen.readLine();

			String[] tempArr = line.split(" , ");
			String[] temp = new String[9];
			int counter = 0;

			//{MAC : 0 , SSID : 1 , AuthMode : 2 ....}, get title index
			for (String csvTtitle : tempArr) {
				keyIndex.put(csvTtitle.trim(), counter++);
			} 

			line = fileOpen.readLine();
			//Print file by ID
			while(line != null) {
				tempArr = line.split(" , ");
					//Print more then one wifi spot
					if(tempArr[keyIndex.get("Time")] != null ) {
						Date timeToCompare = df.parse(tempArr[keyIndex.get("Time")].trim());
						if(( timeToCompare.after(startTime) && timeToCompare.before(endTime) ) 
								|| ( timeToCompare.equals(startTime) || timeToCompare.equals(endTime) ))
							for (int i = 1; i <= Integer.parseInt( tempArr[keyIndex.get("WiFi networks")] ); i++) {
								temp = new String[]{ tempArr[keyIndex.get("Time")], tempArr[keyIndex.get("ID")], 
										tempArr[keyIndex.get("Lat")], tempArr[keyIndex.get("Lon")], tempArr[keyIndex.get("Alt")],
										tempArr[keyIndex.get("SSID " + i)], tempArr[keyIndex.get("MAC " + i)], 
										tempArr[keyIndex.get("Frequncy " + i)], tempArr[keyIndex.get("Signal " + i)]};
								if (temp != null) this.file.add(temp);

							}
					}
				line = fileOpen.readLine();
			}
			fileOpen.close();

		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}catch (IOException | NullPointerException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * @author adiel ,adi and yuda
	 * @return update keyIndex
	 */
	public HashMap<String, Integer> getKeyIndex() {
		HashMap<String, Integer> newKeys= new HashMap<>();
		newKeys.put("Time",0);
		newKeys.put("ID" , 1);
		newKeys.put("Lat" , 2);
		newKeys.put("Lon" , 3);
		newKeys.put("Alt" , 4);
		newKeys.put("SSID" , 5);
		newKeys.put("MAC" , 6);
		newKeys.put("Frequncy" , 7);
		newKeys.put("Signal" , 8);
		return newKeys;
	}


	/**
	 * @author adiel ,adi and yuda
	 * @return file
	 */
	public List<String[]> getFile() {
		return file;
	}
}


