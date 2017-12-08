package projet00;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

// TODO: Auto-generated Javadoc
/**
 * The Class ReadCsv.
 */
public class ReadCsv {
	private List<List<Network>> _fileTable = new ArrayList<List<Network>>();;
	private Network wifiObj;


	/**
	 * Instantiates a new read csv.
	 * No path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	
	public ReadCsv() throws IOException {
		System.err.println("No path selected!");
	}

	/**
	 * Instantiates a new read csv.
	 *
	 * @author adiel, adi and yuda
	 * @param String path
	 * @return list of csv file
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see https://www.tutorialspoint.com/java/io/file_listfiles.htm
	 * @see http://www.homeandlearn.co.uk/java/read_a_textfile_in_java.html
	 */
	public ReadCsv(String path) throws IOException {
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

			//show all file in folder or read file
			else if (filesInFolder.size()> 1) {
				while(!filesInFolder.isEmpty()) {
					System.out.println(filesInFolder.get(0).getName());
					String typeOfFile = fileType(filesInFolder.get(0).getName());
					String pathOfEachFile = filesInFolder.get(0).getPath();

					if (typeOfFile.equals("CSV"))
						csvInput(pathOfEachFile);
					else unauthorizedFile(filesInFolder.get(0).getName());

					filesInFolder.remove(0);
				}
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Csv input.
	 *
	 * @param String csvPath
	 * @return copy csv file to workspace
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void csvInput(String csvPath) throws IOException {
		try {
			File input_csv = new File (csvPath);
			File output_csv = new File ("temp_csv.csv");
			Files.copy(input_csv.toPath(), output_csv.toPath(), REPLACE_EXISTING);

			System.out.println("The CSV copy successfully!");
			getOrder("temp_csv.csv");
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Sorry, somethings went wrong! \nPlease check if your file is corrupted");
		}
	}
	
	/**
	 * Order the csv.
	 *
	 * @param String path
	 * @return list of csv file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void getOrder(String path) throws IOException{
		try {
			//Read file
			FileReader readFile = new FileReader(path);
			BufferedReader fileOpen = new BufferedReader(readFile);
			String model = "NaN" , stop = null;

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
			if(stop != null) orFile = stop.split(",");	
			
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
				if(stop != null) orFile = stop.split(",");
			}

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

			//			System.out.println(this._fileTable.toString());

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
			//			System.out.println(this._fileTable.toString());

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
	
	/**
	 * Gets the file table.
	 *
	 * @return List<List<Network>>
	 */
	public List<List<Network>> get_fileTable() {
		return _fileTable;
	}

	/**
	 * File type.
	 *
	 * @param filePath the file path
	 * @return type of file
	 */
	public String fileType (String filePath) {
		//Cut the type from file (exemple: txt)
		int length = filePath.length()-3;
		String type = filePath.substring(length, filePath.length());
		return type.toUpperCase();
	}

	/**
	 * Unauthorized file.
	 */
	//Check if file type is unauthorized 
	public void unauthorizedFile(String unauthorizedFile) {
		System.err.println(unauthorizedFile+" - " +"Unauthorized File, please change the file to authorized file (csv,txt or kml)!"); 
	}

}
