package com.atm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.atm.config.DBConfig;
import com.atm.pojo.AnswersPojo;
import com.atm.pojo.DoshaSurveyQuestionPojo;

public class DoshaDao {
	
	static DBConfig dbconfig = new DBConfig() ;
	
	public ArrayList<DoshaSurveyQuestionPojo> getAllQuestion() throws SQLException {
		Connection con = dbconfig.GetMysqlCon();
		Connection con1 = dbconfig.GetMysqlCon();
		ArrayList<DoshaSurveyQuestionPojo> dsqs = new ArrayList<DoshaSurveyQuestionPojo>();
		ArrayList<DoshaSurveyQuestionPojo> dsqs1 = new ArrayList<DoshaSurveyQuestionPojo>();
		ArrayList<AnswersPojo> DoshaOpting = new ArrayList<AnswersPojo>();
		String sqlQuestion = "select * from ayurveda.question ";
			String sqlAnswer = "select * from ayurveda.Answer ";
			try {
				Statement statment = con.createStatement();
				Statement statment1 = con1.createStatement();
				ResultSet rsQuestion = statment.executeQuery(sqlQuestion);
				ResultSet rsAnswer = statment1.executeQuery(sqlAnswer);
				while(rsQuestion.next()) {
					ArrayList<AnswersPojo> emptyDoshaOpting = new ArrayList<AnswersPojo>();
					DoshaSurveyQuestionPojo dsq = new  DoshaSurveyQuestionPojo();
							dsq.setQuestion(rsQuestion.getString("observation"));
							dsq.setQuestionid(rsQuestion.getInt("id"));
							dsqs.add(dsq);
							dsq.setOption(emptyDoshaOpting);
//							System.out.println("dsq:"+dsq);
					}
				while (rsAnswer.next()) {
					AnswersPojo ansPojo = new AnswersPojo();
					ansPojo.setId(rsAnswer.getInt("id"));
					ansPojo.setAnswerId(rsAnswer.getString("answerId"));
					ansPojo.setDosha(rsAnswer.getString("dosha"));
					ansPojo.setQuestionId(rsAnswer.getInt("question_id"));
					DoshaOpting.add(ansPojo);
//					System.out.println("ans:"+ansPojo);
				}
				System.out.println();
				for(AnswersPojo anp: DoshaOpting ) {
//					System.out.println("ansinforloop:"+anp);
					for(DoshaSurveyQuestionPojo doshaS: dsqs) {
//					System.out.println("dsqsinforllooop:"+dsqs);
						if(anp.getQuestionId() == doshaS.getQuestionid()) {
							doshaS.getOption().add(anp);
							dsqs1.add(doshaS);
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
				con1.close();
				con.close();
			}
			System.out.println("dsqs:"+dsqs1);
		return dsqs1;
	} 

}
