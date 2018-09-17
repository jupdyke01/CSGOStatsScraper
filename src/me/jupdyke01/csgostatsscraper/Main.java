package me.jupdyke01.csgostatsscraper;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Main {

	public static final String URL = "https://onelinefun.com/";
	WebClient client;
	Random r;
	
	public Main() {
		client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		r = new Random();
		System.out.println(findPun());
	}

	public String findPun() {
		String searchUrl = "https://onelinefun.com/" + (r.nextInt(492) + 1) + "/";
		try {
			HtmlPage page = client.getPage(searchUrl);
			List<HtmlElement> jokes = page.getByXPath(".//div[@class='o']/p");
			if (jokes.isEmpty()) {
				System.out.println("404 Joke not found.");
			} else {
				return jokes.get(r.nextInt(jokes.size() - 1)).asText();
			}
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		return "404 Joke not found.";
	}
	
	

	public static void main(String[] args) {
		new Main();
	}

}
