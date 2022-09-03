

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

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class NoteTest2 {
	
	//local date, current data
		private static LocalDate created_on = LocalDate.now();
		private static List<Attention> contained_attention = new ArrayList<>();
		private static Set<Emphasys> contained_emphasys = new HashSet<>();
		private static Set<Header1> contained_header1 = new HashSet<>();
		private static Set<Header2> contained_header2 = new HashSet<>();
		private static Set<Header3> contained_header3 = new HashSet<>();
		private static Set<Header4> contained_header4= new HashSet<>();
		private static Set<Header5> contained_header5= new HashSet<>();
		private static Set<Tag> contained_tags= new HashSet<>();
	
	//create note string
		final String content = "# heading 1 \n"
				 + "# second heading 1 #testingtag \n"
			     + "bla bla bla\n"
			     + "fbla bla bla [[note 2]] \n"
			     + "### heading 3 djdjdj \n"
			     + "bla [[note 3]] bla bla \n"
			     + "## heading 2 bal;kasddfas \n"
			     + "fbla bla bla \n"
		         + "fbla [[note 4]] bla bla \n"
				+ "#happy, #new, #year \n";
		
	
	//create note
		
	Note my_Note = new Note("note1$test1", "content of note 1",created_on, "Luis Ramos", "c.//folder/folder");
	


	@Test
	@Order(1)
	void testGetHeader1() {
		my_Note.setContent(content);
//		my_Note.generateHeader1();
//		my_Note.generateHeader2();
//		my_Note.generateHeader3();
//		my_Note.generateHeader4();
//		my_Note.generateHeader5();
//		my_Note.generateNoteLinks();
		my_Note.generateTags("text/plain", 
				"http://127.0.0.1:8090/fdb/my/obsidian3/sparql", 
				"POST");
		System.out.println("generated all elements");
		System.out.println("the partial fluree json: \n"+my_Note.getPartialJSON());
	}

	
}
