package Application;

import java.net.URL;
import java.net.URLConnection;
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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Events.Date;
import Events.Event;
import Profil.Adress;
import Profil.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;

/*
 *  This class is used for sending request to enib.net such as:
 *  		- Getting user's information.
 */


public class Http {

	private HttpCookie csrftoken;
	private HttpCookie sessionid;
	private List<NameValuePair> postParams;	
	
	public static void main(String[] args) throws Exception {
		Http h = new Http();
		h.getSendUser(new Student("a2guilli"));
	}
	
	/*
	 *  Initialize cookies from enib.net
	 */
	public void initCookie(String direction){

		CookieManager cookiemanager = new CookieManager();
		CookieHandler.setDefault(cookiemanager);
		URL url = null;
		try {
			url = new URL(direction);
		} catch (MalformedURLException e) {
			System.out.println("Mal formed URL");
			e.printStackTrace();
		}
		URLConnection connection = null;
		try {
			connection = url.openConnection();
		} catch (IOException e) {
			System.out.println("Can't open connection");
			e.printStackTrace();
		}
		try {
			connection.getContent();
		} catch (IOException e) {
			System.out.println("Can't connect");
			e.printStackTrace();
		}
		
		/*
		 *  Getting csrfmiddletoken cookie from enib.net
		 */
		CookieStore cookieStore = cookiemanager.getCookieStore();
		List<HttpCookie> cookieList = cookieStore.getCookies();
		for (HttpCookie cookie : cookieList){
			this.csrftoken = cookie;
		}
	}
	
	/*
	 *  Setting parameters for connect the user
	 */
	public void setParams(String login,String password){
		this.postParams = new ArrayList<NameValuePair>();
		this.postParams.add(new BasicNameValuePair("csrfmiddlewaretoken",this.csrftoken.getValue()));
		this.postParams.add(new BasicNameValuePair("email",login));
		this.postParams.add(new BasicNameValuePair("password",password));
	}
	
	/*
	 *  Connect user
	 */
	public void postInit(String url){
		HttpPost post = new HttpPost(url);		  

		// add header
		post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		post.setHeader("Origin", "http://enib.net");
		post.setHeader("Cookie",this.csrftoken.getName()+"="+this.csrftoken.getValue());
		post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setHeader("Referer", "http://enib.net/accounts/login");
		post.setHeader("Accept-Encoding", "gzip, deflate");
		post.setHeader("Accept-Language", "fr-FR,fr;q=0.8,en-US;q=0.6,en;q=0.4");

		try {
			post.setEntity(new UrlEncodedFormEntity(this.postParams));
		} catch (UnsupportedEncodingException e) {
			System.out.println("Wring encodation");
			e.printStackTrace();
		}
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			response = client.execute(post);
		} catch (IOException e) {
			System.out.println("Can't execute post");
			e.printStackTrace();
		}
		
