

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ramos.obsidian.models.Car;

public class CarTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Car> list = new ArrayList<>();
		list.add(new Car("Volvo V40" , "XYZ 201845", 5));
		list.add(new Car("Citroen C1", "ABC 164521", 4));
		list.add(new Car("Dodge Ram" , "KLM 845990", 2));
		
		Iterator<Car> iterator = list.iterator();
		while(iterator.hasNext()) {
		    Car next = iterator.next();
		    System.out.println(next.getBrand());
		}
	}

}
