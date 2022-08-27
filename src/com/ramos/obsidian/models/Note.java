

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
import java.util.stream.Collectors;

/**
 * @author Luis Ramos
 *
 */


public final class Note extends BasicObsidianObject{
	
	//specific note variables
	private static LocalDate created_on;
	private static String creator;
	private static String located_in;
	private static List<Attention> contained_attention;
	private static List<Emphasys> contained_emphasys;
	private static List<Header1> contained_header1;
	private static List<Header2> contained_header2;
	private static List<Header3> contained_header3;
	private static List<Header4> contained_header4;
	private static List<Header5> contained_header5;

	
	public Note(LocalDate creation_on, String creator,String located_in,List<Folder> contained_folders,
			List<Attention> contained_attention, List<Emphasys> contained_emphasys, List<Header1> contained_header1,
			List<Header2> contained_header2, List<Header3> contained_header3, List<Header4> contained_header4, List<Header5> contained_header5) {
		super(getName(), getContent());
		// TODO Auto-generated constructor stub
		this.created_on = created_on;
		this.creator = creator;
		this.located_in = located_in;
		this.contained_attention = contained_attention;
		this.contained_emphasys = contained_emphasys;
		this.contained_header1 =contained_header1 ;
		this.contained_header2 =contained_header2;
		this.contained_header3 =contained_header3;
		this.contained_header4 =contained_header4;
		this.contained_header5 =contained_header5;
	}

	
	/**
	 * @return the created_on
	 */
	public static LocalDate getCreated_on() {
		return created_on;
	}

	/**
	 * @param created_on the created_on to set
	 */
	public static void setCreated_on(LocalDate created_on) {
		Note.created_on = created_on;
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
		Note.creator = creator;
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
		Note.located_in = located_in;
	}


	/**
	 * @return the contained_attention
	 */
	public static List<Attention> getContained_attention() {
		return contained_attention;
	}


	/**
	 * @param contained_attention the contained_attention to set
	 */
	public static void setContained_attention(List<Attention> contained_attention) {
		Note.contained_attention = contained_attention;
	}


	/**
	 * @return the contained_emphasys
	 */
	public static List<Emphasys> getContained_emphasys() {
		return contained_emphasys;
	}


	/**
	 * @param contained_emphasys the contained_emphasys to set
	 */
	public static void setContained_emphasys(List<Emphasys> contained_emphasys) {
		Note.contained_emphasys = contained_emphasys;
	}

	/**
	 * @return the contained_header1
	 */
	public static List<Header1> getContained_header1() {
		return contained_header1;
	}

	/**
	 * @param contained_header1 the contained_header1 to set
	 */
	public static void setContained_header1(List<Header1> contained_header1) {
		Note.contained_header1 = contained_header1;
	}


	/**
	 * @return the contained_header2
	 */
	public static List<Header2> getContained_header2() {
		return contained_header2;
	}


	/**
	 * @param contained_header2 the contained_header2 to set
	 */
	public static void setContained_header2(List<Header2> contained_header2) {
		Note.contained_header2 = contained_header2;
	}


	/**
	 * @return the contained_header3
	 */
	public static List<Header3> getContained_header3() {
		return contained_header3;
	}


	/**
	 * @param contained_header3 the contained_header3 to set
	 */
	public static void setContained_header3(List<Header3> contained_header3) {
		Note.contained_header3 = contained_header3;
	}

	/**
	 * @return the contained_header4
	 */
	public static List<Header4> getContained_header4() {
		return contained_header4;
	}

	/**
	 * @param contained_header4 the contained_header4 to set
	 */
	public static void setContained_header4(List<Header4> contained_header4) {
		Note.contained_header4 = contained_header4;
	}

	/**
	 * @return the contained_header5
	 */
	public static List<Header5> getContained_header5() {
		return contained_header5;
	}

	/**
	 * @param contained_header5 the contained_header5 to set
	 */
	public static void setContained_header5(List<Header5> contained_header5) {
		Note.contained_header5 = contained_header5;
	}

    //get json per object
//	  {
//	        "_id": "_predicate",
//	        "name": "Header3/textContent",
//	        "type": "string",
//	        "doc": "Text content of Header level 3 of a note"
//	    },
	 
	//get string with all subfolders
		public String getObsidianObjectJson(List<? extends BasicObsidianObject> Objects) {
			String my_folders;
			String joinedString;
			
			try {
				//lambda foreach
				//folders.stream().forEach((folder) -> my_folders = my_folders+ "\"constains_folder\""+":"+"\""+folder+"\"" );
				// Convert elements to strings and concatenate them, separated by commas
				// String joined = folders.stream().map(n -> n.getName()).collect(Collectors.joining("\"constains_folder\""+":"+"\""));
				//String joinedString = folders.stream().map(n -> n.getName()).collect(Collectors.joining());    //ABCD
				//String joinedString = folders.stream().map(n -> n.getName()).collect(Collectors.joining(","));
				joinedString = Objects.stream().map(n -> n.getName()).collect(Collectors.joining("\",\"","{", "}"));
			} catch (Exception e) {
				// TODO: handle exception
				joinedString="";
			}

			return joinedString;
		}


	@Override
	public String getPartialJSON() {
		// TODO Auto-generated method stub
		final String a_json = "{\"_id: \""+ this.getName()+"\","
							  +"\"textContent\""+":"+"\""+this.getContent()+"\","
							  +"\"name\""+":"+"\""+this.getName()+"\""+
							  getObsidianObjectJson(contained_attention)+
							  getObsidianObjectJson(contained_emphasys)+
							  getObsidianObjectJson(contained_header1)+
							  getObsidianObjectJson(contained_header2)+
							  getObsidianObjectJson(contained_header3)+
							  getObsidianObjectJson(contained_header4)+
							  getObsidianObjectJson(contained_header5)+
							  "}";
		return a_json;
		
	}
	
	@Override
	public String getFullJSON() {
		return "["+getPartialJSON()+"]";
	}

}