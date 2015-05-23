import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ListIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ProjectQueryServiceImpl implements ProjectQueryService{
	String host = "http://eavesdrop.openstack.org/meetings/";
	
	public String getProjectYear(String url) {
		StringBuilder result = new StringBuilder();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href^=20]");
			ListIterator<Element> iter = links.listIterator();
			while(iter.hasNext()){
				Element e = (Element) iter.next();
				String s = e.text();
				s = s.replace("/", "");
				result.append(s + " &nbsp;&nbsp;&nbsp;&nbsp; " + getProjectCount(url + "/" + s) +"<br>");
			}
			return result.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "found no years";
	}

	public String getProjectCount(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href$=.log.html]");
			return Integer.toString(links.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "0";
	}

	public String getProject(String name) {
		StringBuffer urlName = new StringBuffer(host);
		urlName.append(name);
		URL url;
		StringBuffer result = new StringBuffer();
		try {
			url = new URL(urlName.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			if(connection.getResponseCode() == 200 && name != ""){
				result.append("Year &nbsp; Count<br>");
				result.append(getProjectYear(urlName.toString()));
				return result.toString();
			}else{
				return "Unknown project " + name;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return "Could not make a url";
	}
	
}
