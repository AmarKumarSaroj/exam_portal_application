/**
 * 
 */
package com.onlineExamination.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlineExamination.entity.ExamQuestionsOptions;



/**
 * @author Lenovo
 *
 */
@Repository
public interface ExamQuestionsOptionsRepo extends JpaRepository<ExamQuestionsOptions, Long>  {

	
	@Query("select u from ExamQuestionsOptions u where u.quid = ?1 and u.isCorrectOption = true")
	ExamQuestionsOptions getOptionsByQuestionAndIsCorrectOption(long quid);
	
//	@Query("select u from ExamQuestionsOptions u where u.quid = ?1 and u.isCorrectOption=true")
//	ExamQuestionsOptions findCorrectOptionByQuid(long quid);
	
	@Query("select u from ExamQuestionsOptions u where u.optionValue = ?1 and  u.quid = ?2 ")
	public ExamQuestionsOptions findCorrectOptionByOptionValue(String optionValue, long quid);

	/**
	 * @param quesId
	 * @return
	 */
//	@Query("select u from ExamQuestionsOptions u where u.quid = ?1")
//	List<ExamQuestionsOptions> findCorrectOptionByQuid(long quesId);
	
	
}
