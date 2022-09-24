

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
package com.ramos.obsidian.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Luis Ramos
 *
 */
public abstract class GenericUtilities {
	
	public static List getList(String response){
		List<String> thelist = new ArrayList<>();
		Pattern note_link = Pattern.compile("\\[(.+?)\\]");
		Matcher m = note_link.matcher(response);
		while (m.find()) {
			thelist.add(m.group(1));
		}//end m.find()
		return thelist;
	}

}
