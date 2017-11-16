package projet00;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.*;

/**
 * 
 * @author Adiel
 * @since 29/10/2017 
 * @see:
 *      *https://www.tutorialspoint.com/java/io/file_listfiles.htm
 *      *http://www.homeandlearn.co.uk/java/read_a_textfile_in_java.html
 *      *https://labs.micromata.de/projects/jak/implementing.html
 * 
 */

public class FileToCsv {

	private List<List<Network>> _fileTable;
	private Network wifiObj;

	//@Constractors
	//No path
	public FileToCsv() throws IOException {
		System.err.println("No path selected!");
	}

	//Selecting treatment type
	public FileToCsv(String path) throws IOException {
		try {
			/**
			 * Read all file from path
			 * Function copy from https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java 
			 **/
			List<File> filesInFolder = Files.walk(Paths.get(path))
					.filter(Files::isRegularFile)
					.map(Path::toFile)
					.collect(Collectors.toList());

			//Folder is empty
			if(filesInFolder.isEmpty()) System.err.println("Folder is empty!");

			//Only one file in folder
			if(filesInFolder.size()==1) {	
				String typeOfFile = fileType(path);
				if (typeOfFile.equals("CSV"))
					csvInput(path);
				/*In develop
				 * else if (typeOfFile.equals("KML")) 
					kmlToCsv(path);*/
				else unauthorizedFile(filesInFolder.get(0).getName());

			}

			//More then one file in folder
			else if (filesInFolder.size()>1) {
				while(!filesInFolder.isEmpty()) {
					System.out.println(filesInFolder.get(0).getName());

					String typeOfFile = fileType(filesInFolder.get(0).getName());
					String pathOfEachFile = filesInFolder.get(0).getPath();

					if (typeOfFile.equals("CSV"))
						csvInput(pathOfEachFile);
					/*In develop
					 * else if (typeOfFile.equals("KML")) 
						kmlToCsv(path);*/
					else unauthorizedFile(filesInFolder.get(0).getName());

					filesInFolder.remove(0);
					
				}
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	//Csv input
	public void csvInput(String csvPath) throws IOException {
		try {
			File input_csv = new File (csvPath);
			File output_csv = new File ("temp_csv.csv");
			Files.copy(input_csv.toPath(), output_csv.toPath(), REPLACE_EXISTING);
			
			System.out.println("The CSV copy successfully!");
			getOrder("temp_csv.csv");
			print();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Sorry, somethings went wrong! \nPlease check if your file is corrupted");
		}
	}

	/**
	 * Kml to csv for future devl
	 * @param kmlPath
	 * https://labs.micromata.de/projects/jak/kml-in-the-java-world.html
	 */
	public void kmlToCsv(String kmlPath) {

	}

	
	/**
	 * 
	 * Order the csv  
	 * @throws IOException 
	 */
	public void getOrder(String path) throws IOException{
		try {
			//Read file
			FileReader readFile = new FileReader(path);
			BufferedReader fileOpen = new BufferedReader(readFile);
			String model = null , stop = null;
			
			//Main list
			this._fileTable = new ArrayList<List<Network>>();
			
			//Temp list
			List<Network> line_of_table = new ArrayList<Network>();
			
			//Title index
			HashMap<String, Integer>keyIndex = new HashMap<String,Integer>();
			
			//Temp String[] 
			String [] orFile = fileOpen.readLine().split(",");
			
			//Array of required title
			String[] title = {"MAC","SSID","AuthMode","FirstSeen","Channel","RSSI","CurrentLatitude",
							"CurrentLongitude","AltitudeMeters"};
			
			//Get ID
			for (String temp_cell : orFile) {
				if(temp_cell != null && temp_cell.contains("model")) {	
					model = temp_cell.split("=")[1].trim();
					break;
				}
			}
			orFile = fileOpen.readLine().split(",");
			
			//{MAC : 0 , SSID : 1 , AuthMode : 2 ....}, get title index
			for (int i = 0 ; i < title.length; i++) {
				keyIndex.put(orFile[i].trim(), i);
			}
			stop = fileOpen.readLine();
			if(stop != null)
				orFile = stop.split(",");			
			//Create table
			while(stop != null) {
						//Add to line of list
						wifiObj = new Network(orFile[keyIndex.get("SSID")] , orFile[keyIndex.get("MAC")] , 
								Integer.parseInt(orFile[keyIndex.get("Channel")]) , 
								Integer.parseInt(orFile[keyIndex.get("RSSI")]), 
								orFile[keyIndex.get("FirstSeen")], model, 
								orFile[keyIndex.get("CurrentLatitude")] ,
								orFile[keyIndex.get("CurrentLongitude")] ,
								orFile[keyIndex.get("AltitudeMeters")]);

						line_of_table.add(wifiObj);
							stop = fileOpen.readLine();
							if(stop != null)
								orFile = stop.split(",");
			}
		/**	//Sort by time
			Collections.sort(line_of_table,new Comparator<Network>() {
				
				public int compare(Network wifi1, Network wifi2){
					try {
						// 31/10/2017  01:34:06 
						    Date first=new SimpleDateFormat("dd/MM/yyyy").parse(wifi1.getTime().trim().split(" ")[0]);  
						    Date second=new SimpleDateFormat("dd/MM/yyyy").parse(wifi2.getTime().trim().split(" ")[0]);  

							if(first.before(second)) 
								return 1;
							if(first.equals(second))
								return 0;
							
							return -1;
					} catch (ParseException e) {
						e.printStackTrace();
					}  
					return 0;
				}
			});**/
			//Move to main table, _fileTable, by date
			int fromIndex = 0;
			for (int i = 0 ; i < line_of_table.size()-1 ; i++) {
				if(!line_of_table.get(i).getTime().trim().equals(line_of_table.get(i+1).getTime().trim())) {
					this._fileTable.add(line_of_table.subList(fromIndex, i));
					fromIndex = i+1;
				}
			}
			if(fromIndex == 0 || fromIndex < line_of_table.size())
				this._fileTable.add(line_of_table.subList(fromIndex, line_of_table.size()));
			System.out.println(this._fileTable.toString());

			//Sort by signal
			for (List<Network> obj : this._fileTable) {
				Collections.sort(obj,new Comparator<Network>() {
					public int compare(Network wifi1, Network wifi2) {
						if(Math.abs(wifi1.getSignal()) < Math.abs(wifi2.getSignal()))
							return -1;
						if(Math.abs(wifi1.getSignal()) == Math.abs(wifi2.getSignal()))
							return 0;
						return 1;
					}
				});
			}
			System.out.println(this._fileTable.toString());
			for (List<Network> net : this._fileTable) {
				if(net.size() > 10) {
					net = net.subList(0, 9);
				}
			}

			//Close session
			readFile.close();
			System.out.println("The CSV creat successfully!");
			//Delete temp file
			File delete = new File("test_csv.csv");
			Files.deleteIfExists(delete.toPath());

		}catch(NullPointerException|IOException e) {
			System.err.println(e.getMessage());
			System.err.println("Sorry, somethings went wrong! \nPlease check if your file is corrupted");
		}
	}
	
	private void print() throws IndexOutOfBoundsException {
		try {
		BufferedWriter makeFile = new BufferedWriter(new FileWriter("final_csv.csv"));
		String [] firstHeader = {"Time", "ID", "Lat", "Lon", "Alt", "WiFi networks"},
				secondHeader = {"SSID", "MAC", "Frequncy", "Signal"};
		
		//First required titles
		for (String newHeader : firstHeader) {
			makeFile.write(newHeader + " , ");
		}
		//Second required titles
		for (int i = 1; i < 11; i++) {
			for (String newHeader : secondHeader) {
				makeFile.write(newHeader +" " + i + " , ");
			}
		}
		makeFile.newLine();
		
		//Write to file
		if(this._fileTable.size() != 0) {
			for (List<Network> runList: this._fileTable) {
				int count = 0;
				if(runList != null) {
					if(runList.size() >= 10) {
						makeFile.write(runList.get(0).getTime() + " , " + runList.get(0).getId() + " , "
								+runList.get(0).getLat() + " , " + runList.get(0).getLon() + " , "
								+ runList.get(0).getAlt() + " , " + 10);
					}else {
					makeFile.write(runList.get(0).getTime() + " , " + runList.get(0).getId() + " , "
							+runList.get(0).getLat() + " , " + runList.get(0).getLon() + " , "
							+ runList.get(0).getAlt() + " , " + runList.size());
					}
					for (Network network : runList) {
						if(count >10) break;
						if(runList.size() <= 10 ) {
						makeFile.write( " , " + network.getSsid() + " , " + network.getMac() + " , " 
								+ network.getFrequncy() + " , " + network.getSignal());
						}else{
							count++;
							makeFile.write( " , " + network.getSsid() + " , " + network.getMac() + " , " 
									+ network.getFrequncy() + " , " + network.getSignal());
						}
					}
					makeFile.newLine();
				}
				
			}
			
		}

		//close session
		makeFile.close();
		}catch(IndexOutOfBoundsException | IOException e) {
			System.err.println(e.getMessage());
			System.out.println("Sorry, somethings went wrong! \nPlease check if your file is corrupted");
		}
		
	}

	//Check of file type 
	public String fileType (String filePath) {
		//Cut the type from file (exemple: txt)
		int length = filePath.length()-3;
		String type = filePath.substring(length, filePath.length());
		return type.toUpperCase();
	}

	//Check if file type is unauthorized 
	public void unauthorizedFile(String unauthorizedFile) {
		System.err.println(unauthorizedFile+" - " +"Unauthorized File, please change the file to authorized file (csv,txt or kml)!"); 
	}

}
