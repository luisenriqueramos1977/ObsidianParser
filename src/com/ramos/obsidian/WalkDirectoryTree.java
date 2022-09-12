

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

import java.io.File;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
// Java sample program to iterate through directory tree
// Uses the Files.walk method added in Java 8
public class WalkDirectoryTree {
	
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    
    
    public static void main(String[] args)  {
    	
    	 System.setProperty("log4j.configurationFile",  "bin/com/ramos/obsidian/resources/log4j2.xml");
    	 System.setProperty("logFilename", "myApp.log");
    	
        Logger logger = LoggerFactory.getLogger( WalkDirectoryTree.class);

        // in windows use the form c:\\folder
        String rootFolder = "C:\\Users\\User\\Documents\\luis_obsidian";
        try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			logger.info("process started at "+timestamp);
			walkDirTree(rootFolder);
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
    private static void walkDirTree(String rootFolder) throws Exception {
    	System.out.println("simply print files and folders");
   	    String separator = System.getProperty("file.separator");
        System.out.println(separator);

        Files.walk(Paths.get(rootFolder)).forEach(path -> {
        	String name = path.toString();
        	int lastIndexOf = name.lastIndexOf(".");
            if (lastIndexOf == -1) {
                System.out.println(name + " is a folder");
                //create folder in fluree
                String folder_name = name.substring(name.lastIndexOf(separator) + 1);
                System.out.println(folder_name + " is a folder_name");
                
            }
            else if (name.substring(lastIndexOf).endsWith(".md")){
            	System.out.println(name + " is a markdown file");
            	  File file = new File(name);
            	  
                //create note in fluree
            }
            
        });
    }
  
}
