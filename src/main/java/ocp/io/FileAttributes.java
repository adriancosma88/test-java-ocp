package ocp.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileAttributes {
	public static void main(String[] args) {
		Path testFil1 = Paths.get("testFile1.txt");
		try {
			Files.createFile(testFil1);
		} catch (IOException e) {			
		}
		
		System.out.println(Files.isReadable(testFil1));
		System.out.println(Files.isExecutable(testFil1));
		System.out.println(Files.isWritable(testFil1));
		try {
			System.out.println(Files.getLastModifiedTime(testFil1));
		} catch (IOException e) {
		}
		
		/**
		 * File Attributes
		 */
		try {
			BasicFileAttributes basicAttributes = Files.readAttributes(testFil1, BasicFileAttributes.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
