

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



package com.ramos.obsidian.models;

import java.util.regex.Pattern;

public abstract class Matcher {
	
	public String getHeader(String aNote) {
		Pattern h1 = Pattern.compile("(?m)^#(?!#)(.*)");
		Pattern h2 = Pattern.compile("(?m)^#{2}(?!#)(.*)");
		Pattern h3 = Pattern.compile("(?m)^#{3}(?!#)(.*)");
		Pattern h4 = Pattern.compile("(?m)^#{4}(?!#)(.*)");
		Pattern h5 = Pattern.compile("(?m)^#{5}(?!#)(.*)");
		Pattern h6 = Pattern.compile("(?m)^#{6}(?!#)(.*)");
		return null;
	}
}
