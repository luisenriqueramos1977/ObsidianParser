

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

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class NoteTest {
	
	private static List<Attention> contained_attention;
	
	@ParameterizedTest
    @CsvFileSource(resources = "/attentions.csv", delimiter = ';')
    public void testListattentions(String name, String contains) {
    assertNotNull(name);
    assertNotNull(contains);
	 Attention my_attention = new Attention();
	 my_attention.setName(name);
	 my_attention.setContent(contains);
     System.out.println("fucking name: "+name);
      //assertNotNull(name);
    }

//	@Test
//	void testGetObsidianObjectJson() {
//		Note my_Note = new Note(null, null, null, null, null, null, null, null, null, null, null);
//		System.out.println("the partial fluree json: "+my_Note.getPartialJSON());
//
//	}

}
