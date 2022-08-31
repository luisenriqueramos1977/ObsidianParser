

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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class NoteTest {
	
	//local date, current data
	static LocalDate created_on = LocalDate.now();
	private static List<Attention> contained_attention = new ArrayList<>();
	private static Set<Emphasys> contained_emphasys = new HashSet<>();
	private static Set<Header1> contained_header1 = new HashSet<>();
	private static Set<Header2> contained_header2 = new HashSet<>();
	private static Set<Header3> contained_header3 = new HashSet<>();
	private static Set<Header4> contained_header4= new HashSet<>();
	private static Set<Header5> contained_header5= new HashSet<>();
	private static Set<Tag> contained_tags= new HashSet<>();

	
	@ParameterizedTest
	@Order(1)
    @CsvFileSource(resources = "/attentions.csv", delimiter = ';')
    void testListattentions(String name, String contains) {
	    assertNotNull(name);
	    assertNotNull(contains);
    	contained_attention.add(new Attention(name,contains));
    }
	
	@ParameterizedTest
	@Order(2)
    @CsvFileSource(resources = "/emphasys.csv", delimiter = ';')
    void testListEmphasys(String name, String contains) {
	    assertNotNull(name);
	    assertNotNull(contains);
	    contained_emphasys.add(new Emphasys(name,contains));
    }
	
	@ParameterizedTest
	@Order(3)
    @CsvFileSource(resources = "/header1.csv", delimiter = ';')
    void testListHeader1(String name, String contains) {
	    assertNotNull(name);
	    assertNotNull(contains);
	    contained_header1.add(new Header1(name,contains));
    }
	
	@ParameterizedTest
	@Order(4)
    @CsvFileSource(resources = "/header2.csv", delimiter = ';')
    void testListHeader2(String name, String contains) {
	    assertNotNull(name);
	    assertNotNull(contains);
	    contained_header2.add(new Header2(name,contains));
    }
	
	@ParameterizedTest
	@Order(5)
    @CsvFileSource(resources = "/header3.csv", delimiter = ';')
    void testListHeader3(String name, String contains) {
	    assertNotNull(name);
	    assertNotNull(contains);
	    contained_header3.add(new Header3(name,contains));
    }
	
	@ParameterizedTest
	@Order(6)
    @CsvFileSource(resources = "/header4.csv", delimiter = ';')
    void testListHeader4(String name, String contains) {
	    assertNotNull(name);
	    assertNotNull(contains);
	    contained_header4.add(new Header4(name,contains));
    }
	
	@ParameterizedTest
	@Order(7)
    @CsvFileSource(resources = "/header5.csv", delimiter = ';')
    void testListheader5(String name, String contains) {
	    assertNotNull(name);
	    assertNotNull(contains);
	    contained_header5.add(new Header5(name,contains));
    }
	
	@ParameterizedTest
	@Order(8)
    @CsvFileSource(resources = "/tags.csv", delimiter = ';')
    void testListtags(String name, String contains) {
	    assertNotNull(name);
	    assertNotNull(contains);
	    contained_tags.add(new Tag(name,contains));
    }

	@Test
	@AfterAll
	public static void testGetObsidianObjectJson() {
		
		Note my_Note = new Note("note1$test1", "content of note 1",created_on, "Luis Ramos", "c.//folder/folder");

		System.out.println("the partial fluree json: \n"+my_Note.getPartialJSON());
		//System.out.println("getObsidianObjectJson: "+my_Note.getObsidianObjectJson(contained_attention));
	}

}
