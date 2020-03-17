package model;

import java.io.Serializable;
import java.util.Date;

public class OrderProduct implements Serializable {

	private int productNum;
	private int orderNum; 
	private String productName;
	private int qty;
	private int price;
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "OrderProduct [productNum=" + productNum + ", orderNum=" + orderNum + ", productName=" + productName
				+ ", qty=" + qty + ", price=" + price + "]";
	}
	
	
}