		/*
		 *  Getting & want to save sessionid & csrftoken cookie
		 */
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response status : " + response.getStatusLine());
		this.csrftoken.setValue(response.getFirstHeader("Set-Cookie").getValue().substring(10,42));
		this.sessionid.setValue(response.getLastHeader("Set-Cookie").getValue().substring(10,42));
		this.saveCookies(csrftoken, sessionid);
	}
	
	/*
	 *  Getting information from user
	 */
	public void getSendUser(Student student) throws Exception {
		HttpGet get = new HttpGet("http://enib.net/accounts/"+student.getLogin()+"/edit");
		
		get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.setHeader("Cookie",this.sessionid.getName()+"="+this.sessionid.getValue()+";"
				+this.csrftoken.getName()+"="+this.csrftoken.getValue());
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
		get.setHeader("Content-Type", "application/x-www-form-urlencoded");
		get.setHeader("Accept-Encoding", "gzip, deflate");
		get.setHeader("Accept-Language", "fr-FR,fr;q=0.8,en-US;q=0.6,en;q=0.4");
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse response = client.execute(get);
		BufferedReader rd = new BufferedReader(new InputStreamReader((response.getEntity().getContent()),"UTF-8"));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result);
		}
	
	/*
	 *  Getting information from user from enib.net
	 */
	public void getFormParams(String html,Student student)throws UnsupportedEncodingException {
		Document doc = Jsoup.parse(html);

		Element getter = doc.getElementById("id_user-last_name");
		String userLastName = getter.attr("value").toString();
		student.setLastName(userLastName);

		getter = doc.getElementById("id_user-first_name");
		String userFisrtName = getter.attr("value").toString();
		student.setFirstName(userFisrtName);

		getter = doc.getElementById("id_user-email");
		String userEmail = getter.attr("value").toString();
		student.setEmail(userEmail);

		getter = doc.getElementById("id_profile-nickname");
		String userNickname = getter.attr("value").toString();
		student.setNickname(userNickname);

		getter = doc.getElementById("id_profile-phone");
		String userPhone = getter.attr("value").toString();
		student.setPhone(userPhone);

		getter = doc.getElementById("birth_date");
		String userBirth = getter.attr("value").toString();
		int birthDay = Integer.parseInt(userBirth.substring(0,2));
		int birthMonth = Integer.parseInt(userBirth.substring(3,5));
		int birthYear = Integer.parseInt(userBirth.substring(6,10));
		Date birthDate = new Date(birthDay,birthMonth,birthYear,0,0);
		student.setBirth(birthDate);

		getter = doc.getElementById("id_profile-family");
		//String userFamily = getter.attr("value").toString();
		//System.out.println(userFamily);

		getter = doc.getElementById("id_profile-promo");
		String userPromo = getter.attr("value").toString();
		student.setPromotion(userPromo);
		
		getter = doc.getElementById("id_profile-enib_join_year");
		int userYear = Integer.parseInt(getter.attr("value").toString());
		student.setEnterSchoolYear(userYear);

		getter = doc.getElementById("id_address-street");
		String userStreet = getter.attr("value").toString();

		getter = doc.getElementById("id_address-postal_code");
		String userZip = getter.attr("value").toString();

		getter = doc.getElementById("id_address-town");
		String userTown = getter.attr("value").toString();
		Adress userAdress = new Adress(userStreet,userZip,userTown);
		student.setAdress(userAdress);
	}
	
	/*
	 *  Saving cookies in a "cookies.xml" file for next connection
	 */
	public void saveCookies(HttpCookie csrftoken,HttpCookie sessionid) {

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("Can't build");
			e.printStackTrace();
		}
		Node document = null;
		try {
			document = builder.parse(new File("cookies.xml"));
		} catch (SAXException | IOException e) {
			System.out.println("Can't build file");
			e.printStackTrace();
		}
		final Node root = ((org.w3c.dom.Document) document).getDocumentElement();

		final NodeList rootNode = root.getChildNodes();
		final int rootNodeNbr = rootNode.getLength();
		
		/*
		 *  Setting cookie's value
		 */
		for (int i = 0; i<rootNodeNbr; i++) {
			if(rootNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Node subject = rootNode.item(i);
				if(((org.w3c.dom.Element) subject).getAttribute("name").equals("csrftoken")){
					((org.w3c.dom.Element) subject).setAttribute("value", csrftoken.getValue());
				}
				else if(((org.w3c.dom.Element) subject).getAttribute("name").equals("sessionid")){
					((org.w3c.dom.Element) subject).setAttribute("value", sessionid.getValue());
				}
			}

		}
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		Result output = new StreamResult(new File("cookies.xml"));
		Source input = new DOMSource(document);

		try {
			transformer.transform(input, output);
		} catch (TransformerException e) {
			System.out.println("Can't save cookies");
			e.printStackTrace();
		}

	}
	
	/*
	 *  Setting cookies from the XML file
	 */
	public void setCookies(){
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("Can't build document");
			e.printStackTrace();
		}
		Node document = null;
		try {
			document = builder.parse(new File("cookies.xml"));
		} catch (SAXException | IOException e) {
			System.out.println("Can't read cookies.xml");
			e.printStackTrace();
		}
		
		final Node root = ((org.w3c.dom.Document) document).getDocumentElement();

		final NodeList rootNode = root.getChildNodes();
		final int rootNodeNbr = rootNode.getLength();
		
		/*
		 *  Getting cookie's value
		 */
		for (int i = 0; i<rootNodeNbr; i++) {
			if(rootNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Node subject = rootNode.item(i);
				if(((org.w3c.dom.Element) subject).getAttribute("name").equals("csrftoken")){
					this.csrftoken = new HttpCookie("csrftoken",((org.w3c.dom.Element) subject).getAttribute("value"));
				}
				else if(((org.w3c.dom.Element) subject).getAttribute("name").equals("sessionid")){
					this.sessionid = new HttpCookie("sessionid",((org.w3c.dom.Element) subject).getAttribute("value"));
				}
			}
		}
	}
	
	/*
	 *  Checking if cookies are not expired by sending user's getting information request & checking if the title is "Site des élèves" or not
	 */
	public boolean isConnected(String login) throws IOException{
		HttpGet get = new HttpGet("http://enib.net/accounts/"+login);

		get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.setHeader("Cookie",this.sessionid.getName()+"="+this.sessionid.getValue()+";"
				+this.csrftoken.getName()+"="+this.csrftoken.getValue());
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
		get.setHeader("Content-Type", "application/x-www-form-urlencoded");
		get.setHeader("Referer", "http://enib.net/accounts/"+login);
		get.setHeader("Accept-Encoding", "gzip, deflate");
		get.setHeader("Accept-Language", "fr-FR,fr;q=0.8,en-US;q=0.6,en;q=0.4");

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;

		try {
			response = client.execute(get);
		} catch (IOException e) {
			System.out.println("Can't send get request");
			e.printStackTrace();
		}
		BufferedReader rd = new BufferedReader(new InputStreamReader((response.getEntity().getContent()),"UTF-8"));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		Document doc = Jsoup.parse(result.toString());
		if(doc.title().equals("Site des élèves")){
			return false;
		}
		return true;

	}
	
	/*
	 *  Setting information from user to enib.net
	 */
	public void sendPostUserSettings(Student student){
		
		HttpPost post = new HttpPost("http://enib.net/accounts/"+student.getLogin()+"/edit");
		List<NameValuePair> userParams= new ArrayList<NameValuePair>();

		userParams.add(new BasicNameValuePair("csrfmiddlewaretoken",this.csrftoken.getValue()));
		userParams.add(new BasicNameValuePair("user-last_name",student.getLastName()));
		userParams.add(new BasicNameValuePair("user-first_name",student.getFirstName()));
		userParams.add(new BasicNameValuePair("user-email",student.getEmail()));
		userParams.add(new BasicNameValuePair("profile-nickname",student.getNickname()));
		userParams.add(new BasicNameValuePair("profile-phone",student.getPhone()));
		userParams.add(new BasicNameValuePair("profile-birthdate",student.getBirth().getDay()+"/0"
																+student.getBirth().getMonth()+"/"
																+student.getBirth().getYear()));
		userParams.add(new BasicNameValuePair("profile-family",""));
		userParams.add(new BasicNameValuePair("profile-promo",""));
		userParams.add(new BasicNameValuePair("profile-enib_join_year",Integer.toString(student.getEnterSchoolYear())));
		userParams.add(new BasicNameValuePair("profile-semester","S6"));
		userParams.add(new BasicNameValuePair("profile_img-picture",""));
		userParams.add(new BasicNameValuePair("address-street",student.getAdress().getAdress()));
		userParams.add(new BasicNameValuePair("address-postal_code",student.getAdress().getZipCode()));
		userParams.add(new BasicNameValuePair("address-town",student.getAdress().getTown()));
		userParams.add(new BasicNameValuePair("old_password",""));
		userParams.add(new BasicNameValuePair("new_password1",""));
		userParams.add(new BasicNameValuePair("new_password2",""));
		

		// add header
		post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		post.setHeader("Origin", "http://enib.net");
		post.setHeader("Cookie",this.sessionid.getName()+"="+this.sessionid.getValue()+this.csrftoken.getName()+"="+this.csrftoken.getValue());
		post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
		post.setHeader("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryeiDPd0Av2wIHiyLH");
		post.setHeader("Referer", "http://enib.net/accounts/"+student.getLogin()+"/edit");
		post.setHeader("Accept-Encoding", "gzip, deflate");
		post.setHeader("Accept-Language", "fr-FR,fr;q=0.8,en-US;q=0.6,en;q=0.4");

		try {
			post.setEntity(new UrlEncodedFormEntity(userParams));
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported Encodagin of userParams");
			e.printStackTrace();
		}
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			response = client.execute(post);
		} catch (IOException e) {
			System.out.println("Can't send post request");
			e.printStackTrace();
		}

		System.out.println("\nSending 'POST' request to URL : " + "http://enib.net/accounts/"+student.getLogin()+"/edit");
		System.out.println("Post parameters : " + userParams);
		System.out.println("Response status : " + response.getStatusLine());
	}
	
	/*
	 *  Getting events from enib.net/events
	 */
	public void getSendEvent(List<Event> evts) throws Exception {

		HttpGet get = new HttpGet("http://enib.net/events");

		get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.setHeader("Cookie",this.sessionid.getName()+"="+this.sessionid.getValue()+";"
				+this.csrftoken.getName()+"="+this.csrftoken.getValue());
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
		get.setHeader("Content-Type", "application/x-www-form-urlencoded");
		get.setHeader("Referer", "http://enib.net/");
		get.setHeader("Accept-Encoding", "gzip, deflate");
		get.setHeader("Accept-Language", "fr-FR,fr;q=0.8,en-US;q=0.6,en;q=0.4");

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;

		response = client.execute(get);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		Document doc = Jsoup.parse(result.toString());
		Elements events = doc.getElementsByClass("event");
		
		/*
		 *  Getting event's number for accessing event's information
		 */
		for (Element event : events){
			String number = event.select("a").attr("href").substring(8,10);
			
			/*
			 *  Accessing event's information
			 */
			HttpGet HttpEvent = new HttpGet("http://enib.net/events/"+number);
			
			HttpEvent.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			HttpEvent.setHeader("Cookie",this.sessionid.getName()+"="+this.sessionid.getValue()+";"
					+this.csrftoken.getName()+"="+this.csrftoken.getValue());
			HttpEvent.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
			HttpEvent.setHeader("Content-Type", "application/x-www-form-urlencoded");
			HttpEvent.setHeader("Accept-Encoding", "gzip, deflate");
			HttpEvent.setHeader("Accept-Language", "fr-FR,fr;q=0.8,en-US;q=0.6,en;q=0.4");
			
			HttpClient clientEvent = HttpClientBuilder.create().build();
			HttpResponse responseEvent = null;

			responseEvent = clientEvent.execute(HttpEvent);

			BufferedReader rdEvent = new BufferedReader(new InputStreamReader(responseEvent.getEntity().getContent(),"UTF-8"));

			StringBuffer resultEvent = new StringBuffer();
			String lineEvent = "";
			while ((lineEvent = rdEvent.readLine()) != null) {
				resultEvent.append(lineEvent);
			}
			/*
			 * Initialize event's information
			 */
			Document docEvent = Jsoup.parse(resultEvent.toString(),"ISO-8859-1");
			Elements evs = docEvent.getElementsByClass("announce");
			String name = evs.select("h1").text();
			String description = "";
			String place = "";
			String price = "";
			String nbrPeople = "";
			Date begining = null;
			Date ending = null;
			
			/*
			 *  Getting event's information
			 */
			for(Element ev : evs){
				Elements markdown = ev.getElementsByClass("markdown");
				for (Element paragraph : markdown.select("p")){
					description = description+paragraph.text()+System.getProperty("line.separator");
				}
				Elements containers = ev.getElementsByClass("flex_container");
				
				int i = 0; //Need to know if we're getting price detail which only have 1 child (avoid outOfBound)
				
				for (Element container : containers){
					for (Element detail : container.getElementsByClass("flex")){
						i++;
						if (i == 5){
							price = detail.child(0).text();
							break;
						}
						String choice = detail.child(1).text();
						switch(choice){
						case "Début":
							begining = new Date(detail.child(0).text());
							break;
							
						case "Fin":
							ending = new Date(detail.child(0).text());
							break;
							
						case "Lieu":
							place = detail.child(0).text();
							break;
							
						case "Inscrit(s)":
							nbrPeople = detail.child(0).text().replaceAll(" ","");
							break;
						}	
					}
				}
			}
			/*
			 * Create event and add it to events-application's list
			 */
			Event eventUser = new Event(description,place,price,false,nbrPeople,name,begining,ending,Integer.parseInt(number));
			evts.add(eventUser);
		}
	}

	public Http() {
		super();
		this.setCookies();
	}
}

