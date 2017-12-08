# OOP_First_Project
Our project is able to take csv file and export to kml file according to different filter types.
Our project works with Wiggle Wifi export file only.
Our project is devided to different class and each class has JunitTest class which verifies the currectness of every function.



# Function:
   * **Network** - the Network class is reciving the names of the parameters of the csv file and orginazing them as we were asked.

   * **ReadCsv** - the ReadCsv class uses List of List of Network (by the class above) to read from csv file that we give to it and orginaize the parameters as we were asked. The function "ReadCsv" uses Strings in order to write the file properly.

   * **WriteCsv** - the WriteCsv class uses the List of List of Network as in ReadCsv and BufferedWriter to create a CSV file/

   * **csvFilter** - the csvFilter is divided to types of filters for each parameter:
   ```
1. filter By MAC or SSID - the fenction gets String MAC or SSID ans String parameter and filter the file, 
the functuon returns csv file after the filter.
2.filter By ID - the fenction gets String ID ans String parameter and filter the file, 
the functuon returns csv file after the filter.
3. filter By Time - the fenction gets String start and String end and filter the file from the time start 
to the time end, the functuon returns csv file after the filter.            
4.The function "All" - the fenction uses FileReader BufferedReader to create the file completley.
```
   * **WriteKml** - the WriteKml class gets csv file after filter and uses "JAK" libraries in order to create KML file with time stamp. 

##How to use it - Example
```
public static void main(String[] args) throws IOException {
		/*ReadCsv gets csv file from WiggleWif only (path to file or folder)*/
		ReadCsv file = new ReadCsv("Test");
		/*WriteCsv gets List<List<Network>> ,which provided by ReadCsv file.*/
		WriteCsv write = new WriteCsv(file.get_fileTable());
		/*Build csvFilter and then use one of the filters, but it's possible to use it directly*/
		csvFilter filter = new csvFilter();
		filter.All("final_csv.csv");
		/*The class gets List<String []> ,HashMap<String, Integer> which provided by Filter*/
		WriteKml kml = new WriteKml(filter.getFile(),filter.getKeyIndex());
		/*Kml crated (with time stamp)*/

	}
  ```
