package com.example.valentin.applicationenib.appCodeSource.application;

public class News {
	
	protected String name;
	protected String information;
	protected String news;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}

	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	
	public News(String name, String information, String news) {
		super();
		this.name = name;
		this.information = information;
		this.news = news;
	}
}
