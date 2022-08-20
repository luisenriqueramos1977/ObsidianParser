

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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Luis Ramos
 *
 */


public final class Folder extends BasicObsidianObject{
	
	//specific folder variables
	private static LocalDate creation_on;
	private static String creator;
	private static String located_in;
	private static List<Folder> contained_folders;
	private static List<Note> contained_notes;
	
	
	
	
	
	
	/**
	 * 
	 */
	private Folder(LocalDate creation_on, String creator, String located_in, List<Folder> contained_folders, List<Note> contained_notes) {
		super();
		// TODO Auto-generated constructor stub
		this.creation_on = creation_on;
		this.creator = creator;
		this.located_in = located_in;
		this.contained_folders= contained_folders;
		this.contained_notes= contained_notes;
	}

	/**
	 * @return the creation_on
	 */
	public static LocalDate getCreation_on() {
		return creation_on;
	}

	/**
	 * @param creation_on the creation_on to set
	 */
	public static void setCreation_on(LocalDate creation_on) {
		Folder.creation_on = creation_on;
	}

	/**
	 * @return the creator
	 */
	public static String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public static void setCreator(String creator) {
		Folder.creator = creator;
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
		
		//lambda foreach
		
		//folders.stream().forEach((folder) -> my_folders = my_folders+ "\"constains_folder\""+":"+"\""+folder+"\"" );
		
		// Convert elements to strings and concatenate them, separated by commas
		// String joined = folders.stream().map(n -> n.getName()).collect(Collectors.joining("\"constains_folder\""+":"+"\""));
		//String joinedString = folders.stream().map(n -> n.getName()).collect(Collectors.joining());    //ABCD
		//String joinedString = folders.stream().map(n -> n.getName()).collect(Collectors.joining(","));
		String joinedString = folders.stream().map(n -> n.getName()).collect(Collectors.joining("\",\"","{", "}"));

		return joinedString;
	}

	@Override
	public String getJSON() {
		// TODO Auto-generated method stub
		String folder_json = getFoldersJson(contained_folders);
		
		final String a_json = "{\"_id: \""+ this.getName()+"\","
							  +"\"name\""+":"+"\""+this.getName()+"\""
							  +"\"created_on\""+":"+"\""+this.getCreation_on()+"\""+
							  "\"creator\""+":"+"\""+this.getCreator()+"\""+
							  "\"located_in\""+":"+"\""+this.getLocated_in()+"\""+
							  folder_json+
							  "\"constains_note\""+":"+"\""+this.getContained_notes()+"\""+
							  "}";
		return a_json;
		
	}

}
