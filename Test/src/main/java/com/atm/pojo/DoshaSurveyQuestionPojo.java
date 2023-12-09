package com.atm.pojo;

import java.util.ArrayList;

public class DoshaSurveyQuestionPojo {
	
	private int questionid;
	private String question;
	private ArrayList<AnswersPojo> option;
	
	public int getQuestionid() {
		return questionid;
	}
	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public ArrayList<AnswersPojo> getOption() {
		return option;
	}
	public void setOption(ArrayList<AnswersPojo> option) {
		this.option = option;
	}
	@Override
	public String toString() {
		return "DoshaSurveyQuestionPojo [questionid=" + questionid + ", question=" + question + ", option=" + option
				+ "]";
	}

	
}
