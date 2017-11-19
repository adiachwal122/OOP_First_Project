package projet00;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WriteKml {

	public WriteKml() {

	}
	/**
	 * Learned from:
	 *  https://labs.micromata.de/projects/jak/kml-in-the-java-world.html
	 * Creat Kml file
	 * @param KmlPath
	 */
	//Check if valide
	public boolean valid(String path) throws IOException {
		if(fileType(path).equals("CSV")) {
			try {
				//Read file
				FileReader readFile = new FileReader(path);
				BufferedReader fileOpen = new BufferedReader(readFile);

				String recognize = fileOpen.readLine().split(",")[0].trim();

				if (recognize.equals("Time")) {
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
