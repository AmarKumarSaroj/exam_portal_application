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
@Table(name = "stdntExamQuestions")
public class StudentOfExamQuestions {
	
	@Id
	@GeneratedValue(generator = "student_gen",strategy = GenerationType.AUTO)	
	private long studentId;
	
	private String examScheduleId;
	private String selectedOption;
	private String correctOption;
	private String statusOption;
	private String questionCode;
	private String condidateId;
	
	public long getStudentId() {
		return studentId;
	}
	
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
	public String getExamScheduleId() {
		return examScheduleId;
	}
	
	public void setExamScheduleId(String examScheduleId) {
		this.examScheduleId = examScheduleId;
	}
	
	public String getSelectedOption() {
		return selectedOption;
	}
	
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	
	public String getCorrectOption() {
		return correctOption;
	}
	
	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}
	
	public String getStatusOption() {
		return statusOption;
	}
	
	public void setStatusOption(String statusOption) {
		this.statusOption = statusOption;
	}
	
	public String getQuestionCode() {
		return questionCode;
	}
	
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	
	public String getCondidateId() {
		return condidateId;
	}
	
	public void setCondidateId(String condidateId) {
		this.condidateId = condidateId;
	}
	@Override
	public String toString() {
		return "StudentOfExamQuestions [studentId=" + studentId + ", examScheduleId=" + examScheduleId
				+ ", selectedOption=" + selectedOption + ", correctOption=" + correctOption + ", statusOption="
				+ statusOption + ", questionCode=" + questionCode + ", condidateId=" + condidateId + "]";
	}
	
	public StudentOfExamQuestions(long studentId, String examScheduleId, String selectedOption, String correctOption,
			String statusOption, String questionCode, String condidateId) {
		super();
		this.studentId = studentId;
		this.examScheduleId = examScheduleId;
		this.selectedOption = selectedOption;
		this.correctOption = correctOption;
		this.statusOption = statusOption;
		this.questionCode = questionCode;
		this.condidateId = condidateId;
	}
	
	public StudentOfExamQuestions() {
		super();
	}
	
	
	

}
