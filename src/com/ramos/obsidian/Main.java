

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
import java.nio.file.InvalidPathException;
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
import com.ramos.obsidian.utilities.FolderNotesLinker;
import com.ramos.obsidian.utilities.FolderReader;
import com.ramos.obsidian.utilities.NotesLinker;
import com.ramos.obsidian.utilities.NotesReader;
 
// Java sample program to iterate through directory tree
// Uses the Files.walk method added in Java 8
public class Main {
	
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    
    private static String obsidianFolder = null;//C:\\Users\\User\\Documents\\luis_obsidian
    private static String sparql_url = null;//"http://127.0.0.1:8090/fdb/my/obsidian3/sparql";
    private static String transaction_url = null;//"http://127.0.0.1:8090/fdb/my/obsidian3/transact";
    private static String fluree_url = null;
    private static final String content_type = "text/plain";
    private static final String http_method = "POST";
    
    
    public static void main(String[] args)  {
    	
    	
    	 System.setProperty("log4j.configurationFile",  "bin/com/ramos/obsidian/resources/log4j2.xml");
    	 System.setProperty("logFilename", "obsidian_fluree_logs.log");
        Logger logger = LoggerFactory.getLogger( Main.class);
		Scanner input = new Scanner(System.in);//set up input reader
		
		//it is required to provide all information before begin!!!!
        // in windows use the form c:\\folder
        
        try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			logger.info("process started at "+timestamp);
			//requested basic information to proceed 
			
			System.out.println(""
					+ "*******************************************************************"
					+ "Welcome to Obsidian Fluree Parser! \n"
					+ "Some information will be requested and verified. \n"
					+ "Take in account that if some information is wrong the program \n"
					+ "will be inmediately terminated.\n"
					+ "*******************************************************************");
			Boolean obsidian_folder = false;
			while (!obsidian_folder) {
				System.out.println("Please, provide obsidian root folder location:");
				obsidianFolder = input.nextLine();    //getting an answer from user.
				try {
					File file = new File(obsidianFolder);
			        if (file.isDirectory()) {
			            obsidian_folder= true;
			        }
			        else {
			            logger.error("Path "+obsidianFolder+ " is invalid!. Program terminated");
			            System.exit(1);
			        }
			    } catch (InvalidPathException  | NullPointerException ex) {
			    	obsidian_folder= false;
		            logger.error("Path "+obsidianFolder+ "is invalid!.  Program terminated");
		            System.exit(1);
			    } 
			}//while (obsidian_folder) 
			
			//getting fluree server url
			Boolean fluree_server_up = false;
			while (!fluree_server_up) {
				System.out.println("Please, provide fluree server URL:");
				 fluree_url = input.nextLine();    //getting an answer from user.
				 String fluree_url_health = fluree_url+"/fdb/health";
				try {
					String consulting_response = HttpURLFlureeDBConnection.
							sendOkHttpClientPost(content_type,fluree_url_health,http_method,"");
					//System.out.println("fluree health: "+consulting_response);
			        if (consulting_response.contains("\"ready\":true")) {
			        	fluree_server_up= true;
			        }
			        else {
			            logger.error(fluree_url + "server is not available.  Program terminated");
			            System.exit(1);
			        }
			    } catch (InvalidPathException  | NullPointerException ex) {
			    	fluree_server_up= false;
		            logger.error(fluree_url + "server is not available.  Program terminated");
		            System.exit(1);
			    } 
			}//while fluree server
			
			//getting fluree db to use
			Boolean fluree_db_gotten = false;
			while (!fluree_db_gotten) {
				System.out.println("Please, provide fluree DB name (please follow pattern:some/db)");
				 String fluree_db_name = input.nextLine();    //getting an answer from user.
				try {
					Pattern h1 = Pattern.compile("(?m)/(?!#)(.*)");
					Matcher m = h1.matcher(fluree_db_name);
					
					if (m.find()) {
						fluree_db_gotten = true;
						sparql_url = fluree_url+"/fdb/"+fluree_db_name+"/sparql";
						//System.out.println("sparql_url: "+sparql_url);
						transaction_url = fluree_url+"/fdb/"+fluree_db_name+"/transact";
						//System.out.println("transaction_url: "+transaction_url);
					} else {
						fluree_db_gotten= false;
			            logger.error(fluree_db_name + " improperly formatted.  Program terminated");
			            System.exit(1);
					}
			        
			    } catch (Exception e) {
			    	fluree_db_gotten= false;
		            logger.error(fluree_db_name + "improperly formatted.  Program terminated");
		            System.exit(1);
			    } 
			}//while fluree server
			//asking to customer, and getting answer
			//Console console = System.console();
			Boolean read_folder = false;
			while (!read_folder) {
				System.out.println("Doy you want to create obsidian folders in fluree?: (y/n)");
				String read_folder_answer = input.nextLine();    //getting an answer from user.
				if (read_folder_answer.equalsIgnoreCase("y")) {
					FolderReader.folderSearchWrite(obsidianFolder, logger, content_type, sparql_url, transaction_url,http_method);
					read_folder=true;
				} else if (read_folder_answer.equalsIgnoreCase("n")){
					read_folder=true;
				}
			}//while (read_folder) 
			//requesting reading folder
			Boolean read_notes = false;
			while (!read_notes) {
				System.out.println("Doy you want to create obsidian notes in fluree?: (y/n)");
				String read_note_answer = input.nextLine();    //getting an answer from user.
				if (read_note_answer.equalsIgnoreCase("y")) {
					NotesReader.notesSearchWrite(obsidianFolder, logger, content_type, sparql_url, transaction_url,http_method);
					read_notes=true;
				} else if (read_note_answer.equalsIgnoreCase("n")){
					read_notes=true;
				}
			}//while (read_folder) 
			
			Boolean link_notes = false;
			while (!link_notes) {
				System.out.println("Doy you want to link obsidian notes?: (y/n)");
				String link_notes_answer = input.nextLine();    //getting an answer from user.
				if (link_notes_answer.equalsIgnoreCase("y")) {
					NotesLinker.notesSearchLink(obsidianFolder, logger, content_type, sparql_url, transaction_url,http_method);
					link_notes=true;
				} else if (link_notes_answer.equalsIgnoreCase("n")){
					link_notes=true;
				}
			}//while (read_folder) 
			
			/*
			 * search for notes linked to folder
			 */
			Boolean folder_link_notes = false;
			while (!folder_link_notes) {
				System.out.println("Doy you want to link folder to obsidian notes?: (y/n)");
				String link_notes_answer = input.nextLine();    //getting an answer from user.
				if (link_notes_answer.equalsIgnoreCase("y")) {
					NotesLinker.notesSearchLink(obsidianFolder, logger, content_type, sparql_url, transaction_url,http_method);
					FolderNotesLinker.folderSearchNotesLink(logger, content_type, sparql_url, transaction_url,http_method);
					folder_link_notes=true;
				} else if (link_notes_answer.equalsIgnoreCase("n")){
					folder_link_notes=true;
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
        //walkDirTreeWithSymLinks(obsidianFolder);
 
       // String searchFor = "md";
        //walkDirTreeAndSearch(obsidianFolder, searchFor);
 
    }
 
    // Prints all file/directory names in the entire directory tree!
//    private static void walkDirTree(String obsidianFolder, Logger logger, String content_type, String query_url, String transaction_url, String http_method) throws Exception {
//    	System.out.println("simply print files and folders");
//   	    String separator = System.getProperty("file.separator");
//        System.out.println(separator);
//
//        Files.walk(Paths.get(obsidianFolder)).forEach(path -> {
//        	
//        	File directory = new File(path.toString());
//        	
//            BasicFileAttributes fileAttributes;
//            
//			try {
//				fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
//				
//				if (directory.isDirectory()) {
//	        		//System.out.println(directory + " is a folder directory");
//	        		String folder_name = directory.toString().substring(directory.toString().lastIndexOf(separator) + 1);
////	                System.out.println(folder_name + " is a folder_name ******");
////	                System.out.println("created_on:     " + fileAttributes.creationTime());
////	                System.out.println("lastAccessTime:   " + fileAttributes.lastAccessTime());
////	                System.out.println("lastModifiedTime: " + fileAttributes.lastModifiedTime());
//	                //search for folder by name and location
//	                try {
//	                	//modify separator
//	                	String modified_directory = null;
//	                	if (directory.toString().contains("\\")) {
//							modified_directory = directory.toString().replace("\\", "/");
//						}
//	                	String http_body = String.format("\"SELECT ?folder WHERE { ?folder fd:Folder/folderName \\\"%s\\\"; fd:Folder/folderName \\\"%s\\\" . }\"", folder_name, modified_directory);
//	                	//System.out.println("the query: "+http_body);
//	                	String consulting_response = HttpURLFlureeDBConnection.
//								sendOkHttpClientPost(content_type,query_url,http_method,http_body);
//	                	System.out.println("the response: "+consulting_response);
//	                	//reading response
//	                	
//				        if (consulting_response.equals("[]")) {
//						     //create folder because it does not exist
//				        	Folder a_folder;
//				        	if (modified_directory.equals(null)) {
//					        	a_folder = new Folder(folder_name,fileAttributes.creationTime(),directory.toString());
//							} else {
//					        	a_folder = new Folder(folder_name,fileAttributes.creationTime(),modified_directory);
//							}
//				        	System.out.println("folder json: "+a_folder.getFullJSON());
//				        		 //request to create the tag
//				        	try {
//				        		String transaction_response = HttpURLFlureeDBConnection.
//										sendOkHttpClientPost(content_type,transaction_url,http_method,a_folder.getFullJSON());
//				        		//System.out.println("transaction_response: "+transaction_response);
//							} catch (Exception e) {
//								// TODO: handle exception
//								logger.error("error while creating the folder: "+directory.toString());
//							}
//								 
//						} //consulting response
//						
//	                	
//					} catch (Exception e) {
//						// TODO: handle exception
//						logger.error(e.toString());
//					}
//	                
//				} else if ((FilenameUtils.getExtension(directory.toString())).equalsIgnoreCase("md")){
//	        		System.out.println(directory.getName() + " is markdown file name");
////	        		System.out.println("file name: "+directory.getName());
////	        		System.out.println("file path: "+directory.getParent());
////	        		System.out.println("file extension: "+FilenameUtils.getExtension(directory.toString()));
////	                System.out.println("created_on:     " + fileAttributes.creationTime());
////	                System.out.println("lastAccessTime:   " + fileAttributes.lastAccessTime());
////	                System.out.println("lastModifiedTime: " + fileAttributes.lastModifiedTime());
//				}//directory.isDirectory()
//				
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				logger.info("error while reading attributes of  "+directory);
//			}
//
//        	
//        	
//        	
////				        	String name = path.toString();
////				        	int lastIndexOf = name.lastIndexOf(".");
////				            if (lastIndexOf == -1) {
////				                System.out.println(name + " is a folder");
////				                //create folder in fluree
////				                String folder_name = name.substring(name.lastIndexOf(separator) + 1);
////				                System.out.println(folder_name + " is a folder_name");
////				                
////				            }
////				            else if (name.substring(lastIndexOf).endsWith(".md")){
////				            	System.out.println(name + " is a markdown file");
////				            	  File file = new File(name);
////				            	  
////				                //create note in fluree
////				            }
//            
//        }
    //);
   // }
  
}
