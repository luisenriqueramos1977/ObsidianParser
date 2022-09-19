

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
package com.ramos.obsidian.models;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.json.JSONException;

import com.ramos.obsidian.models.HttpURLFlureeDBConnection;

/**
 * @author Luis Ramos
 *
 */


public final class Note {
	
	public String name;
	public String content;
	//specific note variables
	private static FileTime created_on;
	private static String creator;
	private static String located_in;
	private static List<Attention> contained_attention = new ArrayList<Attention>();
	private static Set<Emphasys> contained_emphasys = new HashSet<>();
	private static Set<Header1> contained_header1 = new HashSet<>();
	private static Set<Header2> contained_header2 = new HashSet<>();
	private static Set<Header3> contained_header3 = new HashSet<>();
	private static Set<Header4> contained_header4= new HashSet<>();
	private static Set<Header5> contained_header5= new HashSet<>();
	private static Set<Tag> contained_tags= new HashSet<>();
	private static Set<Note> contained_notes= new HashSet<>();
	private static Map<String, String> tags_map = new HashMap<String, String>();
	private static List<String> linked_notes = new ArrayList<String>();
	private static Boolean full_notes_links;



	
	public Note(String name, String content, FileTime created_on, String creator,String located_in) {
		// TODO Auto-generated constructor stub
		this.name =name.replaceAll("\\s+","_");
		this.content= content;
		this.created_on = created_on;
		this.creator = creator;
		this.located_in = located_in;
		this.contained_header1.clear();
		this.contained_header2.clear();
		this.contained_header3.clear();
		this.contained_header4.clear();
		this.contained_header5.clear();
		this.contained_attention.clear();
		this.contained_emphasys.clear();
		this.contained_tags.clear();
		this.contained_notes.clear();
		this.tags_map.clear();
		this.linked_notes.clear();
	}

	
	
	/**
	 * @return the tags_map
	 */
	public static Map<String, String> getTags_map() {
		return tags_map;
	}


	/**
	 * @param tags_map the tags_map to set
	 */
	public static void setTags_map(Map<String, String> tags_map) {
		Note.tags_map = tags_map;
	}



	/**
	 * @return the name
	 */
	public  String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public  void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the content
	 */
	public  String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @return the created_on
	 */
	public static FileTime getCreated_on() {
		return created_on;
	}

	/**
	 * @param created_on the created_on to set
	 */
	public static void setCreated_on(FileTime created_on) {
		Note.created_on = created_on;
	}

	/**
	 * @return the creator
	 */
	public static String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public static void setCreator(String creator) {
		Note.creator = creator;
	}


	/**
	 * @return the located_in
	 */
	public static String getLocated_in() {
		return located_in;
	}


	/**
	 * @param located_in the located_in to set
	 */
	public static void setLocated_in(String located_in) {
		Note.located_in = located_in;
	}


	/**
	 * @return the contained_attention
	 */
	public static List<Attention> getContained_attention() {
		return contained_attention;
	}


	/**
	 * @param contained_attention the contained_attention to set
	 */
	public static void setContained_attention(List<Attention> contained_attention) {
		Note.contained_attention = contained_attention;
	}


	/**
	 * @return the contained_emphasys
	 */
	public static Set<Emphasys> getContained_emphasys() {
		return contained_emphasys;
	}


	/**
	 * @param contained_emphasys the contained_emphasys to set
	 */
	public static void setContained_emphasys(Set<Emphasys> contained_emphasys) {
		Note.contained_emphasys = contained_emphasys;
	}

	/**
	 * @return the contained_header1
	 */
	public static Set<Header1> getContained_header1() {
		return contained_header1;
	}

	/**
	 * @param contained_header1 the contained_header1 to set
	 */
	public static void setContained_header1(Set<Header1> contained_header1) {
		Note.contained_header1 = contained_header1;
	}


	/**
	 * @return the contained_header2
	 */
	public static Set<Header2> getContained_header2() {
		return contained_header2;
	}


	/**
	 * @param contained_header2 the contained_header2 to set
	 */
	public static void setContained_header2(Set<Header2> contained_header2) {
		Note.contained_header2 = contained_header2;
	}


	/**
	 * @return the contained_header3
	 */
	public static Set<Header3> getContained_header3() {
		return contained_header3;
	}


