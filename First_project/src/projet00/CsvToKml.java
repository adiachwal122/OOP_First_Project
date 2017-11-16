package projet00;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CsvToKml {

	public CsvToKml() {

	}
	/**
	 * Learned from:
	 *  https://labs.micromata.de/projects/jak/kml-in-the-java-world.html
	 * Creat Kml file
	 * @param KmlPath
	 */
	//Check if valide
	public boolean valid(String path) throws IOException {
		if(fileType(path) == "CSV") {
			try {
				//Read file
				FileReader readFile = new FileReader(path);
				BufferedReader fileOpen = new BufferedReader(readFile);
				
				String recognize = fileOpen.readLine().split(",")[0].trim();
				
				if (recognize == "Time") {
					fileOpen.close();
					return true;
				}
					unauthorizedFile(path);
					fileOpen.close();
					return false;
			}
			catch(IOException e){
				System.err.println(e.getMessage());
				System.err.println("Kml file was not created!");
				return false;
			}
		}else {
			unauthorizedFile(path);
			return false;
		}
	}
	
	//Filter by MAC, Signal, SSID or Frequncy
	public void filterBy(String file, String title, String parameter) throws NumberFormatException, IOException {
		if(valid(file)) {
			try {
				String bottom = "</Document>\r\n" + "</kml>";
				//Read file
				FileReader readFile = new FileReader("Header.txt");
				BufferedReader fileOpen = new BufferedReader(readFile);
				BufferedWriter makeFile = new BufferedWriter(new FileWriter("filterBy" + title + ".kml"));

				//Title index
				HashMap<String, Integer>keyIndex = new HashMap<String,Integer>();

				String line = fileOpen.readLine();
				//Copy all file (Header)
				while(line != null) {
					makeFile.write(line);
					line = fileOpen.readLine();
				}
				makeFile.newLine();

				readFile = new FileReader(file);
				fileOpen = new BufferedReader(readFile);
				
				line = fileOpen.readLine();
				String[] tempArr = line.split(" , ");
				int counter = 0;

				//{MAC : 0 , SSID : 1 , AuthMode : 2 ....}, get title index
				for (String csvTtitle : tempArr) {
					keyIndex.put(csvTtitle.trim(), counter++);
				} 

				line = fileOpen.readLine();
				//Print all file
				while(line!=null) {
					if(Integer.parseInt( tempArr[keyIndex.get(title.trim())] ) > 1){
						//Print more then one wifi spot
						for (int i = 1; i < Integer.parseInt( tempArr[keyIndex.get("WiFi networks")] ); i++) {
							if(tempArr[keyIndex.get(title.trim() + " " + i)].trim().equalsIgnoreCase(parameter.trim())) {
								makeFile.write(" <Placemark>\r\n" +  "<name>\r\n" + tempArr[keyIndex.get("ID")] + "</name>" 
										+ "	<description><![CDATA[" + " SSID: " + tempArr[keyIndex.get("SSID " + i)] 
												+ " MAC: "  + tempArr[keyIndex.get("MAC " + i)] + 
												" Frequncy: " + tempArr[keyIndex.get("Frequncy " + i)] + 
												" Signal: " + tempArr[keyIndex.get("Signal " + i)] + " ]]>");
								makeFile.write("\n </description>\r\n  <Point> \n<coordinates>" + tempArr[keyIndex.get("Lon")] + "," 
										+ tempArr[keyIndex.get("Lat")] + "," + tempArr[keyIndex.get("Alt")] + "\n" + "</coordinates>\r\n" + 
										"  </Point>\r\n" + "</Placemark>");
								line = fileOpen.readLine();
							}
						}

					}else {
						//Only one wifi spot
						if(tempArr[keyIndex.get(title.trim() + " " + 1)].trim().equalsIgnoreCase(parameter.trim())) {
							tempArr = line.split(" , ");
							makeFile.write(" <Placemark>\r\n" +  "<name>\r\n" + tempArr[keyIndex.get("ID")] + "</name>" 
									+ "	<description><![CDATA[" + " SSID: " + tempArr[keyIndex.get("SSID 1")] 
											+ " MAC: "  + tempArr[keyIndex.get("MAC 1")] + 
											" Frequncy: " + tempArr[keyIndex.get("Frequncy 1")] + 
											" Signal: " + tempArr[keyIndex.get("Signal 1")] + " ]]>");
							makeFile.write("\n </description>\r\n  <Point> \n<coordinates>" + 
									tempArr[keyIndex.get("Lon")] + "," 
									+ tempArr[keyIndex.get("Lat")] + "," + 
									tempArr[keyIndex.get("Alt")] + "\n" + "</coordinates>\r\n" + 
									"  </Point>\r\n" + "</Placemark>");
						}
						line = fileOpen.readLine();
					}
				makeFile.newLine();
				}
				makeFile.write(bottom);
				fileOpen.close();
				makeFile.close();
			}catch (IOException e) {
				System.err.println(e.getMessage() + "\nProblem occurred while reading the file");
	
			}
		}else {
			unauthorizedFile(file);
		}
	}
	//Print all file to kml
	public void All(String file) throws IOException {
try {
			
			String bottom = "</Document>\r\n" + "</kml>";
			//Read file
			FileReader readFile = new FileReader("Header.txt");
			BufferedReader fileOpen = new BufferedReader(readFile);
			BufferedWriter makeFile = new BufferedWriter(new FileWriter("final kml.kml"));
				
			//Title index
			HashMap<String, Integer>keyIndex = new HashMap<String,Integer>();
			
			String line = fileOpen.readLine();
			//Copy all file (Header)
			while(line != null) {
				makeFile.write(line);
				line = fileOpen.readLine();
			}
			makeFile.newLine();

			readFile = new FileReader(file);
			fileOpen = new BufferedReader(readFile);
			
			line = fileOpen.readLine();
			String[] tempArr = line.split(" , ");
			int counter = 0;
			
			//{MAC : 0 , SSID : 1 , AuthMode : 2 ....}, get title index
			for (String title : tempArr) {
				keyIndex.put(title.trim(), counter++);
			} 
			
			line = fileOpen.readLine();
			while(line != null) {
				
				tempArr = line.split(" , ");
				if(Integer.parseInt( tempArr[keyIndex.get("WiFi networks")] ) > 1) {
					//Print more then one wifi spot
					for (int i = 1; i < Integer.parseInt( tempArr[keyIndex.get("WiFi networks")] ); i++) {
						makeFile.write(" <Placemark>\r\n" +  "<name>\r\n" + tempArr[keyIndex.get("ID")] + "</name>" 
								+ "	<description><![CDATA[" + " SSID: " + tempArr[keyIndex.get("SSID " + i)] 
										+ " MAC: "  + tempArr[keyIndex.get("MAC " + i)] + 
										" Frequncy: " + tempArr[keyIndex.get("Frequncy " + i)] + 
										" Signal: " + tempArr[keyIndex.get("Signal " + i)] + " ]]>");
						makeFile.write("\n </description>\r\n  <Point> \n<coordinates>" + tempArr[keyIndex.get("Lon")] + "," 
								+ tempArr[keyIndex.get("Lat")] + "," + tempArr[keyIndex.get("Alt")] + "\n" + "</coordinates>\r\n" + 
								"  </Point>\r\n" + "</Placemark>");
						line = fileOpen.readLine();
					}
							
				}else {
				//Only One Spot
					tempArr = line.split(" , ");
					makeFile.write(" <Placemark>\r\n" +  "<name>\r\n" + tempArr[keyIndex.get("ID")] + "</name>" 
				+ "	<description><![CDATA[" + " SSID: " + tempArr[keyIndex.get("SSID 1")] 
						+ " MAC: "  + tempArr[keyIndex.get("MAC 1")] + 
						" Frequncy: " + tempArr[keyIndex.get("Frequncy 1")] + 
			    " Signal: " + tempArr[keyIndex.get("Signal 1")] + " ]]>");
				makeFile.write("\n </description>\r\n  <Point> \n<coordinates>" + 
			    tempArr[keyIndex.get("Lon")] + "," 
			    + tempArr[keyIndex.get("Lat")] + "," + 
			    tempArr[keyIndex.get("Alt")] + "\n" + "</coordinates>\r\n" + 
			    		"  </Point>\r\n" + "</Placemark>");
				}
				line = fileOpen.readLine();
			}
			
			makeFile.write(bottom);
			
		fileOpen.close();
		makeFile.close();
		
		}
		catch(IOException e){
			System.err.println(e.getMessage());
			System.err.println("Kml file was not created!");
		}
	}
	
	//Filter by Time ,ID 
	public void filter(String file, String Time_or_ID, String parameter) throws NumberFormatException, IOException {
		if(valid(file)) {
			try {
				String bottom = "</Document>\r\n" + "</kml>";
				//Read file
				FileReader readFile = new FileReader("Header.txt");
				BufferedReader fileOpen = new BufferedReader(readFile);
				BufferedWriter makeFile = new BufferedWriter(new FileWriter("filterBy" + Time_or_ID + ".kml"));

				//Title index
				HashMap<String, Integer>keyIndex = new HashMap<String,Integer>();

				String line = fileOpen.readLine();
				//Copy all file (Header)
				while(line != null) {
					makeFile.write(line);
					line = fileOpen.readLine();
				}
				makeFile.newLine();

				readFile = new FileReader(file);
				fileOpen = new BufferedReader(readFile);

				line = fileOpen.readLine();
				String[] tempArr = line.split(" , ");
				int counter = 0;

				//{MAC : 0 , SSID : 1 , AuthMode : 2 ....}, get title index
				for (String csvTtitle : tempArr) {
					keyIndex.put(csvTtitle.trim(), counter++);
				} 

				line = fileOpen.readLine();
				//Print all file
				while(line != null) {

					tempArr = line.split(" , ");
					if(Integer.parseInt( tempArr[keyIndex.get("WiFi networks")] ) > 1) {
						//Print more then one wifi spot
						for (int i = 1; i < Integer.parseInt( tempArr[keyIndex.get("WiFi networks")]+1 ); i++) {
							if(tempArr[keyIndex.get(Time_or_ID.trim())].trim().equalsIgnoreCase(parameter.trim()) ) {
								makeFile.write(" <Placemark>\r\n" +  "<name>\r\n" + tempArr[keyIndex.get("ID")] + "</name>" 
										+ "	<description><![CDATA[" + " SSID: " + tempArr[keyIndex.get("SSID " + i)] 
												+ " MAC: "  + tempArr[keyIndex.get("MAC " + i)] + 
												" Frequncy: " + tempArr[keyIndex.get("Frequncy " + i)] + 
												" Signal: " + tempArr[keyIndex.get("Signal " + i)] + " ]]>");
								makeFile.write("\n </description>\r\n  <Point> \n<coordinates>" + tempArr[keyIndex.get("Lon")] + "," 
										+ tempArr[keyIndex.get("Lat")] + "," + tempArr[keyIndex.get("Alt")] + "\n" + "</coordinates>\r\n" + 
										"  </Point>\r\n" + "</Placemark>");
								line = fileOpen.readLine();
							}
						}

					}else {
						//Only One Spot
						if(tempArr[keyIndex.get(Time_or_ID.trim())].trim().equalsIgnoreCase(parameter.trim()) ) {
							makeFile.write(" <Placemark>\r\n" +  "<name>\r\n" + tempArr[keyIndex.get("ID")] + "</name>" 
									+ "	<description><![CDATA[" + " SSID: " + tempArr[keyIndex.get("SSID 1")] 
											+ " MAC: "  + tempArr[keyIndex.get("MAC 1")] + 
											" Frequncy: " + tempArr[keyIndex.get("Frequncy 1")] + 
											" Signal: " + tempArr[keyIndex.get("Signal 1")] + " ]]>");
							makeFile.write("\n </description>\r\n  <Point> \n<coordinates>" + 
									tempArr[keyIndex.get("Lon")] + "," 
									+ tempArr[keyIndex.get("Lat")] + "," + 
									tempArr[keyIndex.get("Alt")] + "\n" + "</coordinates>\r\n" + 
									"  </Point>\r\n" + "</Placemark>");
						}
						line = fileOpen.readLine();
					}
					makeFile.newLine();
					}
				makeFile.write(bottom);
				fileOpen.close();
				makeFile.close();
			}catch (IOException e) {
				System.err.println(e.getMessage() + "\nProblem occurred while reading the file");
	
			}
		}else {
			unauthorizedFile(file);
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
			System.err.println(unauthorizedFile+" - " +"Unauthorized File, please change the file to authorized file (Authorize csv)!"); 
		}
}
