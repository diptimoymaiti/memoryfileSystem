package com.file;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/**
 * 
 * @author Diptimoy
 *
 */
public class InmemoryFileSystem implements FileSystem {
	
	private File root;
	private File pwd;
	
	public InmemoryFileSystem() {
		root = new File();
		pwd = root;
	}

	@Override
	public Iterable<String> ls() {
		return pwd.getLists().keySet();
	}

	@Override
	public boolean mkdir(String path) {
		File start = pwd;
		String [] files = FileUtils.validateAndGetPath(path, root, start);
		if(files == null) {
			return false;
		}
		boolean isNew = false;
		for(String file : files) {
			File dir = start.getLists().get(file);
			if(dir == null) {
				isNew = true;
				dir = new File(file, FileType.DIR, start);
				start.getLists().put(file, dir);
			}else if(FileType.FILE.equals(dir.getType())){
				System.out.println("A file " + dir.getName() + " already exist with same name");
				return false;
			}
			start = dir;
		}
		
		return isNew;
	}

	@Override
	public String touch(String path) {
		File start = pwd;
		String [] files = FileUtils.validateAndGetPath(path, root, start);
		if(files == null) {
			return null;
		}		
		for(int i = 0; i<files.length-1; i++) {
			File dir = start.getLists().get(files[i]);
			if(dir == null) {
				System.out.println("Invalid path");
				return null;
			}
			start = dir;
		}
		String fileName = files[files.length-1];
		if(start.getLists().get(fileName) !=null) {
			System.out.println("File already exists");
			return null;
		}
		File file = new File(fileName, FileType.FILE, start);
		//if(file.getParent().getType())
		start.getLists().put(fileName, file);
		return file.getPath();
		
	}

	@Override
	public String cd(String path) {
		File start = pwd;
		String [] files = FileUtils.validateAndGetPath(path, root, start);
		for(String file : files) {
			if(file.equals("..") && start == root) {
				System.out.println("Invalid path. Already in root");
				return  null;
			}else if(file.equals("..")) {
				start = start.getParent();
				continue;
			}
			File dir = start.getLists().get(file);
			if(dir == null) {
				System.out.println("Invalid path");
				return null;
			}
			start = dir;
		}
		pwd = start;
		
		return pwd.getPath();
	}

	@Override
	public String pwd() {
		return pwd.getPath();
	}

	@Override
	public boolean rm(String path, boolean recursive) {
		File start = pwd;
		String [] files = FileUtils.validateAndGetPath(path, root, start);
		if(files == null) {
			return false;
		}	
		for(int i = 0; i<files.length-0; i++) {
			File dir = start.getLists().get(files[i]);
			if(dir == null) {
				System.out.println("Invalid path");
				return false;
			}
			start = dir;
		}
		File parent = start.getParent();
		if(start.getType().equals(FileType.FILE)) {
			parent.getLists().remove(start.getName());
			start = null;
			return true;
		}
		int count =0;
		if(recursive) {
			parent.getLists().remove(start.getName());
			start = null;
			return true;
		}else {
			Queue<File> q = new LinkedList<>();
			q.add(start);
			while(q.size()>0) {
				File file = q.poll();
				Map<String, File>  lists = file.getLists();
				List<String> marked = new ArrayList<>();
				for(File f:  lists.values()) {
					if(f.getLists().isEmpty()) {
						count++;
						marked.add(f.getName());
					}else {
						q.offer(f);
					}
					
				}
				for(String m : marked) {
					lists.remove(m);
				}
			}
			if(count >0) {
				parent.getLists().remove(start.getName());
				start = null;
				return true;
			}
		}
		
		return  false;
	}

}
