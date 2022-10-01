

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


public final class Header3 {
	
	public String name;
	public String content;

    public Header3(String name, String content) {
        this.name = name;
		this.content = content;
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
		final String a_json = "{\"_id\""+":"+"\""+  this.getName()+"\","
							  +"\"text_content\""+":"+"\""+this.getContent()+"\","
							  +"\"name\""+":"+"\""+this.getName()+"\""+
							  "}";
		return a_json;
	}

	public String getFullJSON() {
		return "["+getPartialJSON()+"]";
	}

}
