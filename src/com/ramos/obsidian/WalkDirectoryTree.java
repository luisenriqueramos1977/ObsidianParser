

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

import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 
// Java sample program to iterate through directory tree
// Uses the Files.walk method added in Java 8
public class WalkDirectoryTree {
    public static void main(String[] args) throws Exception {
        // in windows use the form c:\\folder
        String rootFolder = "C:\\Users\\User\\Documents\\luis_obsidian";
        walkDirTree(rootFolder);
        //walkDirTreeWithSymLinks(rootFolder);
 
       // String searchFor = "md";
        //walkDirTreeAndSearch(rootFolder, searchFor);
 
    }
 
    // Prints all file/directory names in the entire directory tree!
    private static void walkDirTree(String rootFolder) throws Exception {
    	System.out.println("simply print files and folders");
        Files.walk(Paths.get(rootFolder)).forEach(path -> {
        	String name = path.toString();
        	int lastIndexOf = name.lastIndexOf(".");
            if (lastIndexOf == -1) {
                System.out.println(name + " is a folder");
                //create folder in fluree
            }
            else if (name.substring(lastIndexOf).endsWith(".md")){
            	System.out.println(name + " is a markdown file");
                //create note in fluree
            }
            
        });
    }
 
    // This example uses FileVisitOption to follow symbolic links
    // Prints all file/directory names in the entire directory tree
    // Prints all file/directory names in the entire directory tree!
//    private static void walkDirTreeWithSymLinks(String rootFolder) throws Exception {
//    	System.out.println("with symbolic links");
//        Files.walk(Paths.get(rootFolder), FileVisitOption.FOLLOW_LINKS).forEach(path -> {
//            System.out.println(path);
//        });
//    }
// 
    

    // Walk the directory tree looking for files/folder with the search field
    // May throw AccessDeniedException or FileSystemLoopException exception.
//    private static void walkDirTreeAndSearch(String rootFolder, String search) throws Exception {
//    	System.out.println("with search text");
//        Files.walk(Paths.get(rootFolder), FileVisitOption.FOLLOW_LINKS).filter(t -> {
//            return t.toString().contains(search);
//        }).forEach(path -> {
//            System.out.println(path);
//        });
//    }
}