	/**
	 * @param contained_header3 the contained_header3 to set
	 */
	public static void setContained_header3(Set<Header3> contained_header3) {
		Note.contained_header3 = contained_header3;
	}

	/**
	 * @return the contained_header4
	 */
	public static Set<Header4> getContained_header4() {
		return contained_header4;
	}

	/**
	 * @param contained_header4 the contained_header4 to set
	 */
	public static void setContained_header4(Set<Header4> contained_header4) {
		Note.contained_header4 = contained_header4;
	}

	/**
	 * @return the contained_header5
	 */
	public static Set<Header5> getContained_header5() {
		return contained_header5;
	}
	
	/**
	 * @param contained_header5 the contained_header4 to set
	 */
	public static void setContained_header5(Set<Header5> contained_header4) {
		Note.contained_header5 = contained_header5;
	}

	/**
	 * @param contained_header5 the contained_header5 to set
	 */
	public static void setContained_tags(Set<Tag> contained_tags) {
		Note.contained_tags = contained_tags;
	}
	
	/**
	 * @return the contained_attention
	 */
	public static Set<Tag> getContained_tags() {
		return contained_tags;
	}
	 
	//get string with all subfolders
		public String getObsidianObjectJson(List<? extends BasicObsidianObject> Objects) {
			String my_folders;
			String joinedString;
			// enhanced for-loop also uses an iterator behind the scenes
	        for (BasicObsidianObject item: Objects) {
	            System.out.println("item.getName():"+ item.getName());
	        }
			try {
				//lambda foreach
				joinedString = Objects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(","));
			} catch (Exception e) {
				// TODO: handle exception
				joinedString="";
			}

