package com.onlineExamination.entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class DataOfStudent{
	
	@Id
	@GeneratedValue(generator = "student_gen",strategy = GenerationType.AUTO)	
	private long stdUid;	
	
	private long qid;
	private String question;
	
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	private String selectedOption;
	private String correctOption;
	private String statusOption;
	
	private String chrCourseCode;
	private String chrImagePath;
	private String chrQuestType;
	private String questionCode;
	private String condidateId;
	

	public String getCondidateId() {
		return condidateId;
	}
	
	public void setCondidateId(String condidateId) {
		this.condidateId = condidateId;
	}
	
	public String getQuestionCode() {
		return questionCode;
	}
	
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	
	public long getStdUid() {
		return stdUid;
	}
	
	public void setStdUid(long stdUid) {
		this.stdUid = stdUid;
	}
	
	public long getQid() {
		return qid;
	}
	
	public void setQid(long qid) {
		this.qid = qid;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
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
	
	public String getChrCourseCode() {
		return chrCourseCode;
	}
	
	public void setChrCourseCode(String chrCourseCode) {
		this.chrCourseCode = chrCourseCode;
	}
	
	public String getChrImagePath() {
		return chrImagePath;
	}
	
	public void setChrImagePath(String chrImagePath) {
		this.chrImagePath = chrImagePath;
	}
	
	public String getChrQuestType() {
		return chrQuestType;
	}
	
	public void setChrQuestType(String chrQuestType) {
		this.chrQuestType = chrQuestType;
	}
	
	public DataOfStudent(long stdUid, long qid, String question, String option1, String option2, String option3,
			String option4, String selectedOption, String correctOption, String statusOption, String chrCourseCode,
			String chrImagePath, String chrQuestType) {
		this.stdUid = stdUid;
		this.qid = qid;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.selectedOption = selectedOption;
		this.correctOption = correctOption;
		this.statusOption = statusOption;
		this.chrCourseCode = chrCourseCode;
		this.chrImagePath = chrImagePath;
		this.chrQuestType = chrQuestType;
	}
	
	
	public DataOfStudent() {
	}
	@Override
	public String toString() {
		return "DataOfStudent [stdUid=" + stdUid + ", qid=" + qid + ", question=" + question + ", option1=" + option1
				+ ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4 + ", selectedOption="
				+ selectedOption + ", correctOption=" + correctOption + ", statusOption=" + statusOption
				+ ", chrCourseCode=" + chrCourseCode + ", chrImagePath=" + chrImagePath + ", chrQuestType="
				+ chrQuestType + "]";
	}
	
	
 

	
}
