

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


package com.ramos.obsidian.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import com.ramos.obsidian.models.Folder;
import com.ramos.obsidian.models.HttpURLFlureeDBConnection;

public abstract class NotesReader {
	
	 // Prints all file/directory names in the entire directory tree!
    public static void notesSearchWrite(String rootFolder, Logger logger, String content_type, String query_url, String transaction_url, String http_method) throws Exception {
    	//System.out.println("simply print files and folders");
   	    String separator = System.getProperty("file.separator");
        //System.out.println(separator);
        Files.walk(Paths.get(rootFolder)).forEach(path -> {
        	File directory = new File(path.toString());
            BasicFileAttributes fileAttributes;
			try {
				fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
				if ((FilenameUtils.getExtension(directory.toString())).equalsIgnoreCase("md")){
	        		System.out.println(directory.getName() + " is markdown file name");
	        		System.out.println("file name: "+directory.getName());
	        		System.out.println("file path: "+directory.getParent());
	        		System.out.println("file extension: "+FilenameUtils.getExtension(directory.toString()));
	                System.out.println("created_on:     " + fileAttributes.creationTime());
	                System.out.println("lastAccessTime:   " + fileAttributes.lastAccessTime());
	                System.out.println("lastModifiedTime: " + fileAttributes.lastModifiedTime());
	                //proceeding to work with the note
	                //we check if the note exist in fluree, before proceed to create it
	                try {
	                	//modify separator
//	                	String modified_directory = null;
//	                	if (directory.toString().contains("\\")) {
//							modified_directory = directory.toString().replace("\\", "/");
//						}
	                	String http_body = String.format("\"SELECT ?note WHERE { ?note fd:Note/note_name \\\"%s\\\". }\"", directory.getName().replaceAll("\\s+","_"));
	                	System.out.println("the query: "+http_body);
//	                	String consulting_response = HttpURLFlureeDBConnection.
//								sendOkHttpClientPost(content_type,query_url,http_method,http_body);
	                	//System.out.println("the response: "+consulting_response);
	                	//reading response
	                	
//				        if (consulting_response.equals("[]")) {
//						     //create folder because it does not exist
//				        	Folder a_folder;
//				        	if (modified_directory.equals(null)) {
//					        	a_folder = new Folder(folder_name,fileAttributes.creationTime(),directory.toString());
//							} else {
//					        	a_folder = new Folder(folder_name,fileAttributes.creationTime(),modified_directory);
//							}
//				        	//System.out.println("folder json: "+a_folder.getFullJSON());
//				        		 //request to create the tag
//				        	try {
//				        		String transaction_response = HttpURLFlureeDBConnection.
//										sendOkHttpClientPost(content_type,transaction_url,http_method,a_folder.getFullJSON());
//				        		//System.out.println("transaction_response: "+transaction_response);
//							} catch (Exception e) {
//								// TODO: handle exception
//								logger.error("error while creating the folder: "+directory.toString());
//							}
//						} //consulting response
					} catch (Exception e) {
						// TODO: handle exception
						logger.error(e.toString());
					}
	                
				}//directory.isDirectory()
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("error while reading attributes of  "+directory);
			}
        });
    }


}//end folderreader

