

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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			try {
				fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
				if ((FilenameUtils.getExtension(directory.toString())).equalsIgnoreCase("md")){
		       		List<String> linked_notes = new ArrayList<String>();
					UserPrincipal owner = Files.getOwner(path, LinkOption.NOFOLLOW_LINKS);
	                //proceeding to work with the note
	                //we check if the note exist in fluree, before proceed to create it
	                try {
						     //create folder because it does not exist
				        	//create note
				        	String the_content = FileUtils.readFileToString(directory, "UTF-8").replace("\"", "'");
				        	//search for link notes in text
				    		Pattern note_link = Pattern.compile("\\[\\[(.+?)\\]\\]");
				    		Matcher m = note_link.matcher(the_content);
				    		while (m.find()) {
				    			System.out.println(" notes linked: "+m.group(1).replaceAll("\\s+","_")+".md"); 
				    			//querying if to be linked note exists in db.
				    			try {
				    				String http_body = String.format("\"SELECT ?note WHERE { ?note fd:Note/note_name \\\"%s\\\". }\"", m.group(1).replaceAll(" ","_")+".md");
				    				System.out.println("http body query for note: "+http_body);
				    				String consulting_response = HttpURLFlureeDBConnection.
				    						sendOkHttpClientPost(content_type,query_url,http_method,http_body);
				    				System.out.println("linked note response: "+consulting_response);
				    				if (consulting_response.equalsIgnoreCase("[]")) {
										logger.error("linked found, but not related note with name "+m.group(1).toString());
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
											System.out.println("fluree id: "+result[0]);
											linked_notes.add(result[0]);
										}

									}
				    				//second try
				    				
				    			} catch (Exception e) {
				    				// TODO: handle exception
				    			}//end first try catch
				    		}//end while m.find
				    		
				    		
//				        	Note my_Note = new Note(directory.getName().toString(), myString.replace("\"", "'"),fileAttributes.creationTime(), owner.getName().toString(), directory.getParent());
//				        	my_Note.generateHeader1(logger);
//				    		my_Note.generateHeader2(logger);
//				    		my_Note.generateHeader3(logger);
//				    		my_Note.generateHeader4(logger);
//				    		my_Note.generateHeader5(logger);
//				    		//check tags generation
//				    		my_Note.generateTags(logger,content_type, query_url,transaction_url,http_method);
//				        	System.out.println("notes fluree json: "+my_Note.getPartialJSON(logger));
//				        		 //request to create the tag
				        	
				
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("error while searching note (NotesLinker): "+e.toString());
					}
	                
		    		System.out.println("list of links: "+linked_notes);
		    		if (linked_notes.size()> 0) {
						//generate the json file
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
