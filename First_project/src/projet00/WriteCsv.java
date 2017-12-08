package projet00;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class WriteCsv.
 */
public class WriteCsv {
	
	/** The file table. */
	private List<List<Network>> _fileTable;
	
	/**
	 * Instantiates a new write csv.
	 *
	 * @author adiel ,adi and yuda
	 */
	public WriteCsv(){
		
		this._fileTable = null;
	}
	
	/**
	 * Instantiates a new write csv.
	 *
	 * @author adiel ,adi and yuda
	 * @param csvList the csv list
	 * @return csv file
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public WriteCsv(List<List<Network>> csvList) throws IndexOutOfBoundsException  {
		try {
			this._fileTable = csvList;
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
					if(!runList.isEmpty()) {
						if(runList.size() > 10) {
							makeFile.write(runList.get(0).getTime() + " , " + runList.get(0).getId() + " , "
									+runList.get(0).getLat() + " , " + runList.get(0).getLon() + " , "
									+ runList.get(0).getAlt() + " , " + 10);
						}else {
							makeFile.write(runList.get(0).getTime() + " , " + runList.get(0).getId() + " , "
									+runList.get(0).getLat() + " , " + runList.get(0).getLon() + " , "
									+ runList.get(0).getAlt() + " , " + runList.size());
						}
						for (Network network : runList) {
							if(count >=10) break;
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
		}catch(IndexOutOfBoundsException | IOException | NullPointerException e) {
			e.printStackTrace();
			System.out.println("Sorry, somethings went wrong! \nPlease check if your file is corrupted");
		}
	}
}
