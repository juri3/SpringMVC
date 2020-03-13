package model;

import java.util.Date;

public class RcpDataBean {
	// rcp table
	int rcpNum; // rcpNum int primary key,
	String title; // title varchar(20),
	String foodName; // foodName varchar(30),
	String subtitle; // subtitle varchar(30),
	String ingredient; // ingredient2 varchar(50),
	String cookingTime; // cookingTime varchar(30),
	int memNum; // memNum int,
	Date reg_date; // reg_date date,
	String thumbNail; // thumbNail varchar(30),
	String hashTag; // HashTag varchar(1000)

	/*// imgFile table
	int step;
	int fileSize; // fileSize int,
	String fileName; // fileName varchar(30)
	String content; // content varchar(100)
*/
	public int getRcpNum() {
		return rcpNum;
	}

	public void setRcpNum(int rcpNum) {
		this.rcpNum = rcpNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(String cookingTime) {
		this.cookingTime = cookingTime;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getThumbNail() {
		return thumbNail;
	}

	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}

	/*public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}*/

}