/**
 * 
 */
package com.onlineExamination.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineExamination.entity.ExamDetails;



/**
 * @author Lenovo
 *
 */
public interface ExamDetailsRepo extends JpaRepository<ExamDetails, Long>{

	/**
	 * @param username
	 * @return
	 */
	ExamDetails findByCandidateId(String condidateId);

	/**
	 * @param candidateId
	 * @param dates
	 * @return
	 */
	ExamDetails findByCandidateIdAndDates(String candidateId, String dates);

	/**
	 * @param formattedDate
	 * @param condidateId
	 */
	ExamDetails findEndTimeByDatesAndCandidateId(String formattedDate, String condidateId);
}
