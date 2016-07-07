package co.uk.sainsburys.app.model;

import java.text.DecimalFormat;

public class Product {
	
	private String title;
	private String size; // should be a float - but json out needs a string here - so the object is rather to see as a frontend display object than a model object 
	private String unit_price; // should be a float - but json out needs a string here - again ,  the object is more a frontend display object than a model object
	private String description;
	
	
	
	public String getPrice() {
		
		return unit_price;
	}

	public void setPrice(float price) {
		DecimalFormat formatter = new DecimalFormat("#.00");
		this.unit_price = formatter.format(price);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [title=" + title + ", size=" + size + ", unit_price=" + unit_price + ", description="
				+ description.length() + "]";
	}

	
	

	
}
