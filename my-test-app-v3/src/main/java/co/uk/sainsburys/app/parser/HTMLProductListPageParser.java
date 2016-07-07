package co.uk.sainsburys.app.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import co.uk.sainsburys.app.model.Product;

/**
 * 
 * @author matthiaselsner
 *
 */
public class HTMLProductListPageParser extends HTMLPageParser{
	
	private List<Product> products = new ArrayList<Product>();
	
	
	/**
	 * 
	 * @param pageUrl
	 */
	public HTMLProductListPageParser(String pageUrl) {
    	provideHTMLPageAsDocumentFromURL(pageUrl);
	}
	
	/**
	 * 
	 */
	public void parse(){
		parseDocGetProductsWithDescriptionAsList();
	}
	
	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}
	
    /**
     * Parse the HTML Page for products and return a list of populated products  
     * @param doc
     * @return List of populated products
     */
    private void parseDocGetProductsWithDescriptionAsList(){
    	
    	// get all products from DOM
		Elements ul = doc.select("ul.productLister li");
		
		for (Element li : ul) {
			Element productInfoWrapperElt = li.select("div.productInfoWrapper > div.productInfo > h3 > a").first();
			Element pricePerUnitElt = li.select("div.addToTrolleytabBox  p.pricePerUnit").first();
			String productUrl = productInfoWrapperElt.attr("href");
			
			HTMLProductPageParser productPageParser = new HTMLProductPageParser(productUrl);
			productPageParser.parse();
			
			Product product =  productPageParser.getProduct();
			
			product.setTitle(productInfoWrapperElt.text() );
			product.setPrice(new Float(pricePerUnitElt.ownText().replace("&pound", "")));
			
			products.add(product);
		}
		
    }
    
    /**
     * 
     */
    public String toJSON(){
    	return  getPrettyJSON(convertProductListAndAddTotalToJSON(products));
    }
    
    /**
     * Creates A JSON Object with a total sum and an array of products for the product list  
     * @param products
     * @return JSONObject
     */
    private static JSONObject convertProductListAndAddTotalToJSON(List<Product> products) {
    	
    	float total = new Float(0);
    	
    	JSONArray productsJSON = new JSONArray();
    	for (Product product : products) {
    		total = total + new Float(product.getPrice());
    		productsJSON.add(product);
    	}

    	
    	JSONObject productsWithTotal = new JSONObject();
    	productsWithTotal.put("results", productsJSON);
    	productsWithTotal.put("total", total);
    	
    	return productsWithTotal;
    }
}
