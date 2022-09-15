

/*
 * Copyright [2022] [Luis Enrique Ramos García].
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ramos.obsidian;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;


import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ramos.obsidian.models.Folder;
import com.ramos.obsidian.models.HttpURLFlureeDBConnection;
import com.ramos.obsidian.utilities.FolderReader;
import com.ramos.obsidian.utilities.NotesReader;
 
// Java sample program to iterate through directory tree
// Uses the Files.walk method added in Java 8
public class WalkDirectoryTree {
	
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    
    private static final String sparql_url = "http://127.0.0.1:8090/fdb/my/obsidian3/sparql";
    private static final String transaction_url = "http://127.0.0.1:8090/fdb/my/obsidian3/transact";
    private static final String content_type = "text/plain";
    private static final String http_method = "POST";
    
    
    public static void main(String[] args)  {
    	
    	 System.setProperty("log4j.configurationFile",  "bin/com/ramos/obsidian/resources/log4j2.xml");
    	 System.setProperty("logFilename", "obsidian_fluree_logs.log");
        Logger logger = LoggerFactory.getLogger( WalkDirectoryTree.class);
		Scanner input = new Scanner(System.in);//set up input reader
		
		//it is required to provide all information before begin!!!!

        // in windows use the form c:\\folder
        String rootFolder = "C:\\Users\\User\\Documents\\luis_obsidian";
        try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			logger.info("process started at "+timestamp);
			
			//asking to customer, and getting answer
			Console console = System.console();
			Boolean read_folder = false;
			while (!read_folder) {
				System.out.println("Doy you want to read obsidian folders?: (y/n)");
				String read_folder_answer = input.nextLine();    //getting an answer from user.
				if (read_folder_answer.equalsIgnoreCase("y")) {
					FolderReader.folderSearchWrite(rootFolder, logger, content_type, sparql_url, transaction_url,http_method);
					read_folder=true;
				} else if (read_folder_answer.equalsIgnoreCase("n")){
					read_folder=true;
				}
			}//while (read_folder) 
			//requesting reading folder
			Boolean read_notes = false;
			while (!read_notes) {
				System.out.println("Doy you want to read obsidian notes?: (y/n)");
				String read_note_answer = input.nextLine();    //getting an answer from user.
				if (read_note_answer.equalsIgnoreCase("y")) {
					NotesReader.notesSearchWrite(rootFolder, logger, content_type, sparql_url, transaction_url,http_method);
					read_notes=true;
				} else if (read_note_answer.equalsIgnoreCase("n")){
					read_notes=true;
				}
			}//while (read_folder) 
			
			
			//write finalization to log
			timestamp = new Timestamp(System.currentTimeMillis());
			logger.info("process finishing at "+timestamp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//write error to log
			logger.error("error reading the files "+e);
			e.printStackTrace();
		}
        //walkDirTreeWithSymLinks(rootFolder);
 
       // String searchFor = "md";
        //walkDirTreeAndSearch(rootFolder, searchFor);
 
    }
 
    // Prints all file/directory names in the entire directory tree!
    private static void walkDirTree(String rootFolder, Logger logger, String content_type, String query_url, String transaction_url, String http_method) throws Exception {
    	System.out.println("simply print files and folders");
   	    String separator = System.getProperty("file.separator");
        System.out.println(separator);

        Files.walk(Paths.get(rootFolder)).forEach(path -> {
        	
        	File directory = new File(path.toString());
        	
            BasicFileAttributes fileAttributes;
            
			try {
				fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
				
				if (directory.isDirectory()) {
	        		//System.out.println(directory + " is a folder directory");
	        		String folder_name = directory.toString().substring(directory.toString().lastIndexOf(separator) + 1);
//	                System.out.println(folder_name + " is a folder_name ******");
//	                System.out.println("created_on:     " + fileAttributes.creationTime());
//	                System.out.println("lastAccessTime:   " + fileAttributes.lastAccessTime());
//	                System.out.println("lastModifiedTime: " + fileAttributes.lastModifiedTime());
	                //search for folder by name and location
	                try {
	                	//modify separator
	                	String modified_directory = null;
	                	if (directory.toString().contains("\\")) {
							modified_directory = directory.toString().replace("\\", "/");
						}
	                	String http_body = String.format("\"SELECT ?folder WHERE { ?folder fd:Folder/folderName \\\"%s\\\"; fd:Folder/folderName \\\"%s\\\" . }\"", folder_name, modified_directory);
	                	//System.out.println("the query: "+http_body);
	                	String consulting_response = HttpURLFlureeDBConnection.
								sendOkHttpClientPost(content_type,query_url,http_method,http_body);
	                	System.out.println("the response: "+consulting_response);
	                	//reading response
	                	
				        if (consulting_response.equals("[]")) {
						     //create folder because it does not exist
				        	Folder a_folder;
				        	if (modified_directory.equals(null)) {
					        	a_folder = new Folder(folder_name,fileAttributes.creationTime(),directory.toString());
							} else {
					        	a_folder = new Folder(folder_name,fileAttributes.creationTime(),modified_directory);
							}
				        	System.out.println("folder json: "+a_folder.getFullJSON());
				        		 //request to create the tag
				        	try {
				        		String transaction_response = HttpURLFlureeDBConnection.
										sendOkHttpClientPost(content_type,transaction_url,http_method,a_folder.getFullJSON());
				        		//System.out.println("transaction_response: "+transaction_response);
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("error while creating the folder: "+directory.toString());
							}
								 
						} //consulting response
						
	                	
					} catch (Exception e) {
						// TODO: handle exception
						logger.error(e.toString());
					}
	                
				} else if ((FilenameUtils.getExtension(directory.toString())).equalsIgnoreCase("md")){
	        		System.out.println(directory.getName() + " is markdown file name");
//	        		System.out.println("file name: "+directory.getName());
//	        		System.out.println("file path: "+directory.getParent());
//	        		System.out.println("file extension: "+FilenameUtils.getExtension(directory.toString()));
//	                System.out.println("created_on:     " + fileAttributes.creationTime());
//	                System.out.println("lastAccessTime:   " + fileAttributes.lastAccessTime());
//	                System.out.println("lastModifiedTime: " + fileAttributes.lastModifiedTime());
				}//directory.isDirectory()
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("error while reading attributes of  "+directory);
			}

        	
        	
        	
//				        	String name = path.toString();
//				        	int lastIndexOf = name.lastIndexOf(".");
//				            if (lastIndexOf == -1) {
//				                System.out.println(name + " is a folder");
//				                //create folder in fluree
//				                String folder_name = name.substring(name.lastIndexOf(separator) + 1);
//				                System.out.println(folder_name + " is a folder_name");
//				                
//				            }
//				            else if (name.substring(lastIndexOf).endsWith(".md")){
//				            	System.out.println(name + " is a markdown file");
//				            	  File file = new File(name);
//				            	  
//				                //create note in fluree
//				            }
            
        });
    }
  
}
