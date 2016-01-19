package Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
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

import Notes.Bloc;
import Notes.Semester;
import Notes.Subject;
import Profil.Student;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

import EDT.EDT;
import Events.Date;
import Events.Event;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * Application is where you have everything that you need for using the application without using other Classes 
 * 
 * Application is able to:
 * 			- Initialize semesters
 * 			- Initialize a student
 * 			- Initialize news
 * 			- Initialize events
 * 			- Activate/Deactivate DS on a subject
 * 			- Add/save/get homework on a event
 * 			- add/save/get marks on a subject
 * 			- Connect a user
 * 			- Initialize/compare/update EDT
 * 			
 * 		
 */

@objid ("f09965d2-63f5-42e8-9b5d-1ecb9c3c6ecb")
public class Application {
	
    @objid ("33e2389b-36e4-4826-b085-7a485dd71b2a")
    protected List<Semester> semesters = new ArrayList<Semester> ();
    protected Http http = new Http();
    protected Student student;
    protected List<News> news = new ArrayList<News> ();
    protected List<Event> events = new ArrayList<Event> ();
    protected EDT edt = new EDT();
    
	public Student getStudent() {
		return student;
	}

	public List<Semester> getSemesters() {
		return semesters;
	}

	public List<News> getNews() {
		return news;
	}

	public List<Event> getEvents() {
		return events;
	}
    
	/*			
     * Getting news		
     */
    @objid ("1a14b57b-2669-4dce-839a-975cbf385721")
    public void wantsNews() {
    	Home home = new Home();
    	this.news = home.getNews();
    }
    
    /*			
     * Getting events		
     */
    @objid ("8e1b86d9-bfe3-485e-9b60-85f8d95667ae")
    public void wantsEvents() throws Exception {
    	this.http.getSendEvent(this.events);
    }
    
    /*			
     * Getting user's information		
     */
    @objid ("f18ac479-1cef-435d-9037-000b06b7afa4")
    public void wantsUser() {
			try {
				this.http.getSendUser(this.student);
			} catch (Exception e) {
				System.out.println("Can't get Student");
				e.printStackTrace();
			}
			//this.student.showUser();
    }
    
    /*			
     * Connecting user		
     */
    @objid ("e2ab4b1f-5bc1-4b89-9e5e-765c6a5a2c0e")
    public void wantsConnect(String login, String password) throws IOException {

    	this.student = new Student(login);
    	if(!this.http.isConnected(student.getLogin())){
    		this.http.initCookie("http://enib.net/accounts/login");
    		this.http.setParams(login, password);
    		this.http.postInit("http://enib.net/accounts/login");
    	}
    	this.wantsUser();
    }

    @objid ("70d63768-3c71-4a4a-aac4-167c6d1c5642")
    public void wantsModifyUser() {
    }
    
    /*			
     * Getting a semester		
     */
    @objid ("8be7c990-3cb5-4a09-a3f2-bace9ce0efd2")
    public Semester getSemester(int number) {
        Semester semester = new Semester(0,new ArrayList<Bloc> ());
        for (Semester s : this.semesters){
            if(s.getSemesterNbr() == number){
                semester = s;
                break;
            }
        }
        return semester;
    }
    
    /*			
     * Add subect's homework		
     */
    @objid ("598549a8-b97d-404b-a7aa-9857622c2dba")
    public void addHW(int uID,String description,EDT edt) {
    	edt.getClass(uID).addHW(description);
    	edt.edtData(edt.getClass(uID));
    }
    
    /*			
     * activate subject's DS		
     */
    @objid ("f4e62567-5816-49cd-b91d-4431482245d3")
    public void activateDS(int uID, EDT edt) {
    	edt.getClass(uID).enableDS();
    	edt.edtData(edt.getClass(uID));
    }
    
    /*			
     * Deactivate subject's DS	
     */
    public void desactivateDS(int uID, EDT edt) {
    	edt.getClass(uID).disableDS();
    	edt.edtData(edt.getClass(uID));
    }
    
    /*
     * Initialization of semesters and blocs without subjects
     */
    @objid ("25d0ada6-27d3-46e5-a098-b2a38332f28d")
    public void initSemesters() {
    	int semestersN [] = {1,2,3,4,5,6,11,22,51,52,53,61,62,63};
        String blocsN [] = {"A","B","C","D","E","F"};
        for (int i : semestersN){
        	this.semesters.add(new Semester(i, new ArrayList<Bloc> ()));
        	for (String j : blocsN){
        		Bloc bloc = new Bloc(j);
        		this.getSemester(i).addBloc(bloc);
            }
        }
        /*
         * Putting subjects in blocs
         */
        initXML("subjects.xml");
    }
    
