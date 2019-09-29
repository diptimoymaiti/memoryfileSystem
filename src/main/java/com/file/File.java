package com.file;

import java.util.HashMap;
import java.util.Map;

public class File {
	private String name;
    private FileType type;
    private Map<String, File> lists;
    private String path;
    private File parent;
    
    protected File() {
    	this.name = "/";
		this.path = "/";
		type = FileType.ROOT;
		lists = new HashMap<>();
	}
    
    protected File(String name, FileType type, File parent) {
    	this.name = name;
    	this.type = type;
    	this.path = FileType.ROOT.equals(parent.type)? parent.path + name  : parent.path + "/" + name;
    	
    	this.parent = parent;		
    	lists = new HashMap<>();
	}
	public FileType getType() {
		return type;
	}
	public void setType(FileType type) {
		this.type = type;
	}
	public Map<String, File> getLists() {
		return lists;
	}
	public void setLists(Map<String, File> lists) {
		this.lists = lists;
	}
	
	public File getParent() {
		return parent;
	}
	public void setParent(File parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
    
    
}
