package com.fileSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.file.FileSystem;
import com.file.FileSystemFactory;

public class FileSystemTest {
	FileSystem fileSystem;
	
	
	@Before
	public void setUp() {
		fileSystem = FileSystemFactory.getFileSystem(FileSystemFactory.IN_MEMORY);
	}

	@Test
	public void testMkdir() {
		boolean status = fileSystem.mkdir("a/b/c/");
		assertTrue(status);
		status = fileSystem.mkdir("a/b/c/");
		assertFalse(status);
		status = fileSystem.mkdir("b/b/c/");
		Iterable<String>  ls= fileSystem.ls();
		ls.forEach(System.out::println);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testCd() {
		boolean status = fileSystem.mkdir("c/b/c/");
		assertTrue(status);
		String path =  fileSystem.cd("c/b");
		assertEquals("/c/b", path);
		
	}
	
	@Test
	public void testPwd() {
		boolean status = fileSystem.mkdir("d/b/c/");
		assertTrue(status);
		String path =  fileSystem.cd("d/b");
		String pwd = fileSystem.pwd();
		assertEquals("/d/b", pwd);
		
	}
	
	@Test
	public void testRm() {
		boolean status = fileSystem.mkdir("d/b/c/");
		assertTrue(status);
		String path =  fileSystem.cd("d/b");
		boolean del = fileSystem.rm("c", true);
		assertTrue(del);;
		
	}

}