			return joinedString;
		}


	
	public String getPartialJSON(Logger logger) {
		// TODO Auto-generated method stub
		LinkedList<String> fluree_links=new LinkedList<String>();  
		LinkedList<String> fluree_bodies=new LinkedList<String>();  
		String json_links;
		String json_bodies;
		String fluree_head_links;
		String fluree_string_head = "\n [{\"_id\": \"Note$"+this.getName()+"\",\n"+
				  "\"text_content\""+":"+"\""+this.getContent().replace("\n", "").replace("\r", "")+"\",\n"+
				  "\"note_name\""+":"+"\""+this.getName()+"\",\n"+
				  "\"created\""+":"+"\""+this.getCreated_on()+"\"";
		
		//adding attentions
		if (!contained_attention.isEmpty()) {
			if (contained_attention.size()>1) {
				fluree_links.add(getAttentionRelationJson(logger, contained_attention, "\"contains_attention\":["));
			} else {
				fluree_links.add(getAttentionRelationJson(logger,contained_attention, "\"contains_attention\":"));
			}
			
			fluree_bodies.add(getAttentionJson(logger, contained_attention));
			
		} else {
			//System.out.println("contained_attention.isEmpty()***: "+contained_attention.isEmpty());
		}
		
		//adding emphasys
		if (!contained_emphasys.isEmpty()) {
			if (contained_emphasys.size()>1) {
				fluree_links.add(getEmphasysRelationJson(logger, contained_emphasys, "\"contains_emphasys\":["));
			} else {
				fluree_links.add(getEmphasysRelationJson(logger, contained_emphasys, "\"contains_emphasys\":"));
			}
			fluree_bodies.add(getEmphasysJson(logger, contained_emphasys));
		} else {
			//System.out.println("contained_emphasys.isEmpty()***: "+contained_emphasys.isEmpty());
		}
		
		//adding header1
		if (!contained_header1.isEmpty()) {
			if (contained_header1.size()>1) {
				fluree_links.add(getHeader1RelationJson(logger, contained_header1, "\"contains_header1\":["));
			} else {
				fluree_links.add(getHeader1RelationJson(logger, contained_header1, "\"contains_header1\":"));
			}
			fluree_bodies.add(getHeader1Json(logger, contained_header1));
		} else {
			//System.out.println("contained_header1.isEmpty()***: "+contained_header1.isEmpty());
		}
		
		//adding header2
		if (!contained_header2.isEmpty()) {
			if (contained_header2.size()>1) {
				fluree_links.add(getHeader2RelationJson(logger, contained_header2, "\"contains_header2\":["));
			} else {
				fluree_links.add(getHeader2RelationJson(logger, contained_header2, "\"contains_header2\":"));
			}
			fluree_bodies.add(getHeader2Json(logger, contained_header2));
		} else {
			//System.out.println("contained_header2.isEmpty()***: "+contained_header2.isEmpty());
		}
		
		//adding header3
		if (!contained_header3.isEmpty()) {
			if (contained_header3.size()>1) {
				fluree_links.add(getHeader3RelationJson(logger, contained_header3, "\"contains_header3\":["));
			} else {
				fluree_links.add( getHeader3RelationJson(logger, contained_header3, "\"contains_header3\":"));
			}
			fluree_bodies.add(getHeader3Json(logger, contained_header3));
		} else {
			//System.out.println("contained_header3.isEmpty()***: "+contained_header3.isEmpty());
		}
		
		//adding header4
		if (!contained_header4.isEmpty()) {
			if (contained_header4.size()>1) {
				fluree_links.add(getHeader4RelationJson(logger, contained_header4, "\"contains_header4\":["));
			} else {
				fluree_links.add(getHeader4RelationJson(logger, contained_header4, "\"contains_header4\":"));
			}
			fluree_bodies.add(getHeader4Json(logger, contained_header4));
		} else {
			//System.out.println("contained_header4.isEmpty()***: "+contained_header4.isEmpty());
		}
		
		
		//adding header5
		if (!contained_header5.isEmpty()) {
			if (contained_header5.size()>1) {
				fluree_links.add(getHeader5RelationJson(logger, contained_header5, "\"contains_header5\":["));
			} else {
				fluree_links.add(getHeader5RelationJson(logger, contained_header5, "\"contains_header5\":"));
			}
			fluree_bodies.add(getHeader5Json(logger, contained_header5));
		} else {
			//System.out.println("contained_header5.isEmpty()***: "+contained_header5.isEmpty());
		}
		
		//adding tags relations links
		if (!tags_map.isEmpty()) {

			if (tags_map.size()>1) {
				fluree_links.add(getTagRelationJson(logger, tags_map, "\"contains_tag\":["));
			} else {
				fluree_links.add(getTagRelationJson(logger, tags_map, "\"contains_tag\":"));
			}
			fluree_bodies.add(getTagJson(logger, contained_tags));
		} else {
			//System.out.println("contained_header5.isEmpty()***: "+contained_header5.isEmpty());
		}
		
		
		//System.out.println("fluree_links.size()***: "+fluree_links.size());
		//if fluree array is not larger than 1
		String test_string = null;
		if (fluree_links.isEmpty()) {
			 //System.out.println("final fluree string: "+fluree_string_head +"}]");
			 return fluree_string_head +"}]";
		} else {
			//streaming to generate the header string
			fluree_head_links = fluree_links.stream().map(n -> n.toString()).collect(Collectors.joining(",\n"));
			 //System.out.println("fluree_head_links : "+fluree_head_links );
			 if (fluree_bodies.isEmpty()) {
				return fluree_string_head +fluree_head_links+"}]";
			}
		}
		//end fluree_array.size()==1
		
		//System.out.println("fluree_bodies.size()***: "+fluree_bodies.size());
		//if fluree array is not larger than 1
		if (fluree_bodies.isEmpty()) {
			 //System.out.println("final fluree string: "+fluree_string_head +"}]");
			 return fluree_string_head +fluree_head_links+"}]";
		} else {
			//streaming to generate the header string
			String fluree_bodies_string = fluree_bodies.stream().map(n -> n).collect(Collectors.joining(",\n"));
			String body_result = null;
			//check end of string
		    body_result = fluree_bodies_string.substring(0, fluree_bodies_string.length() -2);
			
			//System.out.println("fluree_bodies_string : "+body_result);
			 //return fluree_string_head +",\n"+fluree_head_links+"},\n"+body_result+"\"}]";//if not tag, issue  "contains_tag"
			 return fluree_string_head +",\n"+fluree_head_links+"},\n"+body_result+"]";
		    }
		    //end fluree_array.size()==1
	    }//fluree_bodies.isEmpty()
	
	
	private String getAttentionRelationJson(Logger logger, List<Attention> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;//"\"contains_attention\"[";
		
		if (ListofObjects.size()>0) {
			//System.out.println("some attention: "+ListofObjects.size());
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
				if (ListofObjects.size()>1) {
					joinedString3=joinedString2+"],";
				} else {
					joinedString3=joinedString2+",";
				}
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("Error extracting attention on: "+ this.name);
				joinedString3="";
			}
		} else {
			joinedString3="";
		}
			
		return joinedString3;
	}
	
	private String getAttentionJson(Logger logger, List<Attention> ListofObjects) {
		String joinedString;
	
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(",\n"));
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}

		return joinedString;
	}

	private String getEmphasysRelationJson(Logger logger, Set<Emphasys> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
				if (ListofObjects.size()>1) {
					joinedString3=joinedString2+"],";
				} else {
					joinedString3=joinedString2+",";
				}
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
		} else {
			joinedString3="";
		}
		
		return joinedString3;
	}
	
	private String getEmphasysJson(Logger logger, Set<Emphasys> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(",\n"));
			
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}

	private String getHeader1RelationJson(Logger logger, Set<Header1> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
				if (ListofObjects.size()>1) {
					joinedString3=joinedString2+"]";
				} else {
					joinedString3=joinedString2;
				}
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}

		} else {
			joinedString3="";
		}
		
		return joinedString3;
	}
	
	private String getHeader1Json(Logger logger, Set<Header1> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(",\n"));
			if (!joinedString.isBlank()) {
				return joinedString;
			}
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}
	
	
	private String getHeader2RelationJson(Logger logger, Set<Header2> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;//"\"contains_attention\"[";
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
				if (ListofObjects.size()>1) {
					joinedString3=joinedString2+"]";
				} else {
					joinedString3=joinedString2;
				}
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
		} else {
			joinedString3="";
		}
		
		return joinedString3;
	}
	
	
	private String getHeader2Json(Logger logger, Set<Header2> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(",\n"));
			if (!joinedString.isBlank()) {
				return joinedString;
			}
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}
	
	private String getHeader3RelationJson(Logger logger, Set<Header3> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
				if (ListofObjects.size()>1) {
					joinedString3=joinedString2+"]";
				} else {
					joinedString3=joinedString2;
				}
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
		} else {
			joinedString3="";
		}
		return joinedString3;
	}
	
	

	private String getHeader3Json(Logger logger, Set<Header3> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(",\n"));
			if (!joinedString.isBlank()) {
				return joinedString;
			}
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}
	
	private String getHeader4RelationJson(Logger logger, Set<Header4> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
				if (ListofObjects.size()>1) {
					joinedString3=joinedString2+"]";
				} else {
					joinedString3=joinedString2;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
		} else {
			joinedString3="";
		}
		return joinedString3;
	}
	
	
	private String getHeader4Json(Logger logger, Set<Header4> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(",\n"));
			if (!joinedString.isBlank()) {
				return joinedString;
			}
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}
	
	private String getHeader5RelationJson(Logger logger, Set<Header5> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
				if (ListofObjects.size()>1) {
					joinedString3=joinedString2+"]";
				} else {
					joinedString3=joinedString2;
				}
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
		} else {
			joinedString3="";
		}
		
		return joinedString3;
	}
	
	private String getHeader5Json(Logger logger, Set<Header5> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(",\n"));
			if (!joinedString.isBlank()) {
				return joinedString;
			}
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}
	
	private String getTagRelationJson(Logger logger, Map <String,String> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.entrySet()
	                     .stream()
	                     .map(e -> e.getValue())
	                     .collect(Collectors.joining(","));
				if (ListofObjects.size()>1) {
					joinedString3=joinedString2+"]";
				} else {
					joinedString3=joinedString2;
				}
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
			
		} else {
			joinedString3="";
		}
				
		return joinedString3;
	}
	
	private String getTagJson(Logger logger, Set<Tag> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(",\n"));
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}//end gettag
	
	
	//get linked notes
	
	private String getNoteRelationJson(Logger logger, List <String> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().collect(Collectors.joining(","));
				if (ListofObjects.size()>1) {
					joinedString3=joinedString2+"]";
				} else {
					joinedString3=joinedString2;
				}
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
			
		} else {
			joinedString3="";
		}
				
		return joinedString3;
	}
	
	
	public void generateHeader1(Logger logger) {
		//System.out.println("entering generateHeader1");
		Pattern h1 = Pattern.compile("(?m)^#\s+(?!#)(.*)");
		Matcher m = h1.matcher(this.content);
		while (m.find()) {
			String[] parts = m.group().split("#");
			String part1 = parts[0]; // 004
			String contains = parts[1]; // 034556
			//System.out.println("contains = parts[1] "+contains);
	        UUID uuid = UUID.randomUUID();
	        String uuidAsString = uuid.toString();
			//generating uuid for every header
		    contained_header1.add(new Header1("Header1$"+uuidAsString,contains));
		}
		if (contained_header1.size()>0) {
			this.setContained_header1(contained_header1);
		}
	}//end generateHeader1()
	
	
	public void generateHeader2(Logger logger) {
		Pattern h2 = Pattern.compile("(?m)^#{2}\s+(?!#)(.*)");
		Matcher m = h2.matcher(this.content);
		while (m.find()) {
			String[] parts = m.group().split("##");
			String part1 = parts[0]; // 004
			String contains = parts[1]; // 034556
			//System.out.println("contains = parts[1] "+contains);
	        UUID uuid = UUID.randomUUID();
	        String uuidAsString = uuid.toString();
			//generating uuid for every header
		    contained_header2.add(new Header2("Header2$"+uuidAsString,contains));
		}
		if (contained_header2.size()>0) {
			this.setContained_header2(contained_header2);
		}
		
	}
	
	public void generateHeader3(Logger logger) {
		Pattern h3 = Pattern.compile("(?m)^#{3}\s+(?!#)(.*)");
		Matcher m = h3.matcher(this.content);
		while (m.find()) {
			String[] parts = m.group().split("###");
			String part1 = parts[0]; // 004
			String contains = parts[1]; // 034556
			//System.out.println("contains = parts[1] "+contains);
	        UUID uuid = UUID.randomUUID();
	        String uuidAsString = uuid.toString();
			//generating uuid for every header
		    contained_header3.add(new Header3("Header3$"+uuidAsString,contains));
		}
		if (contained_header3.size()>0) {
			this.setContained_header3(contained_header3);
		}

	}
	
	public void generateHeader4(Logger logger) {
		Pattern h4 = Pattern.compile("(?m)^#{4}\s+(?!#)(.*)");
		Matcher m = h4.matcher(this.content);
		while (m.find()) {
			String[] parts = m.group().split("####");
			String part1 = parts[0]; // 004
			String contains = parts[1]; // 034556
			//System.out.println("contains = parts[1] "+contains);
	        UUID uuid = UUID.randomUUID();
	        String uuidAsString = uuid.toString();
			//generating uuid for every header
		    contained_header4.add(new Header4("Header4$"+uuidAsString,contains));
		}
		if (contained_header4.size()>0) {
			this.setContained_header4(contained_header4);
		}
	}
	
	
	public void generateHeader5(Logger logger) {
		Pattern h5 = Pattern.compile("(?m)^#{5}\s+(?!#)(.*)");
		Matcher m = h5.matcher(this.content);
		while (m.find()) {
			String[] parts = m.group().split("#####");
			String part1 = parts[0]; // 004
			String contains = parts[1]; // 034556
			//System.out.println("contains = parts[1] "+contains);
	        UUID uuid = UUID.randomUUID();
	        String uuidAsString = uuid.toString();
			//generating uuid for every header
		    contained_header5.add(new Header5("Header5$"+uuidAsString,contains));
		}
		if (contained_header5.size()>0) {
			this.setContained_header5(contained_header5);
		}

	}
	
	public void generateTags(Logger logger, String content_type, String query_url, String transaction_url, String http_method) {

		String consulting_response;
		String transaction_response;
		//contained_tags=null;
		Map<String, String> tags_map = new HashMap<String, String>();
		Pattern pretags = Pattern.compile("#(\\w+)");
		Matcher m = pretags.matcher(this.content);
		while (m.find()) {
				try {
					String http_body = String.format("\"SELECT ?tag WHERE { ?tag fd:Tag/text_content \\\"%s\\\". }\"", m.group(1));
					//System.out.println("tags query: "+http_body);
					consulting_response = HttpURLFlureeDBConnection.
							sendOkHttpClientPost(content_type,query_url,http_method,http_body);
					//System.out.println("consulting_response: "+consulting_response);
					if (consulting_response.equalsIgnoreCase("[]")) {
						System.out.println("tag " +m.group(1)+ " not found");
						 logger.info("tag " +m.group(1)+ " not found");
						 //creating the tag object in db
							final String an_json_tag = "[{\"_id\""+":"+"\""+ "Tag$"+m.group(1)+"\","
									  +"\"text_content\""+":"+"\""+m.group(1)+"\"}]\n";
							 //request to create the tag
							 transaction_response = HttpURLFlureeDBConnection.
										sendOkHttpClientPost(content_type,transaction_url,http_method,an_json_tag);
							  //converting to json to get fluree id
							  JSONObject tag_json = new JSONObject(transaction_response);
							  //System.out.println("transaction_response on tag: "+transaction_response);
							 // System.out.println("Long.toString(tag_json.getJSONObject(\"tempids\").getLong(\"Tag$\"+m.group(1))): "+Long.toString(tag_json.getJSONObject("tempids").getLong("Tag$"+m.group(1))));
							  try {
								  Long fluree_id = tag_json.getJSONObject("tempids").getLong("Tag$"+m.group(1));
								  tags_map.put(m.group(1), Long.toString(fluree_id));
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("error while adding tag "+m.group(1)+" to map");
							}
							  
					} else {
						//System.out.println("found tag "+m.group(1));
						try {
							String consulting_left_brackets =consulting_response.replace("[", "");
				        	//System.out.println("consulting_left_brackets: "+consulting_left_brackets);
				        	String consulting_right_brackets =consulting_left_brackets.replace("]", "");
				        	//System.out.println("consulting_right_brackets: "+consulting_right_brackets);
				        	//after removing brackets, then try to splitting, if any
				            String[] result = consulting_right_brackets.split(",");
				            //System.out.println("splitted result "+result.length);
				            if (result.length > 1) {
				            	logger.error("error while generating tag: "+m.group(1));
							} else {
								//System.out.println("fluree id: "+result[0]);
								tags_map.put(m.group(1), result[0]);
							}

						} catch (Exception e) {
							logger.error("error while removing [ from string "+consulting_response);
						}
					}

				} catch (Exception e) {
		            logger.error("error while creatintg tag "+e.toString());
				}//end of first try catch
		}//end while of m
		
		//adding tags to note
		if (tags_map.size()>0) {
			this.setTags_map(tags_map);
		}
		
	}//end generatetags()
	
	public void generateNoteLinks(Logger logger, String content_type, String query_url, String http_method) {
		String consulting_response;
		Pattern note_link = Pattern.compile("\\[\\[(.+?)\\]\\]");
		Matcher m = note_link.matcher(this.content);
		while (m.find()) {
			//System.out.println(" notes linked: "+m.group(1).replaceAll("\\s+","_")); 
			//querying if to be linked note exists in db.
			try {
				String http_body = String.format("\"SELECT ?note WHERE { ?note fd:Note/note_name \\\"%s\\\". }\"", m.group(1).replaceAll("\\s+","_"));
				//System.out.println("http body for note: "+http_body);
				consulting_response = HttpURLFlureeDBConnection.
						sendOkHttpClientPost(content_type,query_url,http_method,http_body);
				//System.out.println("note response: "+consulting_response);

				//second try
				try {
					Pattern pattern1 = Pattern.compile("\\[(\\d+)\\]");
			        Matcher matcher1 = pattern1.matcher(consulting_response);
			        if (matcher1.find()) {
						 //System.out.println("note  found "+matcher1.group(1));
						 //we found the tag, thus we get its fluree id, and set exist in fluree to true
						 linked_notes.add(matcher1.group(1));
			        	
					} else {
						 System.out.println("note not found");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}//end second try
				
			} catch (Exception e) {
				// TODO: handle exception
			}//end first try catch
		}//end while m.find
	}//end generateNoteLinks()
	
	//public Set<Header6> getHeader6(String aNote) {
	//	Pattern h3 = Pattern.compile("(?m)^#{3}(?!#)(.*)");
	//	return null;
	//}
	
	
	public String getFullJSON(Logger aLogger) {
		return "["+this.getPartialJSON(aLogger);
	}
	
	
	
	
	
}//end of note