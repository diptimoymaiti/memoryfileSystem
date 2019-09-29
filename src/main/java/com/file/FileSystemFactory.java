package com.file;

public class FileSystemFactory {
	public static final String IN_MEMORY ="IN_MEMORY";
	
	public static FileSystem getFileSystem(String type) {
		if(IN_MEMORY.equalsIgnoreCase(type)) {
			return new InmemoryFileSystem();
		}
		else {
			return null;
		}
	}

}
