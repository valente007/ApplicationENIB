package Application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * Home is the home page of this application and is able to:
 * 			- Get news from enib.net
 */

public class Home {
	

	protected List<News> news = new ArrayList<News> ();	
	
	/*
     * Getting news from "http://enib.net/"
     */
	public List<News> getNews() {
		Document doc = null;
		try {
			doc = Jsoup.connect("http://enib.net/").get();
		} catch (IOException e) {
			System.out.println("Can't load news");
			e.printStackTrace();
		}
		
		/*
		 * Getting name, information, description and add it to the news List
		 */
		Elements getter = doc.getElementsByClass("news");
		for (Element get : getter){
			String news = "";
			String name = get.select("h1").text();
			String information = get.select("h2").text();
			Elements markdown = get.getElementsByClass("markdown");
			for (Element paragraph : markdown.select("p")){
				news = news+paragraph.text()+System.getProperty("line.separator");
			}
			News n = new News(name,information,news);
			this.news.add(n);
		}
		return this.news;
	}

}
