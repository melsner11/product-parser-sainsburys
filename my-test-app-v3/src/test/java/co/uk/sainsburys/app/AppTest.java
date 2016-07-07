package co.uk.sainsburys.app;

import java.util.List;


import co.uk.sainsburys.app.model.Product;
import co.uk.sainsburys.app.parser.HTMLProductListPageParser;
import co.uk.sainsburys.app.parser.HTMLProductPageParser;
import junit.framework.TestCase;

/**
 * Unit test for App.
 */
public class AppTest 
    extends TestCase
{	

	
	private final static String urlProductList="http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
	private final static String urlProduct="http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-avocado-xl-pinkerton-loose-300g.html";
    
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
        
    }
    
    public void testServerIsResponding()
    {
        HTMLProductListPageParser productListPageParser = new HTMLProductListPageParser(urlProductList);
        boolean hasHTMLInResponse = (productListPageParser.getDocument().html().indexOf("html") >=0);
        assertTrue("Server is responding: ", hasHTMLInResponse);
    }
    
    public void testProductPriceSumInListIsCorrect ()
    {
    	HTMLProductListPageParser productListPageParser = new HTMLProductListPageParser(urlProductList);
    	productListPageParser.parse();	
    	List<Product> products = productListPageParser.getProducts();
    	
        assertEquals( "Test calculating product Sum:", calculateSumProductListPrice(products),new Float(15.1));

    }
    
    public void testAmmounOfProductsInListIsSeven ()
    {
    	HTMLProductListPageParser productListPageParser = new HTMLProductListPageParser(urlProductList);
    	productListPageParser.parse();	
    	List<Product> products = productListPageParser.getProducts();
        assertEquals( "Test amount of products in list is 7:", products.size(),7);
        
    }
    
    public void testParserReadsProductDescription ()
    {
    	HTMLProductPageParser productPageParser = new HTMLProductPageParser(urlProduct);
		productPageParser.parse();
		
		Product product =  productPageParser.getProduct();
		int pos = product.getDescription().indexOf("Description");
        assertTrue("Product Parser reads a product description: " ,(pos >=0) );
    }
 
    public void testParserReadsProductTitle ()
    {
    	HTMLProductListPageParser productListPageParser = new HTMLProductListPageParser(urlProductList);
    	productListPageParser.parse();	
    	List<Product> products = productListPageParser.getProducts();
    	Product product = (Product)products.get(0);	
    	
		System.out.print(product);
		int pos = product.getTitle().indexOf("Apricot");
        assertTrue("Product Parser reads a product title: " ,(pos >=0) );
    }
    
    
	  /**
	  * Calculates Sum of product prices 
	  * @param products
	  * @return
	  */
	 private static float calculateSumProductListPrice(List<Product> products){
	 	float productPriceSum=0; 	
	 	for (Product product : products) {
	 		    
	 		  productPriceSum = productPriceSum + new Float(product.getPrice());
	 		   
	 	}
	 	return productPriceSum;
	 }
    
}