    /*			
     * Add mark to a subject and update averages		
     */
    public void addNote(int nbrSemester,String bloc,String subject,String categorie,double mark){
    	this.getSemester(nbrSemester).getBloc(bloc).getSubject(subject).addNote(mark, categorie); 
    	this.getSemester(nbrSemester).getBloc(bloc).calculateAverage();
    	this.getSemester(nbrSemester).calculateAverage();
    	this.saveNote(nbrSemester, subject, categorie, mark);
    }
    
    /*
     * Initialization of subjects with the xml document "subjects.xml" (need to be created before running the app)
     */
    @objid ("5ae4ffbc-d97f-403b-b4cc-f23276f7c01e")
    public void initXML(String file) {
    	
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document= builder.parse(new File(file));
            final Element root = document.getDocumentElement();
        
            /*
             * subjects getting
             */
            final NodeList rootNode = root.getChildNodes();
            final int rootNodeNbr = rootNode.getLength();
                    
            for (int i = 0; i<rootNodeNbr; i++) {
                if(rootNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element sub = (Element) rootNode.item(i);
        
                    /*
                     * proprietes getting
                     */
                    final Element subjectName = (Element) sub.getElementsByTagName("Name").item(0);
                    final Element subjectSemesterNbr = (Element) sub.getElementsByTagName("SemesterNumber").item(0);
                    final Element subjectBloc = (Element) sub.getElementsByTagName("Bloc").item(0);
                    final Element subjectCoeff = (Element) sub.getElementsByTagName("Coeff").item(0);
                    final Element subjectCoeffDS = (Element) sub.getElementsByTagName("CoeffDS").item(0);
                    final Element subjectCoeffTD = (Element) sub.getElementsByTagName("CoeffTD").item(0);
                    final Element subjectCoeffTP = (Element) sub.getElementsByTagName("CoeffTp").item(0);
                    final Element subjectNoteDS = (Element) sub.getElementsByTagName("noteDS").item(0);
                    
                    /*
                     * Cast
                     */
                    String name = subjectName.getTextContent();
                    int semesterNbr = Integer.parseInt(subjectSemesterNbr.getTextContent());
                    String bloc = subjectBloc.getTextContent();
                    int coeff = Integer.parseInt(subjectCoeff.getTextContent());
                    int coeffTD = Integer.parseInt(subjectCoeffTD.getTextContent());
                    int coeffTP = Integer.parseInt(subjectCoeffTP.getTextContent());
                    int coeffDS = Integer.parseInt(subjectCoeffDS.getTextContent());
                    Subject subject = new Subject(name,coeff,coeffTP,coeffTD,coeffDS);
                    
                    if(!subjectNoteDS.getTextContent().isEmpty()){
                    	double noteDS = Double.parseDouble(subjectNoteDS.getTextContent());
                    	subject.addNote(noteDS, "ds");
                    }
                    
                    /*
                     * Get and set TD marks
                     */
                    final NodeList notesTD = sub.getElementsByTagName("noteTD");
                    final int nbNotesTD = notesTD.getLength();
                    					
                    for(int j = 0; j<nbNotesTD; j++) {
                    	if(!notesTD.item(j).getTextContent().isEmpty()){
                    		double note = Double.parseDouble(notesTD.item(j).getTextContent());
                    		subject.addNote(note, "td");
                    	}
                    }
                    
                    /*
                     * Get and set TP marks
                     */
                    final NodeList notesTP = sub.getElementsByTagName("noteTP");
                    final int nbNotesTP = notesTP.getLength();
                    					
                    for(int j = 0; j<nbNotesTP; j++) {
                    	if(!notesTP.item(j).getTextContent().isEmpty()){
                    		double note = Double.parseDouble(notesTP.item(j).getTextContent());
                    		subject.addNote(note, "tp");
                    	}
                    }
                    /*			
                     * Add subject and update averages		
                     */
                   this.getSemester(semesterNbr).getBloc(bloc).addSubject(subject);
               	   this.getSemester(semesterNbr).calculateAverage();
                    }              
                }
        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Initialization of homework and DS setting true from "edtDate.xml"
     */    
    public EDT initXMLHW(String file,EDT edt) {
    	
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document= builder.parse(new File(file));
            final Element root = document.getDocumentElement();
        
            /*
             * subjects getting
             */
            final NodeList rootNode = root.getChildNodes();
            final int rootNodeNbr = rootNode.getLength();
        
            for (int i = 0; i<rootNodeNbr; i++) {
                if(rootNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element sub = (Element) rootNode.item(i);
        
                    /*
                     * Properties getting
                     */
                    final Element subjectHw = (Element) sub.getElementsByTagName("homework").item(0);
                    final Element subjectDS = (Element) sub.getElementsByTagName("ds").item(0);
                    
                    /*
                     * Cast
                     */
                    String hw = subjectHw.getAttribute("homework");
                    int uID = Integer.parseInt(sub.getAttribute("uID"));
                    
                    edt.getClass(uID).addHW(hw);
                    switch(subjectDS.getAttribute("ds")){
                    case "true":
                    	edt.getClass(uID).enableDS();
                    	break;
                    case "false":
                    	edt.getClass(uID).disableDS();
                    	break;
                    }
                    }              
                }
        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
        return edt;
    }
    
    /*
     * Save mark for the next use
     */    
    public void saveNote(int nbrSemester,String subject,String categorie,double mark){
    	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document= builder.parse(new File("subjects.xml"));
            final Element root = document.getDocumentElement();
        
            /*
             * subjects getting
             */
            final NodeList rootNode = root.getChildNodes();
            final int rootNodeNbr = rootNode.getLength();
                    
            for (int i = 0; i<rootNodeNbr; i++) {
                if(rootNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element sub = (Element) rootNode.item(i);
        
                    /*
                     * Properties getting
                     */
                    final Element subjectName = (Element) sub.getElementsByTagName("Name").item(0);
                    final Element subjectSemesterNbr = (Element) sub.getElementsByTagName("SemesterNumber").item(0);
                    
                    /*
                     * Cast
                     */
                    String name = subjectName.getTextContent();
                    int semesterNbr = Integer.parseInt(subjectSemesterNbr.getTextContent());
                    
                    /*
                     * Save mark of the subject from a semester
                     */
                    if(name.equals(subject) && semesterNbr == nbrSemester){
                    	switch(categorie){
                    	case "ds":
                    		final Element noteDS = (Element) sub.getElementsByTagName("noteDS").item(0);
                    		noteDS.setTextContent(Double.toString(mark));
                    		break;
                    	case "td":
                    		final Element notesTD = (Element) sub.getElementsByTagName("notesTD").item(0);
                    		final Element noteTD = document.createElement("noteTD");
                    		noteTD.setTextContent(Double.toString(mark));
                    		notesTD.appendChild(noteTD);
                    		break;
                    	case "tp":
                    		final Element notesTP = (Element) sub.getElementsByTagName("notesTP").item(0);
                    		final Element noteTP = document.createElement("noteTP");
                    		noteTP.setTextContent(Double.toString(mark));
                    		notesTP.appendChild(noteTP);
                    		break;
                    	}
                    }
                    }              
                }
            Application.saveNoteData("subjects.xml", document);
        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }  	
    }
    
