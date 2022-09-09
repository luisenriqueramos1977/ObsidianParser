

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

/**
 * @author Luis Ramos
 *
 */


public final class Tag{
	
	private static String name;
	private static String content;
	private static long fluree_id;
	private static Boolean  exists_in_fluree;

    public Tag(String name, String content, long fluree_id, Boolean exists_in_fluree) {
        this.name = name;
		this.content = content;
		this.fluree_id = fluree_id;
		this.exists_in_fluree=exists_in_fluree;
    }
    
    

	
	/**
	 * @return the exists_in_fluree
	 */
	public static Boolean getExists_in_fluree() {
		return exists_in_fluree;
	}


	/**
	 * @param exists_in_fluree the exists_in_fluree to set
	 */
	public static void setExists_in_fluree(Boolean exists_in_fluree) {
		Tag.exists_in_fluree = exists_in_fluree;
	}




	/**
	 * @return the name
	 */
	public  String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public  void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the content
	 */
	public  String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public String getPartialJSON() {
		// TODO Auto-generated method stub
		final String a_json = "{\"_id\""+":"+"\""+ "Tag$"+ this.getName()+"\","
							  +"\"textContent\""+":"+"\""+this.getContent()+"\","
							  +"\"name\""+":"+"\""+this.getName()+"\""+
							  "}\n";
		return a_json;
	}
	
	public String getFullJSON() {
		return "["+getPartialJSON()+"]";
	}


	/**
	 * @return the fluree_id
	 */
	public long getFluree_id() {
		return fluree_id;
	}


	/**
	 * @param fluree_id the fluree_id to set
	 */
	public void setFluree_id(long fluree_id) {
		this.fluree_id = fluree_id;
	}
	

}

