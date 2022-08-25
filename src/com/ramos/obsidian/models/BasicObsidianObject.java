

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
 * @author Luis Ramos. Abstract class containing the basic elements of an 
 *obsidian model, which are name, text conttent and the json model generator
 *
 */

public abstract class BasicObsidianObject {
	private static String name;
	private static String content;
	
	
	/**
	 * @param name
	 * @param content
	 */
	public BasicObsidianObject(String name, String content) {
		this.name = name;
		this.content = content;
	}
	
	
	/**
	 * @return the name
	 */
	public static String getName() {
		return name;
	}
	
	/**
	 * @return the content
	 */
	public static String getContent() {
		return content;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public abstract String getPartialJSON();
	
	public abstract String getFullJSON();


}
