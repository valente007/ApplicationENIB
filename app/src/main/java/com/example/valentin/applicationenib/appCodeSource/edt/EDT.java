package com.example.valentin.applicationenib.appCodeSource.edt;


import com.example.valentin.applicationenib.appCodeSource.events.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import com.sun.org.apache.xml.internal.serialize.OutputFormat;
//import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

@objid ("a8e886e0-e063-4f7c-8f13-588663ffb8cf")
public class EDT {
	
    @objid ("58bd4622-3634-4175-8bb6-408fad620c7d")
    protected List<SchoolClass> classes = new ArrayList<SchoolClass> ();
    
	public List<SchoolClass> getClasses() {
		return classes;
	}
	
	public SchoolClass getClass(int uID) {
		SchoolClass schoolClass = new SchoolClass(null, uID, false, null, null, null, null, null,null);
		for(SchoolClass i : this.classes){
			if(i.getUID() == uID){
				schoolClass = i;
				break;
			}
		}
		return schoolClass;
	}

	public void addClasses(SchoolClass schoolClass) {
		this.classes.add(schoolClass);
	}

    @objid ("cc75acaf-ae39-4b7c-8366-4b5e53a17639")
    public void showEDT() {
    }
    
    public void makeEDT(Calendar calendar){
    	
    	/*
         * Read the ical document and make all the classes with theses information
         */
    	for (java.util.Iterator<CalendarComponent> i = calendar.getComponents().iterator(); i.hasNext();) {
    		SchoolClass schoolClass = new SchoolClass(null, 0, false, null, null, null, null, null, null);
    		schoolClass.addHW("");
    		Component component = (Component) i.next();
    		for (java.util.Iterator<Property> j = component.getProperties().iterator(); j.hasNext();) {
    	        Property property = (Property) j.next();
    	        switch(property.getName()){
    	        case "SUMMARY":
    	        	schoolClass.setSummary(property.getValue().replaceAll("%20"," "));
    	        	break;
    	        case "UID":
    	        	schoolClass.setUID(Integer.parseInt(property.getValue()));
    	        	break;
    	        case "DESCRIPTION":
    	        	schoolClass.setDescription(property.getValue().replaceAll("%20"," "));
    	        	break;
    	        case "ATTENDEE":
    	        	char ch = property.getValue().charAt(4);
    	        	if(Character.isDigit(ch)){
    	        		schoolClass.setGroup(property.getValue().substring(3,property.getValue().length()).replaceAll("%20"," "));
    	        	}
    	        	else{
    	        		String teacher = property.getValue().substring(3,property.getValue().length()).replaceAll("%20"," ");
    	        		schoolClass.setTeacher(teacher);
    	        	}
    	        	break;
    	        case "DTSTART":
    	        	int dayS = Integer.parseInt(property.getValue().substring(6,8));
    	        	int monthS = Integer.parseInt(property.getValue().substring(4,6));
    	        	int yearS = Integer.parseInt(property.getValue().substring(0,4));
    	        	int hourS = Integer.parseInt(property.getValue().substring(9,11));
    	        	int minS = Integer.parseInt(property.getValue().substring(11,13));
    	        	Date dateStart = new Date(dayS,monthS,yearS,hourS,minS);
    	        	schoolClass.setDtstart(dateStart);
    	        	break;
    	        case "DTEND":
    	        	int dayE = Integer.parseInt(property.getValue().substring(6,8));
    	        	int monthE = Integer.parseInt(property.getValue().substring(4,6));
    	        	int yearE = Integer.parseInt(property.getValue().substring(0,4));
    	        	int hourE = Integer.parseInt(property.getValue().substring(9,11));
    	        	int minE = Integer.parseInt(property.getValue().substring(11,13));
    	        	Date dateEnd = new Date(dayE,monthE,yearE,hourE,minE);
    	        	schoolClass.setDtend(dateEnd);
    	        	break;
					case "LOCATION":
						schoolClass.setLocation(property.getValue());
						break;
    	        }    	        
    	    }
    		this.addClasses(schoolClass);
    	}
    }
    
    /*
     * Update the XML with subject class information, when ds or homework change
     */
    public void edtData(SchoolClass sc,String edtDataPath){
    	
    	/*
         * Creating a new document
         */
    	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
         * Reading the XML
         */
    	Document document = null;
		try {
			document = builder.parse(new File(edtDataPath));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
         * Looking for the subject to be in the XML to know if it has homework or DS setting true
         */
    	final Element root = document.getDocumentElement();
    	
    	final NodeList rootNode = root.getChildNodes();
    	final int rootNodeNbr = rootNode.getLength();
    	boolean here = false;
    	for (int i = 0; i<rootNodeNbr; i++) {
    		if(rootNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
    			final Element subject = (Element) rootNode.item(i);
    			if(Integer.parseInt(subject.getAttribute("uID")) == sc.getUID()){
    				here=true;
    				final Element homework = (Element) subject.getElementsByTagName("homework").item(0);
    				final Element ds = (Element) subject.getElementsByTagName("ds").item(0);
    				homework.setAttribute("homework", sc.getHomework().getDescription());
    				ds.setAttribute("ds",Boolean.toString(sc.isEnableDS()));
    				break;
    			}
    		}
    	}
    	/*
         * If the subject is not in the XML we create a new element Subject and get all the information 
         * from the subject class
         */
		if(!here){
			final Element sub = document.createElement("subject");
			root.appendChild(sub);
			
			sub.setAttribute("uID", Integer.toString(sc.getUID()));
			//root.appendChild(sub);
			
			Element homework = (Element) document.createElement("homework");
			if(sc.getHomework().getDescription() != null){
				homework.setAttribute("homework", sc.getHomework().getDescription());	
			}
			sub.appendChild(homework);
			
			Element ds = (Element) document.createElement("ds");
			ds.setAttribute("ds",Boolean.toString(sc.isEnableDS()));
			sub.appendChild(ds);
		}
		
    	EDT.saveEdtData(edtDataPath,document);
    }
    
    /*
     * Saving new information in the XML
     */
    static void saveEdtData(String  edtDataPath,Document doc){
    	Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Result output = new StreamResult(new File(edtDataPath));
    	Source input = new DOMSource(doc);

    	try {
			transformer.transform(input, output);
		} catch (TransformerException e) {
			System.out.println("Can't save");
			e.printStackTrace();
		}
    }
    
    /*
     * See the XML scheme
     */

	/*
    public void showEDTData(Document doc){
    	XMLSerializer ser = new XMLSerializer(System.out,new OutputFormat("xml", "UTF-8", true));
		try {
			ser.serialize(doc);
		} catch (IOException e) {
			System.out.println("Can't serialize");
			e.printStackTrace();
		}    	
    }
	*/

}
