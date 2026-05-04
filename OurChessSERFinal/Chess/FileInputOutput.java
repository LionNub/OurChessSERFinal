//Griffin Labitt 

import java.util.Scanner; 
import java.io.*; 
import java.util.ArrayList;

public class FileInputOutput{ 
	
	public static void writeFile(File file, ArrayList<String> startingPositions, ArrayList<String> endingPositions) {
		
		//creates new printWriter instance
		PrintWriter output;
		
		try {
			output = new PrintWriter(file);
			
			
			for (int i = 0; i < startingPositions.size(); i++) {//PrintWriter has a println class for pushing text into the file
				output.println(startingPositions.get(i));
				output.println(endingPositions.get(i));
			}
			
			output.close();
			System.out.println("Success! Your file has been created.\n");
			
			//Use this line if you cannot find your file
			//System.out.println(userFile.getAbsolutePath());
			
		} catch (FileNotFoundException e) {
			//Auto-generated catch block
			//e.printStackTrace();
			
			System.out.println(e); 
			System.exit(1);
		}	
	}
 
	public static void readFile(File file, ArrayList<String> startingPositions, ArrayList<String> endingPositions) {
		Scanner fileInput;
		System.out.println("Loading saved game...\n");

		try {
			fileInput = new Scanner(file);
			while (fileInput.hasNextLine()) {
				startingPositions.add(fileInput.nextLine()); // odd lines = start
				if (fileInput.hasNextLine()) {
					endingPositions.add(fileInput.nextLine()); // even lines = end
				}
			}
			fileInput.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
			System.exit(1);
		}
	}


}
