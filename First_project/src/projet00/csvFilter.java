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

// TODO: Auto-generated Javadoc
/**
 * The Class csvFilter.
 */
public class csvFilter {
	
	/** The key index. */
	private HashMap<String, Integer> keyIndex;
	
	/** The file. */
	private List<String []> file;

	/**
	 * Instantiates a new csv filter.
	 */
	public csvFilter() {
		this.keyIndex = null;
		this.file = null;
	}

	/**
	 * Filter by MAC or SSID.
	 *
	 * @author adiel ,adi and yuda
	 * @param String file
	 * @param String MACorSSID
	 * @param String parameter
	 * @return Void (make list)
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @category Filter by MAC and SSID
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
	 * Print All.
	 *
	 * @author adiel ,adi and yuda
	 * @param String file
	 * @return Void(make list)
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @category Print all file
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
	 * Filter by ID.
	 *
	 * @author adiel ,adi and yuda
	 * @param String file
	 * @param String ID
	 * @param String parameter
	 * @return Void(make list)
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @category Filter by ID
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
	 * Filter by time.
	 *
	 * @author adiel ,adi and yuda
	 * @param String file
	 * @param String start
	 * @param String end
	 * @return Void(make list)
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @category Filter by Time
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
	 * Gets the key index.
	 *
	 * @author adiel ,adi and yuda
	 * @return HashMap<String, Integer> keyIndex
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
	 * Gets the file.
	 *
	 * @author adiel ,adi and yuda
	 * @return List<String[]> file
	 */
	public List<String[]> getFile() {
		return file;
	}
}


