package model;

import java.io.Serializable;

public class Sale implements Serializable {
	private int rcpNum;
	private String productname;
	private int price;
	private int stock;
	private int sales;
	
	public int getRcpNum() {
		return rcpNum;
	}
	public void setRcpNum(int rcpNum) {
		this.rcpNum = rcpNum;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	
	
	@Override
	public String toString() {
		return "Sale [rcpNum=" + rcpNum + ", productname=" + productname + ", price=" + price + ", stock=" + stock
				+ ", sales=" + sales + "]";
	}
	
	
	
}
