package ca.sheridan.nguvuong.model;

import java.io.Serializable;

public class Product implements Serializable {
	private int id;
	private String productCode;
	private String productBrand;
	private int quantity;
	private double productPrice;
	
	public Product() {	
	}
	
 	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	public String getProductCode() {
		return this.productCode;
	}
	
	public String getProductBrand() {
		return this.productBrand;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public double getProductPrice() {
		return this.productPrice;
	}


}
