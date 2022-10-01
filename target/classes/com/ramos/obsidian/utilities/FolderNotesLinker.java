

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


import java.util.List;

import org.slf4j.Logger;

import com.ramos.obsidian.models.HttpURLFlureeDBConnection;



public abstract class FolderNotesLinker {
	
	public static void folderSearchNotesLink(Logger logger, String content_type, String query_url, String transaction_url, String http_method) throws Exception {
		
		//first we get all folders in fluree, if any, in no folder, we finish
		try {
			String http_body_1 = "\"SELECT ?id ?location WHERE { ?folder fd:Folder/located_in  ?location ;  fd:_id ?id.}\"";
			String consulting_note = HttpURLFlureeDBConnection.sendOkHttpClientPost(content_type,query_url,http_method,http_body_1);
			if (!consulting_note.equals("[]")) {
				List<String> response_list = GenericUtilities.getList(consulting_note);
				//streamign over the list
				response_list.forEach(folder ->{
		            String[] result = folder.split(",");
		            try {
						System.out.println("id: "+result[0].replace("[", "").replace("]", ""));
						System.out.println("location: "+result[1]);
						//check model located in
					} catch (Exception e) {
						// TODO: handle exception
					}


				});//end streaming 
				

			}//
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}//folderSearchNotesLink


}//end FolderNotesLinker
