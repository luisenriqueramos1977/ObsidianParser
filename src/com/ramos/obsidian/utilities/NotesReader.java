

/*
 * Copyright [2022] [Luis Enrique Ramos Garc?a].
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
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import com.ramos.obsidian.models.Folder;
import com.ramos.obsidian.models.HttpURLFlureeDBConnection;
import com.ramos.obsidian.models.Note;

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
					UserPrincipal owner = Files.getOwner(path, LinkOption.NOFOLLOW_LINKS);
	                //proceeding to work with the note
	                //we check if the note exist in fluree, before proceed to create it
	                try {
	                	//modify separator
	                	String http_body = String.format("\"SELECT ?note WHERE { ?note fd:Note/name \\\"%s\\\". }\"", directory.getName().replaceAll("\\s+","_"));
	                	//System.out.println("the query: "+http_body);
	                	String consulting_response = HttpURLFlureeDBConnection.
								sendOkHttpClientPost(content_type,query_url,http_method,http_body);
	                	//System.out.println("the response: "+consulting_response);
	                	//reading response
	                	
				        if (consulting_response.equals("[]")) {
						    try {
						    	 //create folder because it does not exist
					        	//create note
					        	List<String> lines = Files.readAllLines(Path.of(path.toUri()));
					        	//System.out.println("lines of note: "+lines);
					        	String myString =null;
					        	try {
						        	myString = String.join("\n", lines).replace("\"", "'");
								} catch (Exception e) {
									// TODO: handle exception
									System.out.println("second try error: "+e);
						        	try {
							        	myString = String.join("\n", lines);
									} catch (Exception e2) {
										// TODO: handle exception
										System.out.println("third try error: "+e);
							        	myString = lines.toString();
									}
								}//end try 
					    		
//					        	System.out.println("my string: "+myString);
//					        	System.out.println("end my string: ");
//					        	System.out.println("checking inputs *****");
//					        	System.out.println("directory.getName().toString(): "+directory.getName().toString());
//					        	System.out.println("fileAttributes.creationTime(): "+fileAttributes.creationTime());
//					        	System.out.println("owner.getName().toString(): "+owner.getName().toString());
//					        	System.out.println("directory.getParent().toString().replace(\"\\\\\", \"/\"): "+directory.getParent().toString().replace("\\", "/"));

					        	Note my_Note = new Note(directory.getName().toString(), myString,fileAttributes.creationTime(), owner.getName().toString(), directory.getParent().toString().replace("\\", "/"));
					        	
					        	//System.out.println("object created adding additional elements **************");
					        	
					        	try {
						        	my_Note.generateAttention(logger);
								} catch (Exception e2) {
									// TODO: handle exception
									logger.error("error while generating  attention of "+directory.getName().toString());
								}
					        	try {
						    		my_Note.generateEmphasys(logger);
								} catch (Exception e2) {
									// TODO: handle exception
									logger.error("error while generating  emphasys of "+directory.getName().toString());
								}
					        	try {
						        	my_Note.generateHeader1(logger);
								} catch (Exception e) {
									// TODO: handle exception
									logger.error("error while generating  heading1 of "+directory.getName().toString());
								}
					        	try {
						    		my_Note.generateHeader2(logger);
								} catch (Exception e) {
									// TODO: handle exception
									logger.error("error while generating  heading2 of "+directory.getName().toString());
								}
					        	try {
						    		my_Note.generateHeader3(logger);
								} catch (Exception e) {
									// TODO: handle exception
									logger.error("error while generating  heading3 of "+directory.getName().toString());
								}
					        	try {
						    		my_Note.generateHeader4(logger);
								} catch (Exception e) {
									// TODO: handle exception
									logger.error("error while generating  heading4 of "+directory.getName().toString());
								}
					        	try {
						    		my_Note.generateHeader5(logger);
								} catch (Exception e) {
									// TODO: handle exception
									logger.error("error while generating  heading5 of "+directory.getName().toString());
								}
					    		try {
						    		my_Note.generateTags(logger,content_type, query_url,transaction_url,http_method);
								} catch (Exception e) {
									// TODO: handle exception
									logger.error("error while generating  tags of "+directory.getName().toString());
								}
					    		
					        	//System.out.println("object created and additonal elementsadded **************");

					    		//check tags generation
					        	//System.out.println("notes fluree json to test: "+my_Note.getFullJSON(logger));
					        		 //request to create the tag
					        	
				        		String transaction_response = HttpURLFlureeDBConnection.
										sendOkHttpClientPost(content_type,transaction_url,http_method,my_Note.getFullJSON(logger));
				        		//System.out.println("transaction_response on note: "+transaction_response);
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("error while creating note: "+e);
								logger.error("error while creating note: "+directory.toString());
							}
						} //consulting response
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("error while searching note (NotesReader): "+e.toString());
					}
	                
				}//directory.isDirectory()
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("error while reading attributes of  "+directory.getName());
			}
        });
    }


}//end folderreader

