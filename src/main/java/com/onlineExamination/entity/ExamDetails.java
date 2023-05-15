package com.onlineExamination.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "examdetails")
public class ExamDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long examDetailsId;

	private String Description;
	private String totalquestions;
	private String wrongmarks;
	private String correctmarks;
	private String endTime;
	private String startTime;
	private String examduration;
	private String candidateId;
	private String subjectName;
	private String dates;

	public ExamDetails() {
		
	}

	public ExamDetails(long examDetailsId, String description, String totalquestions, String wrongmarks,
			String correctmarks, String endTime, String startTime, String examduration, String candidateId,
			String subjectName, String dates) {
		super();
		this.examDetailsId = examDetailsId;
		Description = description;
		this.totalquestions = totalquestions;
		this.wrongmarks = wrongmarks;
		this.correctmarks = correctmarks;
		this.endTime = endTime;
		this.startTime = startTime;
		this.examduration = examduration;
		this.candidateId = candidateId;
		this.subjectName = subjectName;
		this.dates = dates;
	}

	@Override
	public String toString() {
		return "ExamDetails [examDetailsId=" + examDetailsId + ", Description=" + Description + ", totalquestions="
				+ totalquestions + ", wrongmarks=" + wrongmarks + ", correctmarks=" + correctmarks + ", endTime="
				+ endTime + ", startTime=" + startTime + ", examduration=" + examduration + ", candidateId="
				+ candidateId + ", subjectName=" + subjectName + ", studentName=" + ", dates=" + dates + "]";
	}

	public long getExamDetailsId() {
		return examDetailsId;
	}

	public void setExamDetailsId(long examDetailsId) {
		this.examDetailsId = examDetailsId;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getTotalquestions() {
		return totalquestions;
	}

	public void setTotalquestions(String totalquestions) {
		this.totalquestions = totalquestions;
	}

	public String getWrongmarks() {
		return wrongmarks;
	}

	public void setWrongmarks(String wrongmarks) {
		this.wrongmarks = wrongmarks;
	}

	public String getCorrectmarks() {
		return correctmarks;
	}

	public void setCorrectmarks(String correctmarks) {
		this.correctmarks = correctmarks;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getExamduration() {
		return examduration;
	}

	public void setExamduration(String examduration) {
		this.examduration = examduration;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

}