    /*
     * Save marks in "subjects.xml"
     */
    static void saveNoteData(String fichier,Document doc){
    	Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
    	Result output = new StreamResult(new File(fichier));
    	Source input = new DOMSource(doc);

    	try {
			transformer.transform(input, output);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
    }
    
    /*
     * Get the schedule from edt.enib.fr with the user's login
     */
    @objid ("e26af8fd-279c-48ba-89fa-d33ddfc84fd7")
    public void wantsEDT(String username) {

    	String pass = "";
    	BufferedReader in = null;
    	Calendar calendar = new Calendar();
  
    	/*
         * Encode the login for password
         */
		try {
			pass = Base64.getEncoder().encodeToString(username.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("Wrong encoding");
			e.printStackTrace();
		}		
		/*
         * making the URL
         */
    	String userUrlString = String.format("http://edt.enib.fr/ics.php?username="+"%s&pass="+"'%s'",username, pass);
    	URL userCalendar = null;
		try {
			userCalendar = new URL(userUrlString);
		} catch (MalformedURLException e1) {
			System.out.println("Malformed URL");
			e1.printStackTrace();
		}
		
		
		/*
         * read the ics
         */
    	try {
			in = new BufferedReader(new InputStreamReader(userCalendar.openStream(),"UTF-8"));
		} catch (IOException e1) {
			System.out.println("Can't read the EDT from edt.enib.fr");
			e1.printStackTrace();
		}
    	/*
         * ics adjusting
         */
    	StringBuilder sb = new StringBuilder();
    	String line;
		try {
			while ((line = in.readLine()) != null) {
				sb.append(line+"\n");
			}
		} catch (IOException e) {
			System.out.println("Can't make String");
			e.printStackTrace();
		}
		sb = new StringBuilder(sb.toString().replaceAll(":MAILTO:edt-noreply@enib.fr",""));
		sb = new StringBuilder(sb.toString().replaceAll(" ","%20"));    	
    	
    	/*
         * New Calendar ical
         */
    	CalendarBuilder builder = new CalendarBuilder();
    	StringReader sin = new StringReader(sb.toString());
    	try {
			calendar = builder.build(sin);
		} catch (IOException e) {
			System.out.println("Can't make String");
			e.printStackTrace();
		} catch (ParserException e) {
			System.out.println("Can't make String");
			e.printStackTrace();
		}
    	sin.close();
    	/*
         * Adding properties
         */
    	calendar.getProperties().add(new ProdId("-//'Khaaaaaa'//Appplication ENIB Beta 1.0//FR"));
    	calendar.getProperties().add(Version.VERSION_2_0);
    	calendar.getProperties().add(CalScale.GREGORIAN);
    	
    	this.updateEDT(calendar);    	
    	this.edt.makeEDT(calendar);
    	this.edt = this.initXMLHW("edtData.xml",edt);
    }
    
    /*
     * Compare old and new schedule, so we can alert the user about something different from last use
     */
    public void compareEDT(String event, String old, String newSetting, String type){
    	event = event.replaceAll("%20"," ");
    	if(old.equals(newSetting)){
    		return;
    	}
    	else if(event !="") {
    		switch (type){
    		case "time":
    			Date oldDate = this.toDate(old);
    			Date newDate = this.toDate(newSetting);
    			System.out.println("L'evenement "+event+" a ete deplace du "+oldDate.getDay()+"/"+oldDate.getMonth()+"/"+oldDate.getYear()+" au "+
    								newDate.getDay()+"/"+
    								newDate.getMonth()+"/"+
    								newDate.getYear());
    			break;
    		case "location":
    			System.out.println("L'evenement "+event+" a ete deplace de la salle "+old+" a la salle "+newSetting);
    		}
    		return;
    	}
    }
    
    /*
     * Compare old and new uIDs, so we can alert the user about a deleted event from last use
     */
    public void compareuID(List<String> oldList,List<String> newList,Calendar userCal){
    	String subject = "";
		Date start = null;
    	for(String olds : oldList){
    		if(!newList.contains(olds)){
    			for (java.util.Iterator<CalendarComponent> iUser = userCal.getComponents().iterator(); iUser.hasNext();) {
	        		Component componentUser = (Component) iUser.next();
	        		for (java.util.Iterator<Property> jUser = componentUser.getProperties().iterator(); jUser.hasNext();) {
	        	        Property propertyUser = (Property) jUser.next();
	        	        if(propertyUser.getName() == "UID"){
	        	        	if(propertyUser.getValue().equals(olds)){
	        	        		for (java.util.Iterator<Property> kUser = componentUser.getProperties().iterator(); kUser.hasNext();) {
	        	        	        Property propertykUser = (Property) kUser.next();
	        	        	        switch(propertykUser.getName()){
		        		        		case "SUMMARY":
		        		        	      	subject = propertykUser.getValue();
		        		        	       	break;
		        		        	    case "DTSTART":
		        		        	    	start = this.toDate(propertykUser.getValue());
		        		        	        break;
	        		        	        }
	        	        		}
	        	        		break;
	        	        	}
	        	        }
	        		}
	        		
    			}
    			System.out.println("L'evenement "+subject.replaceAll("%20", " ")+" du "+
    			start.getDay()+"/"+start.getMonth()+"/"+start.getYear()+" à "+start.getHour()+":"+start.getMinutes()+" a ete supprime");
    		}
    	}
    }
    
    public void updateEDT (Calendar calendar){
    	
    	List<String> listOldEdtuID = new ArrayList<String>();
		List<String> listNewEdtuID = new ArrayList<String>();
    	
    	String oldStart = "";
    	String oldLocation = "";
    	String event = "";
    	
    	FileInputStream fin = null;
		try {
			fin = new FileInputStream("userCal.ics");
			
			/*
	         * If userCal doesn't exist, we create it.
	         */
		} catch (FileNotFoundException e) {
			this.saveEDT(calendar);
			return;
		}
		
		/*
         * Compare userCal.ics with the calendar from edt.enib.fr
         */
		Calendar userCal = null;
    	CalendarBuilder builder = new CalendarBuilder();
    	try {
			userCal = builder.build(fin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/*
         * Iterating over the userCal for uIDs
         */
    	for (java.util.Iterator<CalendarComponent> iUser = userCal.getComponents().iterator(); iUser.hasNext();) {
    		Component componentUser = (Component) iUser.next();
    		for (java.util.Iterator<Property> jUser = componentUser.getProperties().iterator(); jUser.hasNext();) {
    	        Property propertyUser = (Property) jUser.next();
    	        if(propertyUser.getName() == "UID"){
    	        	listOldEdtuID.add(propertyUser.getValue());
    	        }
    		}
    	}
    	
    	/*
         * Iterating over the calendar from edt.enib.fr
         */
    	for (java.util.Iterator<CalendarComponent> iCal = calendar.getComponents().iterator(); iCal.hasNext();) {
    		Component componentCal = (Component) iCal.next();
    		for (java.util.Iterator<Property> jCal = componentCal.getProperties().iterator(); jCal.hasNext();) {
    	        Property propertyCal = (Property) jCal.next();
    	        if(propertyCal.getName() == "UID"){
    	        	/*
    	             * Getting uID from the calendar from edt.enib.fr
    	             */
    	        	listNewEdtuID.add(propertyCal.getValue());
    	        	
    	        	int uIDCal = Integer.parseInt(propertyCal.getValue());
    	        	/*
    	             * Iterating over the userCal
    	             */
    	        	for (java.util.Iterator<CalendarComponent> iUser = userCal.getComponents().iterator(); iUser.hasNext();) {
    	        		Component componentUser = (Component) iUser.next();
    	        		for (java.util.Iterator<Property> jUser = componentUser.getProperties().iterator(); jUser.hasNext();) {
    	        	        Property propertyUser = (Property) jUser.next();
    	        	        if(propertyUser.getName() == "UID"){
    	        	        	if(Integer.parseInt(propertyUser.getValue()) == uIDCal){
    	        	        		for (jUser = componentUser.getProperties().iterator(); jUser.hasNext();) {
    	        	        	        propertyUser = (Property) jUser.next();
    	        	        	        switch(propertyUser.getName()){
    	        	        	        case "DTSTART":
    	        	        	        	oldStart = propertyUser.getValue();
    	        	        	        	break;
    	        	        	        	
    	        	        	        case "LOCATION":
    	        	        	        		oldLocation = propertyUser.getValue();
    	        	        	        	break;
    	        	        	        	
    	        	        	        case "SUMMARY":
    	        	        	        	event = propertyUser.getValue();
        	        	        	        break;
    	        	        	        
    	        	        	        		
    	        	        	        	}
    	        	        	        }  
    	        	        		}
    	        	        	}	
    	        	        }
    	        		}
    	        	
    	        	/*
    	             * Getting new information
    	             */
    	        	for (jCal = componentCal.getProperties().iterator(); jCal.hasNext();) {
	        	        propertyCal = (Property) jCal.next();
	        	        switch(propertyCal.getName()){
	        	        case "DTSTART":
	        	        	String newStart = propertyCal.getValue();
	        	        	this.compareEDT(event,oldStart, newStart, "time");
	        	        	break;
	        	        	
	        	        case "LOCATION":
	        	        	String newLocation = propertyCal.getValue();
	        	        	this.compareEDT(event,oldLocation, newLocation, "location");
	        	        	break;    	        	
	        	        	}
	        	        }
    	        	}
    	        }
    		}
		this.compareuID(listOldEdtuID, listNewEdtuID,userCal);
    	this.saveEDT(calendar);
    }

    
    public void saveEDT(Calendar calendar){
    	
    	FileOutputStream fout = null;
		try {
			fout = new FileOutputStream("userCal.ics");
		} catch (FileNotFoundException e) {
			System.out.println("Can't make ical");
			e.printStackTrace();
		}
		
		CalendarOutputter outputter = new CalendarOutputter();
    	outputter.setValidating(false);
    	try {
			outputter.output(calendar, fout);
		} catch (IOException e) {
			System.out.println("Can't make ical");
			e.printStackTrace();
		} catch (ValidationException e) {
			System.out.println("Can't make ical");
			e.printStackTrace();
		}
    }
    public Date toDate(String stringDate){
    	int day = Integer.parseInt(stringDate.substring(6,8));
    	int month = Integer.parseInt(stringDate.substring(4,6));
    	int year = Integer.parseInt(stringDate.substring(0,4));
    	int hour = Integer.parseInt(stringDate.substring(9,11));
    	int min = Integer.parseInt(stringDate.substring(11,13));
    	return new Date(day,month,year,hour,min);
    }

	public EDT getEdt() {
		return edt;
	}
 }