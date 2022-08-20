

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

/**
 * @author Luis Ramos
 *
 */


public final class Note extends BasicObsidianObject{
	
	//specific note variables
	private static LocalDate created_on;
	private static String creator;
	private static String located_in;
	private static List<Folder> contained_folders;
	private static List<Note> contained_notes;
	private static List<Attention> contained_attention;
	private static List<Emphasys> contained_emphasys;
	private static List<Header1> contained_header1;
	private static List<Header2> contained_header2;
	private static List<Header3> contained_header3;
	private static List<Header4> contained_header4;
	private static List<Header5> contained_header5;



	
	
	
	private Note(LocalDate creation_on, String creator,String located_in,List<Folder> contained_folders,List<Note> contained_notes,
			List<Attention> contained_attention, List<Emphasys> contained_emphasys, List<Header1> contained_header1,
			List<Header2> contained_header2, List<Header3> contained_header3, List<Header4> contained_header4, List<Header5> contained_header5) {
		super();
		// TODO Auto-generated constructor stub
		this.created_on = created_on;
		this.creator = creator;
		this.located_in = located_in;
		this.contained_folders = contained_folders;
		this.contained_notes = contained_notes;
		this.contained_attention = contained_attention;
		this.contained_emphasys = contained_emphasys;
		this.contained_header1 =contained_header1 ;
		this.contained_header2 =contained_header2;
		this.contained_header3 =contained_header3;
		this.contained_header4 =contained_header4;
		this.contained_header5 =contained_header5;
	}

	@Override
	public String getJSON() {
		// TODO Auto-generated method stub
		final String a_json = "{\"_id: \""+ this.getName()+"\","
							  +"\"textContent\""+":"+"\""+this.getContent()+"\""
							  +"\"name\""+":"+"\""+this.getName()+"\""+
							  "}";
		return a_json;
		
	}

}