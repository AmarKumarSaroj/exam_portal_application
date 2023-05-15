/**
 * 
 */
package com.onlineExamination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onlineExamination.entity.StudentExamSchedule;
import com.onlineExamination.entity.StudentOfExamQuestions;

/**
 * @author Lenovo
 *
 */
public interface StudentExamScheduleRepo extends JpaRepository<StudentExamSchedule, Long>{

	/**
	 * 
	 */
	@Query("select u from StudentExamSchedule u where u.CandidateId = ?1")
	StudentExamSchedule findByCandidateId(String candidateId);

}
