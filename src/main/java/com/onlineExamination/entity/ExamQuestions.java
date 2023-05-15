/**
 * 
 */
package com.onlineExamination.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tblquestions")
public class ExamQuestions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quid;
	
	 String Question;
	 String option1;
	 String option2;  
	 String option3;
	 String option4;
	 String CorrectOption;
	 String chrCourseCode;
	 String chrQuestType;
	 String chrImagePath;
	
	
	public long getQuid() {
		return quid;
	}
	
	public void setQuid(int Quid) {
		quid = Quid;
	}
	
	public String getQuestion() {
		return Question;
	}
	
	
	public void setQuestion(String question) {
		Question = question;
	}
	
	public String getOption1() {
		return option1;
	}
	
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	
	public String getOption2() {
		return option2;
	}
	
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	
	public String getOption3() {
		return option3;
	}
	
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	
	public String getOption4() {
		return option4;
	}
	
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	
	public String getCorrectOption() {
		return CorrectOption;
	}
	
	public void setCorrectOption(String correctOption) {
		CorrectOption = correctOption;
	}
	
	public String getChrCourseCode() {
		return chrCourseCode;
	}
	
	public void setChrCourseCode(String chrCourseCode) {
		this.chrCourseCode = chrCourseCode;
	}
	
	public String getChrQuestType() {
		return chrQuestType;
	}
	
	public void setChrQuestType(String chrQuestType) {
		this.chrQuestType = chrQuestType;
	}
	
	public String getChrImagePath() {
		return chrImagePath;
	}
	
	public void setChrImagePath(String chrImagePath) {
		this.chrImagePath = chrImagePath;
	}
	@Override
	public String toString() {
		return "ExamQuestions [QID=" + quid + ", Question=" + Question + ", option1=" + option1 + ", option2=" + option2
				+ ", option3=" + option3 + ", option4=" + option4 + ", CorrectOption=" + CorrectOption
				+ ", chrCourseCode=" + chrCourseCode + ", chrQuestType=" + chrQuestType + ", chrImagePath="
				+ chrImagePath + "]";
	}
	
	public ExamQuestions(String question, String option1, String option2, String option3, String option4,
			String correctOption, String chrCourseCode, String chrQuestType, String chrImagePath) {
		super();
		Question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		CorrectOption = correctOption;
		this.chrCourseCode = chrCourseCode;
		this.chrQuestType = chrQuestType;
		this.chrImagePath = chrImagePath;
	}
	
	
	public ExamQuestions() {
		super();
	}
	
 
	

}
