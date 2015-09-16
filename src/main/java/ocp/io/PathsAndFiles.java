package ocp.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

@SuppressWarnings("unused")
public class PathsAndFiles {
	public static void main(String[] args) {
		Path p1 = Paths.get("pathFile1.txt");
		
		//When starting with the root it is considered an absolute path,
		//otherwise it is considered a relative path.
		Path p2 = Paths.get("c:\\my\\fictive\\path\\folder"); //on Windows
		File file1 = p1.toFile();
		Path convertedPath = file1.toPath();
		
		Path longerPath = FileSystems.getDefault().getPath("c:", "temp");
		
		/**
		 * Creating files and dirs with paths
		 */
		System.out.println("---Creating files and dirs with paths");
		Path path1 = Paths.get("myPath1.txt");
		System.out.println("Files.exists(path1): " + Files.exists(path1));
		System.out.println("Files.notExists(path1): " + Files.notExists(path1));
		try {
			path1 = Files.createFile(path1);
		} catch (IOException e) {
			//If the file already exists, it will throw an IOException
			System.out.println("File Not Created");
		}
		System.out.println(Files.exists(path1));
		try (PrintWriter pw = new PrintWriter(path1.toFile())) {
			pw.println("Test line 1");
			pw.println("Test line 2");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Path pathDir1 = Paths.get("myPathDir1");
		try {
			Files.createDirectory(pathDir1);
		} catch (IOException e) {
		}
		
		Path pathDir2 = Paths.get("myDirUp/mySubDir1/mySubDir2");
		try {
			Files.createDirectories(pathDir2);
		} catch (IOException e) {
		}
		
		/**
		 * Copy File.
		 */
		Path path2 = Paths.get(pathDir1.getFileName() + File.separator + "myPath2.txt");
		System.out.println(path2.getParent());		
		try {
			Files.copy(path1, path2);
		} catch (IOException e) {
			//If the file already exists it will throw an exception.
		}
		
		//Copy with replace existent.
		try {
			Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
		}
		
		/**
		 * The move method is also used for renaming the file, similar to linux.
		 */
		try {
			path2 = Files.move(path1, path2);
		} catch (IOException e) {			
		}
		
		/**
		 * Delete File.
		 */
		try {
			Files.delete(path2);
		} catch (IOException e) {
		}
		
		/**
		 * Delete File if exists.
		 */
		try {
			Files.deleteIfExists(path2);
		} catch (IOException e) {
		}
		
		/**
		 * Path informations.		
		 */
		Path pathForInfo1 = Paths.get("pathInfo1.txt");
		
		System.out.println("---FileInfo");
		System.out.println(pathForInfo1.getRoot());
		System.out.println(pathForInfo1.getParent());
		System.out.println(pathForInfo1.getNameCount());
		System.out.println(pathForInfo1.getName(0));
		System.out.println(pathForInfo1.toString());
		System.out.println(pathForInfo1.subpath(0, 1));
		
		Path pathForInfo2 = Paths.get("c:\\bigFolder\\upperFolder\\parentFolder\\pathInfo12txt");
		
		System.out.println("---FileInfo");
		System.out.println(pathForInfo2.getRoot());
		System.out.println(pathForInfo2.getParent());
		System.out.println(pathForInfo2.getNameCount());
		System.out.println(pathForInfo2.getName(0));
		System.out.println(pathForInfo2.subpath(0, 2));
		System.out.println(pathForInfo2.toString());
		
		//normalize() just manipulates the strings in the path. It does not
		//check the system to see if the data is correct.
		System.out.println("--Normalize path");
		Path pathForInfo3 = Paths.get("c:\\bigFolder\\upperFolder\\..\\..\\parentFolder\\pathInfo12txt");
		System.out.println("Original path: " + pathForInfo3);
		System.out.println("Normalized path: " + pathForInfo3.normalize());
		
		System.out.println(Paths.get("a/./b/./c").normalize());
		
		/**
		 * Resolve() and relativize().
		 */
		Path absolute = Paths.get("/home/java");
		Path relative = Paths.get("dir");
		Path file = Paths.get("myFile.txt");
		System.out.println(absolute.resolve(relative));
		System.out.println(absolute);
		System.out.println(absolute.resolve("fictiveString"));
		System.out.println(absolute.resolve(relative).resolve(file));
		
		System.out.println();
		Path resolvedPath = absolute.resolve(relative).resolve(file);
		System.out.println(resolvedPath);
		System.out.println(absolute.relativize(resolvedPath));
		
		/**
		 * Real path displays the real path as displayed in the system.
		 * If the file does not exist on the file system it will throw
		 * an IOException, which means we have to catch this exception.
		 */
		Path real1 = Paths.get("realPath1.txt");
		try {
			System.out.println(real1.toRealPath());
		} catch (IOException e) {
			System.out.println("error when showing real path");
		}
		
		try {
			Files.createFile(real1);
			System.out.println(real1.toRealPath());
		} catch (IOException e) {
			System.out.println("error when showing real path");
		}
	}
}
