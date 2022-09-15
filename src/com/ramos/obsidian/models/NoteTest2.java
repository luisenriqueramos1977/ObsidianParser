

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

import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.ramos.obsidian.WalkDirectoryTree;

class NoteTest2 {
	
	//local date, current data
		private static Date firstDate = new Date();
		private static FileTime created_on = FileTime.fromMillis( firstDate.getTime() );
    	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(NoteTest2.class);

	
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
				+ "#happy, #new, #year #cancer \n";
		
	
	//create note
		
	Note my_Note = new Note("test1", "content of note 1",created_on, "Luis Ramos", "c.//folder/folder");
	


	@Test
	@Order(1)
	void testGetHeader1() {
		my_Note.setContent(content);
		my_Note.generateHeader1(logger);
		my_Note.generateHeader2(logger);
		my_Note.generateHeader3(logger);
		my_Note.generateHeader4(logger);
		my_Note.generateHeader5(logger);
		my_Note.generateNoteLinks(logger,"text/plain", 
				"http://127.0.0.1:8090/fdb/my/obsidian3/sparql"
				,"POST");
		my_Note.generateTags(logger,"text/plain", 
				"http://127.0.0.1:8090/fdb/my/obsidian3/sparql", 
				"http://127.0.0.1:8090/fdb/my/obsidian3/transact","POST");
		System.out.println("parsed all elements");
		System.out.println("the partial fluree json: \n"+my_Note.getPartialJSON(logger));
	}

	
}
