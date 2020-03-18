package model;

import java.io.Serializable;
import java.util.Date;

public class Jjim implements Serializable {

	private int jjimNum; 
	private int memNum;
	private String productName;
	private int price;
	
	public int getJjimNum() {
		return jjimNum;
	}
	public void setJjimNum(int jjimNum) {
		this.jjimNum = jjimNum;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "Jjim [jjimNum=" + jjimNum + ", memNum=" + memNum + ", productName=" + productName + ", price=" + price
				+ "]";
	}
	
	
}