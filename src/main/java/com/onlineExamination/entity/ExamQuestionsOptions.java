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
@Table(name = "tblquestionsoptions")
public class ExamQuestionsOptions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long qouid;
	
	private long quid;
	private long qoid;
	private String optionValue;
	private boolean isCorrectOption;
	private long mapOptionId;
	
	public long getQouid() {
		return qouid;
	}
	
	public void setQouid(long qouid) {
		this.qouid = qouid;
	}
	
	public long getQuid() {
		return quid;
	}
	
	public void setQuid(long quid) {
		this.quid = quid;
	}
	
	public long getQoid() {
		return qoid;
	}
	
	public void setQoid(long qoid) {
		this.qoid = qoid;
	}
	
	public String getOptionValue() {
		return optionValue;
	}
	
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	
	public boolean isCorrectOption() {
		return isCorrectOption;
	}
	
	public void setCorrectOption(boolean isCorrectOption) {
		this.isCorrectOption = isCorrectOption;
	}
	
	public long getMapOptionId() {
		return mapOptionId;
	}
	
	public void setMapOptionId(long mapOptionId) {
		this.mapOptionId = mapOptionId;
	}
	@Override
	public String toString() {
		return "ExamQuestionsOptions [qouid=" + qouid + ", quid=" + quid + ", qoid=" + qoid + ", optionValue="
				+ optionValue + ", isCorrectOption=" + isCorrectOption + ", mapOptionId=" + mapOptionId + "]";
	}
	
	
 
	

}
