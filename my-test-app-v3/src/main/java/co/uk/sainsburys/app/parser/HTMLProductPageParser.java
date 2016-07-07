package co.uk.sainsburys.app.parser;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import co.uk.sainsburys.app.model.Product;

/**
 * 
 * @author matthiaselsner
 *
 */
public class HTMLProductPageParser extends HTMLPageParser implements HTMLPageParsable {
	
	private Product product = new Product();
	
	public HTMLProductPageParser(String productPageurl) {
		// get HTML Page as DOM Document with one Product from URL
    	provideHTMLPageAsDocumentFromURL(productPageurl);
	}
	
	
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * 
	 */
	public void parse(){
		parseDocGetProductDescriptionAndSize();
	}
	
    /**
     * parses an product page .  
     */
    private void parseDocGetProductDescriptionAndSize(){
    	
    	this.product.setSize(FileUtils.byteCountToDisplaySize(doc.html().length()));
    	
    	Element descriptionElt = doc.select("htmlcontent").first();
    	this.product.setDescription(descriptionElt.text());
    }
    
    
    public String toJSON() {
    	JSONObject productJSON = new JSONObject();
    	productJSON.put("product", product);
    	return getPrettyJSON(productJSON);
    }
}
