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
@Table(name = "student_exam_schedule")
public class StudentExamSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long studentScheduleId;
	
	long examScheduleId;
	String CandidateId;
	String examStartTime;
	String examEndTIme;
	
	@Override
	public String toString() {
		return "StudentExamSchedule [studentScheduleId=" + studentScheduleId + ", examScheduleId=" + examScheduleId
				+ ", CandidateId=" + CandidateId + ", examStartTime=" + examStartTime + ", examEndTIme=" + examEndTIme
				+ "]";
	}


	public long getStudentScheduleId() {
		return studentScheduleId;
	}


	public void setStudentScheduleId(long studentScheduleId) {
		this.studentScheduleId = studentScheduleId;
	}


	public long getExamScheduleId() {
		return examScheduleId;
	}


	public void setExamScheduleId(long examScheduleId) {
		this.examScheduleId = examScheduleId;
	}


	public String getCandidateId() {
		return CandidateId;
	}


	public void setCandidateId(String candidateId) {
		CandidateId = candidateId;
	}


	public String getExamStartTime() {
		return examStartTime;
	}


	public void setExamStartTime(String examStartTime) {
		this.examStartTime = examStartTime;
	}


	public String getExamEndTIme() {
		return examEndTIme;
	}

	
	public void setExamEndTIme(String examEndTIme) {
		this.examEndTIme = examEndTIme;
	}


	public StudentExamSchedule() {
		
	}

}
