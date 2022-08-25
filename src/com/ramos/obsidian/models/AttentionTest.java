

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

class AttentionTest {

	@Test
	void testGetFullJSON() {
		Attention my_attention = new Attention();
		my_attention.setName("attention$test2");
		my_attention.setContent("test attention text");
		System.out.println("the partial fluree json: "+my_attention.getPartialJSON());
		System.out.println("the full fluree json: "+my_attention.getFullJSON());
	}

}
