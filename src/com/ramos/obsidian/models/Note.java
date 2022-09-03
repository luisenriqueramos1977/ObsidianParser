

/*
 * Copyright [2022] [Luis Enrique Ramos Garc�a].
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.json.JSONObject;
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
	private static LocalDate created_on;
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


	
	public Note(String name, String content, LocalDate created_on, String creator,String located_in) {
		// TODO Auto-generated constructor stub
		this.name =name;
		this.content= content;
		this.created_on = created_on;
		this.creator = creator;
		this.located_in = located_in;
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
	public static LocalDate getCreated_on() {
		return created_on;
	}

	/**
	 * @param created_on the created_on to set
	 */
	public static void setCreated_on(LocalDate created_on) {
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


	
	public String getPartialJSON() {
		// TODO Auto-generated method stub
		final String a_json = "\n [{\"_id: \""+ this.getName()+"\",\n"+
							  "\"textContent\""+":"+"\""+this.getContent()+"\",\n"+
							  "\"name\""+":"+"\""+this.getName()+"\",\n"+
							  "\"created_on\""+":"+"\""+this.getCreated_on()+"\",\n"+
							  getAttentionRelationJson(contained_attention, "\"contained_attention\":[")+
							  getEmphasysRelationJson(contained_emphasys, "\"contained_emphasys\":[")+ 
							  getHeader1RelationJson(contained_header1, "\"contained_header1\":[")+
							  getHeader2RelationJson(contained_header2, "\"contained_header2\":[")+
							  getHeader3RelationJson(contained_header3, "\"contained_header3\":[")+
							  getHeader4RelationJson(contained_header4, "\"contained_header4\":[")+
							  getHeader5RelationJson(contained_header5, "\"contained_header5\":[")+
							  getTagRelationJson(contained_tags, "\"contained_tags\":[")+
							  "},\n"+
							  getAttentionJson(contained_attention)+
							  getEmphasysJson(contained_emphasys)+
							  getHeader1Json(contained_header1)+
							  getHeader2Json(contained_header2)+
							  getHeader3Json(contained_header3)+
							  getHeader4Json(contained_header4)+
							  getHeader5Json(contained_header5)+
							  getTagJson(contained_tags)+
							  "]";
		return a_json;
		
	}
	
	
	private String getAttentionRelationJson(List<Attention> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;//"\"contains_attention\"[";
		
		if (ListofObjects.size()>0) {
			System.out.println("some attention: "+ListofObjects.size());
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
			joinedString3=joinedString2+"],\n";
		} else {
			joinedString3="";
		}
			
		return joinedString3;
	}
	
	private String getAttentionJson(List<Attention> ListofObjects) {
		String joinedString;
	
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(","));
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}

		return joinedString;
	}

	private String getEmphasysRelationJson(Set<Emphasys> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
		} else {
			joinedString3="";
		}
		
		return joinedString3;
	}
	
	private String getEmphasysJson(Set<Emphasys> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(","));
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}

	private String getHeader1RelationJson(Set<Header1> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
			joinedString3=joinedString2+"],\n";
		} else {
			joinedString3="";
		}
		
		return joinedString3;
	}
	
	private String getHeader1Json(Set<Header1> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(","));
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}
	
	
	private String getHeader2RelationJson(Set<Header2> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;//"\"contains_attention\"[";
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
			joinedString3=joinedString2+"],\n";
		} else {
			joinedString3="";
		}
		
		return joinedString3;
	}
	
	
	private String getHeader2Json(Set<Header2> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(","));
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}
	
	private String getHeader3RelationJson(Set<Header3> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
			joinedString3=joinedString2+"],\n";
		} else {
			joinedString3="";
		}
		return joinedString3;
	}
	
	

	private String getHeader3Json(Set<Header3> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(","));
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}
	
	private String getHeader4RelationJson(Set<Header4> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
			joinedString3=joinedString2+"],\n";
		} else {
			joinedString3="";
		}
		return joinedString3;
	}
	
	
	private String getHeader4Json(Set<Header4> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(","));
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}
	
	private String getHeader5RelationJson(Set<Header5> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
			joinedString3=joinedString2+"],\n";
		} else {
			joinedString3="";
		}
		
		return joinedString3;
	}
	
	private String getHeader5Json(Set<Header5> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(","));
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}
	
	private String getTagRelationJson(Set<Tag> ListofObjects, String containment) {
		String joinedString1= null;
		String joinedString2= null;
		String joinedString3 = null;
		joinedString1 = containment;
		
		if (ListofObjects.size()>0) {
			try {
				joinedString2 = joinedString1+ListofObjects.stream().map(n -> n.getName()).collect(Collectors.joining("\", \"", "\"", "\""));
			} catch (Exception e) {
				// TODO: handle exception
				joinedString3="";
			}
			
			joinedString3=joinedString2+"],\n";
		} else {
			joinedString3="";
		}
		return joinedString3;
	}
	
	private String getTagJson(Set<Tag> ListofObjects) {
		String joinedString;
		try {
			joinedString = ListofObjects.stream().map(n -> n.getPartialJSON()).collect(Collectors.joining(","));
		} catch (Exception e) {
			// TODO: handle exception
			joinedString="";
		}
		return joinedString;
	}//end gettag
	
	
	public void generateHeader1() {
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
		    contained_header1.add(new Header1("header1$"+uuidAsString,contains));
		}
		if (contained_header1.size()>0) {
			this.setContained_header1(contained_header1);
		}
	}//end generateHeader1()
	
	
	public void generateHeader2() {
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
		    contained_header2.add(new Header2("header2$"+uuidAsString,contains));
		}
		if (contained_header2.size()>0) {
			this.setContained_header2(contained_header2);
		}
		
	}
	
	public void generateHeader3() {
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
		    contained_header3.add(new Header3("header3$"+uuidAsString,contains));
		}
		if (contained_header3.size()>0) {
			this.setContained_header3(contained_header3);
		}

	}
	
	public void generateHeader4() {
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
		    contained_header4.add(new Header4("header4$"+uuidAsString,contains));
		}
		if (contained_header4.size()>0) {
			this.setContained_header4(contained_header4);
		}
	}
	
	
	public void generateHeader5() {
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
		    contained_header5.add(new Header5("header5$"+uuidAsString,contains));
		}
		if (contained_header5.size()>0) {
			this.setContained_header5(contained_header5);
		}

	}
	
	public void generateTags(String content_type, String target_url, String http_method) {
		//Pattern pretags = Pattern.compile("(?m)^#(?!#)(.*)");
		String consulting_response;
		Pattern pretags = Pattern.compile("#(\\w+)");
		Matcher m = pretags.matcher(this.content);
		while (m.find()) {
			//System.out.println(" found tags: "+m.group(1)); 
			//creating the http connection
			try {
				String http_body = String.format("\"SELECT ?tag WHERE { ?tag fd:Tag/textContent \\\"%s\\\". }\"", m.group(1));
				//System.out.println("http body: "+http_body);
				consulting_response = HttpURLFlureeDBConnection.
						sendOkHttpClientPost(content_type,target_url,http_method,http_body);
				
				//System.out.println("the response: "+consulting_response);
				//generating json object for processing
				 try {
					 	Pattern pattern1 = Pattern.compile("\\[\\d+\\]");
				        Matcher matcher1 = pattern1.matcher(consulting_response);
				        //checking matches
				        if (matcher1.find()) {
							 System.out.println("something found");
						} else {
							 System.out.println("nothing found");
							 //creating the tag object in db
								final String an_json_tag = "{\"_id\""+":"+"\""+ m.group(1)+"\","
										  +"\"textContent\""+":"+"\""+m.group(1)+"\","
										  +"\"name\""+":"+"\""+m.group(1)+"\""+
										  "}\n";
								 System.out.println("tag to be created: "+an_json_tag);

								
							 
							 //Tag new_tag = new Tag(http_body, http_body, http_body);
							 
						}
				         while (matcher1.find()) {
				             //System.out.println("the fluree key: "+ matcher1.group(0) );
				             //parsing the string
				             String fluree_id = matcher1.group(0).substring(matcher1.group(0).indexOf("[")+1, matcher1.group(0).indexOf("]"));
				             //System.out.println("fluree_id: "+fluree_id);
						}
			        } catch (JSONException err) {
			            System.out.println("Exception : "+err.toString());
			        }
			} catch (Exception e) {
			    e.printStackTrace();
			}

		}
	}//end generateHeader1()
	
	public void generateNoteLinks() {
		Pattern note_link = Pattern.compile("\\[\\[(.+?)\\]\\]");
		Matcher m = note_link.matcher(this.content);
		while (m.find()) {
			System.out.println(" notes linked: "+m.group(1)); 
		}
	}//end generateNoteLinks()
	
	//public Set<Header6> getHeader6(String aNote) {
	//	Pattern h3 = Pattern.compile("(?m)^#{3}(?!#)(.*)");
	//	return null;
	//}
	
	
	
	
	
	
	
	
}//end of note