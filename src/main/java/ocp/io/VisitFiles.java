package ocp.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class VisitFiles extends SimpleFileVisitor<Path>{
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
		if (matches(file, "**/*Files*"))
			System.out.println(file.toString());		
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
			throws IOException {
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc)
			throws IOException {
		System.out.println("dir!");
		return super.postVisitDirectory(dir, exc);
	}
	
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc)
			throws IOException {
		// TODO Auto-generated method stub
		return super.visitFileFailed(file, exc);
	}
	
	public static void main(String[] args) {
		Path startPath = Paths.get("src");
		try {
			Files.walkFileTree(startPath, new VisitFiles());
		} catch (IOException e) {
		}
	}
	
	private boolean matches (Path path, String globText) {
		PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + globText);
		if (pathMatcher.matches(path))
			return true;
		else 
			return false;
	}
}
