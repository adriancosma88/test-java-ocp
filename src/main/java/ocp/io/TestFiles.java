package ocp.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class TestFiles {
	public static void main (String ... args) {
		//Reader and Writer
		System.out.println("---Setting name...");
		File file = new File("myTestFile.txt");
		
		System.out.println("---Does file exist?");
		if (file.exists()) {
			System.out.println("File exists");
		} else {
			//A new file will be created in the project root folder.
			//It always creates a new file, thus I check if exists first.
			System.out.println("---New file...");
			try {
				file.createNewFile();
				System.out.println("Created file.");				
				System.out.println("---Does file exist after creation?");
				System.out.println(file.exists());
			} catch (IOException e1) {
				System.out.println("File not created.");
			}
		}			
		
		/**
		 * FileWriter
		 */
		
		final boolean APPEND_TO_FILE = true;
		try {
			/**
			 * If append to file is true, then it will append to the existing file,
			 * otherwise no. Write and append generally have the same behavior.
			 * 
			 * However, append accepts CharSequence, unlike write which only accepts
			 * string or char[].
			 */
			FileWriter fileWriter = new FileWriter(file);
			
			fileWriter.write("Test line 1. \nTest line 2. \nTest line 3.");
			
			fileWriter.append("\nTest line 4. \nTest line 5. \nTest line 6.");
			
			/**
			 * Writers and streams usually buffer some of your output data in memory and try
			 * to write it in bigger blocks at a time. flushing will cause an immediate write
			 * to disk from the buffer, so if the program crashes that data won't be lost.
			 */			
			fileWriter.flush();
			fileWriter.close();
			
		} catch (IOException e) {
			System.out.println("Could not get File.");
		}
		
		//We can also use Try with Resources
		try ( FileWriter fileWriter = new FileWriter(file, APPEND_TO_FILE) ) {
			
			fileWriter.write("Test line 1. \nTest line 2. \nTest line 3.");
			
			fileWriter.append("\nTest line 4. \nTest line 5. \nTest line 6.");
			
		} catch (IOException e) {
			System.out.println("Could not get File.");
		}
		
		/**
		 * BufferedWriter
		 * 
		 * - it has an extra newLine() method.
		 */
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, !APPEND_TO_FILE))) {
			bufferedWriter.write("Test line 1. \nTest line 2.");
			bufferedWriter.newLine();
			bufferedWriter.write("Test line 3 after newLine().");
			bufferedWriter.newLine();
			bufferedWriter.append("Test line 4 - CHar sequence");
		} catch (IOException e) {
			/**
			 * Always need to catch an IOException when using buffered and file writers
			 * or readers.
			 */
			System.out.println("Error BufferedWriter");
		}
		
		try (FileWriter fileWriter = new FileWriter(file, APPEND_TO_FILE) ; /* This semicolon is NOT optional.*/
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
			 ; /* This semicolon is optional.*/) {
			bufferedWriter.write("Test line 1. \nTest line 2.");
			bufferedWriter.newLine();
			bufferedWriter.write("Test line 3 after newLine().");
		} catch (IOException e) {
		}
		
		/**
		 * PrintWriter
		 */
		try (PrintWriter printWriter = new PrintWriter(file)) {
			printWriter.write("XTest line 1. \nTest line 2.");
			//printWriter.newLine(); //not legal
			printWriter.println();
			printWriter.write("Test line 3.");
			//First it prints, then calls the println() method, which moved to another line.
			printWriter.println("Test line 4 - with println().");
			printWriter.print("Test line 5 - print after println().");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, APPEND_TO_FILE));
				PrintWriter pw = new PrintWriter(bufferedWriter)) {
			pw.write("Test line 1. \nTest line 2.");
			pw.append("Line append");
		} catch (IOException e) {			
		}
		
		/**
		 * FileReader
		 */
		try (FileReader fileReader = new FileReader(file)) {
			System.out.println("---FileReader");
			System.out.println(fileReader.read()); //reads a single char
			//after the first char is read, it will not be read again with the other chars
			char [] readChars = new char[1000];
			fileReader.read(readChars);
			System.out.println(readChars);
			fileReader.reset();
		} catch (IOException e) {
		}
		
		/**
		 * BufferedReader
		 */
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			System.out.println("---BufferedReader");
			System.out.println(bufferedReader.readLine());
			System.out.println(bufferedReader.readLine());
		} catch (IOException e) {			
		}
		
		System.out.println();
		System.out.println("File manipulation");
		System.out.println();
		
		/**
		 * File Manipulation
		 */
		
		File myDir = new File("myDir");
		myDir.mkdir(); //does not throw IOException
		
		System.out.println("myDir is directory? " + myDir.isDirectory());
		System.out.println("myDir is file? " + myDir.isFile());
		
		File myFile = new File(myDir, "myFile.txt");
		try {
			myFile.createNewFile();
		} catch (IOException e) {
			//Be careful! It throws IOException
		}
		
		System.out.println("myFile is directory? " + myFile.isDirectory());
		System.out.println("myFile is file? " + myFile.isFile());
		
		File myFile2 = new File(myDir, "myFile2.txt");
		try {
			myFile2.createNewFile();
		} catch (IOException e) {
			//Be careful! It throws IOException
		}
		
		System.out.println("delete myFile.txt");
		System.out.println("Exists before delete? " + myFile.exists());
		myFile.delete();
		System.out.println("Exists after delete? " + myFile.exists());
		
		File newFileName = new File(myDir, "myNewFileNameXXX.txt");
		if ( myFile2.renameTo(newFileName)) {
			System.out.println("rename successful");
		};
		
		
		System.out.println();
		System.out.println("---Console---");
		
		//Returns the system's console, if any. This object might be null.
		/**
		 * Using the console, might throw NullPointerException, because not 
		 * all system can return a Console.
		 */
		Console console = System.console();
		if (console != null) {			
			String passwordString = console.readLine();
			System.out.println("You wrote: " + passwordString);
			System.out.println("Enter password:");
			char[] passwordChars = new char[30];
			passwordChars = console.readPassword();
			System.out.println("password chars: " + passwordChars.toString());
		} else {
			System.out.println("console is null");
		}
	}
}
