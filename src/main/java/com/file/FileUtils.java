package com.file;

import java.util.stream.Stream;

public class FileUtils {
	
	public static String []  validateAndGetPath(String path, File root, File start) {
		String [] files = null;
		if(path == null ||  path.trim().length() ==0) {
			System.out.println("Path can't be empty");
			return null;
		}
		files = path.split("/");
		if(path.startsWith("/")) {
			boolean isvalid = Stream.of(files).allMatch(s -> s.length()>0);
			if(!isvalid) {
				System.out.println("filename can't be empty");
				return null;
			}
			start = root;
		}else {
			boolean isvalid = Stream.of(files).allMatch(s -> s.length()>0);
			if(!isvalid) {
				System.out.println("filename can't be empty");
				return null;
			}
		}
		
		return files;
		
	}

}
