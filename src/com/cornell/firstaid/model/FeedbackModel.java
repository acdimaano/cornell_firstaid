package com.cornell.firstaid.model;

/*
 * Developed by : Alan Dimaano
 * For COMP722 in CSDL7
 * Cornell Institute of Business and Technology
 */

public class FeedbackModel extends FirstAidModel {
	
	public String msgEmail;
	public String msgName;
	public String msgPhoneNumber;
	public String msgComment;
	public String msgDevNumber;
	public String msgStatus;
	
	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public String getMsgDevNumber() {
		return msgDevNumber;
	}

	public void setMsgDevNumber(String msgDevNumber) {
		this.msgDevNumber = msgDevNumber;
	}

	public String getMsgEmail() {
		return msgEmail;
	}
	
	public void setMsgEmail(String msgEmail) {
		this.msgEmail = msgEmail;
	}
	
	public String getMsgName() {
		return msgName;
	}
	
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	
	public String getMsgPhoneNumber() {
		return msgPhoneNumber;
	}
	
	public void setMsgPhoneNumber(String msgPhoneNumber) {
		this.msgPhoneNumber = msgPhoneNumber;
	}
	
	public String getMsgComment() {
		return msgComment;
	}
	
	public void setMsgComment(String msgComment) {
		this.msgComment = msgComment;
	}

}
