

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

import org.junit.jupiter.api.Test;

class Header1Test {
	
	Header1 my_header1 = new Header1();

	@Test
	void testSetName() {
		my_header1.setName("header_test1");
	}

	@Test
	void testSetContent() {
		my_header1.setContent("test content");
	}
	
	@Test
	void testGetName() {
		System.out.println("the name: "+my_header1.getName());
	}

	

	@Test
	void testGetContent() {
		System.out.println("the content: "+my_header1.getName());
	}
	
	@Test
	void testGetJSON() {

		System.out.println("the fluree json: "+my_header1.getJSON());

	}

	

	

}
