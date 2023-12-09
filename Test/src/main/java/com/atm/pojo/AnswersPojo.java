package com.atm.pojo;

public class AnswersPojo {
	
	private int id;
	private String answerId;
	private String dosha;
	private int questionId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getDosha() {
		return dosha;
	}
	public void setDosha(String dosha) {
		this.dosha = dosha;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	@Override
	public String toString() {
		return "AnswersPojo [id=" + id + ", answerId=" + answerId + ", dosha=" + dosha + ", questionId=" + questionId
				+ "]";
	}
	
	

}
