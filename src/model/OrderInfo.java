package model;

import java.io.Serializable;
import java.util.Date;

public class OrderInfo implements Serializable {

	private int orderNum; 
	private int memNum;
	private String memName;
	private Date orderdate;
	private int amount;
	private String address;
	private String zipcode;
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
	@Override
	public String toString() {
		return "OrderInfo [orderNum=" + orderNum + ", memNum=" + memNum + ", memName=" + memName + ", orderdate="
				+ orderdate + ", amount=" + amount + ", address=" + address + ", zipcode=" + zipcode + "]";
	}
	
}