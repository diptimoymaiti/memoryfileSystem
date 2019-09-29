package com.file;

public interface FileSystem {
	Iterable<String> ls();
	boolean mkdir(String path) ;
	String touch(String path);
	String cd(String path);
	String pwd();
	boolean rm(String path, boolean recursive);

}
