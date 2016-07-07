package co.uk.sainsburys.app.parser;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class HTMLPageParser implements HTMLPageParsable{

	protected Document doc=new Document("");

	/**
	 * Get any HTML Document from a given URL
	 * 
	 * @param url String to get the document from
	 * @return Document
	 */
	protected  Document provideHTMLPageAsDocumentFromURL(final String url) {

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			doc = new Document("");
			e.printStackTrace();
		}

		return doc;
	}

	/**
	 * 
	 * @param html HTML String to parse the document from 
	 * @return
	 */
	protected  Document provideHTMLPageAsDocumentFromHTML(String html) {

		doc = Jsoup.parse(html);
		return doc;
	}
	
	/**
	 * 
	 * @return doc
	 */
	public Document getDocument(){
		return doc;
	}
	
    /**
     * Formats the JSON Object as pretty human readable output
     * @param jsonObj JSON Object with a total sum and an array of products for the product list 
     * @return String pretty formatted JSon String
     */
    protected static String getPrettyJSON (JSONObject jsonObj){
    	String prettyJson="";
    	
    	ObjectMapper mapper = new ObjectMapper();
         
        try {
 			prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
 			
 		} catch (JsonGenerationException e) {
 			e.printStackTrace();
 		} catch (JsonMappingException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
         
         return prettyJson;
    }
    

}
