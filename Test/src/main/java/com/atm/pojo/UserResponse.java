package com.atm.pojo;

public class UserResponse {
	
	private int id;
	private int patientId;
	private String answerId;
	private int questionId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", patientId=" + patientId + ", answerId=" + answerId + ", questionId="
				+ questionId + "]";
	}
	
	
	
	
}
