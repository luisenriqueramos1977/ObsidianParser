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


package com.ramos.obsidian;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ramos.obsidian.models.Attention;
import com.ramos.obsidian.models.Car;


public class JSON_tester {
	
	


	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		//List of my obsidian objects
//		File testFile = new File("");
//	    String currentPath = testFile.getAbsolutePath();
//	    System.out.println("current path is: " + currentPath);
		List<List<String>> records = new ArrayList<>();
		
		List<Attention> contained_attention = new ArrayList<>();

		
		//loading and reading attention csv
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\Documents\\GitHub\\ObsidianParser\\src\\attentions.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(";");
		        records.add(Arrays.asList(values));
		    	contained_attention.add(new Attention(values[0],values[1]));
		    }
		} 
			
		contained_attention.stream().forEach(an_attention -> System.out.println(an_attention.getPartialJSON()));

	}

}
