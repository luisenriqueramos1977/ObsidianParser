

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
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;

import com.ramos.obsidian.models.HttpURLFlureeDBConnection;
import com.ramos.obsidian.models.Note;

public abstract class NotesLinker {
	
	public static void notesSearchLink(String rootFolder, Logger logger, String content_type, String query_url, String transaction_url, String http_method) throws Exception {
    	//System.out.println("simply print files and folders");
   	    String separator = System.getProperty("file.separator");

   	    //
        //System.out.println(separator);
        Files.walk(Paths.get(rootFolder)).forEach(path -> {
        	File directory = new File(path.toString());
            BasicFileAttributes fileAttributes;
       	    Boolean previouslinkednotes =false;
       	    Boolean note_in_fluree =false;
       	    String fluree_json=null;

			try {
				fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
				if ((FilenameUtils.getExtension(directory.toString())).equalsIgnoreCase("md")){
		       		List<String> to_link_notes = new ArrayList<String>();
		       		List<String> linked_notes = new ArrayList<String>();

					UserPrincipal owner = Files.getOwner(path, LinkOption.NOFOLLOW_LINKS);
	                //proceeding to work with the note
	                //we check if the note exist in fluree, before proceed to create it
	                try {
						    //creating note name
	                		String current_note = directory.getName().replaceAll(" ","_");
	                		//System.out.println("note name to be used: "+current_note);
	                		//getting note fluree id
	                		//querying for existing linked note
	                		try {
			    				String http_body_1 = String.format("\"SELECT ?note WHERE { ?note fd:Note/note_name \\\"%s\\\". }\"", current_note);
			    				//System.out.println("queries for note to be linked: "+http_body_1);
			    				String consulting_note = HttpURLFlureeDBConnection.
			    						sendOkHttpClientPost(content_type,query_url,http_method,http_body_1);
			    				//System.out.println("main note in fluree: "+consulting_note);
			    				if (consulting_note.equals("[]")) {
			    					note_in_fluree =false;
			    					logger.error(current_note+ " not in fluree");
								} else {
									note_in_fluree =true;  
									/*
									 * getting main note fluree id
									 */
									String consulting_left_main =consulting_note.replace("[", "");
						        	//System.out.println("consulting_left_main: "+consulting_left_main);
						        	String main_fluree_id =consulting_left_main.replace("]", "");
						    		//System.out.println("main note id: "+ main_fluree_id);
									/*
									 * ***********************************************************
									 * *****searching linked notes in text, then search in fluree*******
									 * **********************************************************
									 */
									String the_content = FileUtils.readFileToString(directory, "UTF-8").replace("\"", "'");//readint the content of the file (directory)
						        	//search for link notes in text
						    		Pattern note_link = Pattern.compile("\\[\\[(.+?)\\]\\]");
						    		Matcher m = note_link.matcher(the_content);
						    		while (m.find()) {
						    			//querying if to be linked note exists in db.
						    			try {
						    				//removing the last element in string, if it is blank
						    				String splitted_string_1 = m.group(1).replaceAll("\\s+","_")+".md";
							    			//System.out.println(" notes linked in text 1: "+splitted_string_1); 
											String splitted_string_2 = splitted_string_1.replaceAll("_.md",".md");
							    			//System.out.println(" notes linked in text 2: "+splitted_string_2); 
						    				String http_body = String.format("\"SELECT ?note WHERE { ?note fd:Note/note_name \\\"%s\\\". }\"", splitted_string_2);
						    				//System.out.println("http body query for note: "+http_body);
						    				String consulting_response = HttpURLFlureeDBConnection.
						    						sendOkHttpClientPost(content_type,query_url,http_method,http_body);
						    				
						    				//System.out.println("linked note response: "+consulting_response);
						    				if (consulting_response.equalsIgnoreCase("[]")) {
												logger.error("linked found in note, but not related note with name "+m.group(1).toString()+" in fluree");
											} else {
												String consulting_left_brackets =consulting_response.replace("[", "");
									        	//System.out.println("consulting_left_brackets: "+consulting_left_brackets);
									        	String consulting_right_brackets =consulting_left_brackets.replace("]", "");
									        	//System.out.println("consulting_right_brackets: "+consulting_right_brackets);
									        	//after removing brackets, then try to splitting, if any
									            String[] result = consulting_right_brackets.split(",");
									            //System.out.println("splitted result "+result.length);
									            if (result.length > 1) {
									            	logger.error("error while generating tag: "+m.group(1));
												} else {
													//System.out.println("fluree id: "+result[0]);
													to_link_notes.add(result[0]);
												}
											}
						    				//second try
						    				
						    			} catch (Exception e) {
						    				// TODO: handle exception
						    			}//end first try catch
						    		}//end while m.find
						    		//checking if any to link
						    		//System.out.println("list of links in note text: "+to_link_notes);
						    		/*
									 * end searching notes in text, array linked_notes filled with linkable notes
									 */
						    		//System.out.println("searching for linked notes in fluree");
								   	//querying for existing linked note
				                		try {
						    				String http_body_2 = String.format("\"SELECT ?note_link WHERE { ?note fd:Note/note_name \\\"%s\\\"; "
						    						+ "fd:Note/linked_to_note2 ?note_link . }\"", current_note);
						    				//System.out.println("queries for linked notes: "+http_body);
						    				String consulting_linked_note = HttpURLFlureeDBConnection.
						    						sendOkHttpClientPost(content_type,query_url,http_method,http_body_2);
						    				//System.out.println("linked note response: "+consulting_linked_note);
						    				//whatever the result we have to get the fluree id of every one
						    				//with the list of linked notes, we decide about the json and the load of linked notes					 
						    				if (consulting_linked_note.equals("[]")) {
						    					previouslinkednotes =true;
						    					System.out.println("");
											} else {
												previouslinkednotes =false;
												String consulting_left_brackets =consulting_linked_note.replace("[", "");
									        	//System.out.println("consulting_left_brackets: "+consulting_left_brackets);
									        	String consulting_right_brackets =consulting_left_brackets.replace("]", "");
									        	//System.out.println("consulting_right_brackets: "+consulting_right_brackets);
									        	//after removing brackets, then try to splitting, if any
									            String[] result = consulting_right_brackets.split(",");
									            //System.out.println("splitted result "+result.length);
									            linked_notes = Arrays.asList(result);
											}
						    				
										} catch (Exception e) {
											// TODO: handle exception
										}
						    		
				                	//calc diff linked_notes with to_link_notes
				                	List<String> differences = new ArrayList<>(to_link_notes);
				                	differences.removeAll(linked_notes);
						    		
						    		if (differences.size()> 0) {
										//generate the json file
						    			if (differences.size() == 1) {
							    			fluree_json = "\n [{\"_id\":"+main_fluree_id+",\n"
							    					+"\"linked_to_note2\":"+differences.get(0)+"}]";
							    			//System.out.println("fluree_json: "+fluree_json );
										} else {
											String fluree_string_head = "\n [{\"_id\":"+main_fluree_id+",\n";
							    			String fluree_json_body = differences.stream().map(n -> n).collect(Collectors.joining(",\n"));
							    			fluree_json =  fluree_string_head+"\"linked_to_note2\":["+fluree_json_body+"]}]";
							    			//System.out.println("fluree json body: "+fluree_json);
										}
						    			//proceed to write on fluree 
						    			try {
							    			//System.out.println("fluree_json: "+fluree_json );
							        		String transaction_response = HttpURLFlureeDBConnection.
													sendOkHttpClientPost(content_type,transaction_url,http_method,fluree_json);
							        		//System.out.println("transaction_response on notes link: "+transaction_response);
										} catch (Exception e) {
											// TODO: handle exception
											logger.error("error while creating note: "+directory.toString());
										}
									} 
				                	//linked_notes.size()> 0
	
								}//if consulting linked notes
			    				
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("error while searching for note");
							}				
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("error while searching note (NotesLinker): "+e.toString());
					}

				}//if directory.isDirectory()
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("error while reading attributes of  "+directory.getName());
			}
			

        });
    }

}//end noteslinker
