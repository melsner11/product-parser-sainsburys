package co.uk.sainsburys.app;

import co.uk.sainsburys.app.parser.HTMLProductListPageParser;

public class App 
{

	public static final String startUrl="http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
	
	/**
	 * 
	 * @param args
	 */
    public static void main( String[] args )
    {
        
    	HTMLProductListPageParser productListPageParser = new HTMLProductListPageParser(startUrl);
    	productListPageParser.parse();
    	
    	System.out.println(productListPageParser.toJSON());        
    }
        
}
