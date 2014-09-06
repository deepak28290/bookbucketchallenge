package books.pop.bbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	public static List<String> getBooks() throws IOException {
	
		try{
			String urlStr="https://graph.facebook.com/v1.0/search?limit=999&type=post&q=%23bookbucketchallenge&access_token=CAAGkXbsMptgBACq5sYE27eZByskt1A0tVStoAWZACvhmpl3WPCe4CwejoiSqdkoncZCciGVbuW5dAco2aWSiO6YptBHtkdZBz6G6nmLt6GpX0yRGdUunZCVMuurDidolvZAkLNv97ttfyLF4NF7XmFWdlbctH8r2lYc47p4q06ZCwyinfKOsm7kMbW7hf5QAZAyOi2iDMZBxAmsPt3lAcPcAA";
		
	for(int j=0;j<10;j++){
		URL url = new URL(
				urlStr);
		
		URLConnection urlConnection = url.openConnection();
		HttpURLConnection connection = null;
		if (urlConnection instanceof HttpURLConnection) {
			connection = (HttpURLConnection) urlConnection;
		} else {
			System.out.println("Please enter an HTTP URL.");
			return null;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String urlString = "";
		String current;
		while ((current = in.readLine()) != null) {
			urlString += current;
		}
		/* JSONObject json= J */
		JSONObject jsonObj = new JSONObject(urlString);
		
		for(int i=0;i<jsonObj.getJSONArray("data").length();i++){
			if(jsonObj.getJSONArray("data").getJSONObject(i).has("message")){
			System.out.println(	jsonObj.getJSONArray("data").getJSONObject(i).get("message"));
			
						}
		//System.out.println(jsonObj);
		urlStr=jsonObj.getJSONObject("paging").get("next").toString();
		//System.out.println("int i "+j+jsonObj.getJSONArray("data").length());
		//System.out.println(urlStr);
		}
		
	}
		}catch(Exception e){
		e.printStackTrace();	
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(getBooks());

//		System.out.println(jsonObj);
//		jsonObj.get("data");
	}

}
