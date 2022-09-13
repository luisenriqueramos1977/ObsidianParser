

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



/**
 * 
 */
package com.ramos.obsidian.models;

import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Luis Ramos
 *
 */


public final class Folder {
	
	//specific folder variables
	private static String name;
	private static FileTime creation_on;
	private static String located_in;
	private static List<Folder> contained_folders;
	private static List<Note> contained_notes;
	
	
	
	/**
	 * @return the name
	 */
	public static String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public static void setName(String name) {
		Folder.name = name;
	}

	/**
	 * @return the contained_folders
	 */
	public static List<Folder> getContained_folders() {
		return contained_folders;
	}

	/**
	 * @param contained_folders the contained_folders to set
	 */
	public static void setContained_folders(List<Folder> contained_folders) {
		Folder.contained_folders = contained_folders;
	}

	/**
	 * 
	 */
	public Folder(String name, FileTime creation_on, String located_in) {
		this.name=name;
		// TODO Auto-generated constructor stub
		this.creation_on = creation_on;
		this.located_in = located_in;
	}

	/**
	 * @return the creation_on
	 */
	public static FileTime getCreation_on() {
		return creation_on;
	}

	/**
	 * @param creation_on the creation_on to set
	 */
	public static void setCreation_on(FileTime creation_on) {
		Folder.creation_on = creation_on;
	}


	/**
	 * @return the located_in
	 */
	public static String getLocated_in() {
		return located_in;
	}

	/**
	 * @param located_in the located_in to set
	 */
	public static void setLocated_in(String located_in) {
		Folder.located_in = located_in;
	}

	/**
	 * @return the contains_folder
	 */
	public static List<Folder> getContainedFolders() {
		return contained_folders;
	}

	/**
	 * @param contains_folder the contains_folder to set
	 */
	public static void setFolder(List<Folder> contains_folder) {
		Folder.contained_folders = contains_folder;
	}
	
	

	/**
	 * @return the contained_notes
	 */
	public static List<Note> getContained_notes() {
		return contained_notes;
	}

	/**
	 * @param contained_notes the contained_notes to set
	 */
	public static void setContained_notes(List<Note> contained_notes) {
		Folder.contained_notes = contained_notes;
	}

	//get string with all subfolders
	private String getFoldersJson(List<Folder> folders) {
		
		final String my_folders;
		
		String joinedString = folders.stream().map(n -> n.getName()).collect(Collectors.joining("\",\"","{", "}"));

		return joinedString;
	}
	
	//get string with all notes
		private String getNotesJson(List<Note> notes) {
			
			final String my_notes;
			String joinedString = notes.stream().map(n -> n.getName()).collect(Collectors.joining("\",\"","{", "}"));

			return joinedString;
		}

	
	public String getPartialJSON() {
		// TODO Auto-generated method stub
		//String folder_json = getFoldersJson(contained_folders);
		//String notes_json = getNotesJson(contained_notes);
		
		final String a_json = "{\"_id\""+":"+"\""+ "Folder$"+this.getName()+"\",\n"
							  +"\"folderName\""+":"+"\""+this.getName()+"\",\n"
							  +"\"created_on\""+":"+"\""+this.getCreation_on().toString()+"\",\n"+
							  "\"located_in\""+":"+"\""+this.getLocated_in()+"\"}";
		return a_json;
	}
	
	
	public String getFullJSON() {
		return "["+getPartialJSON()+"]";
	}

}
