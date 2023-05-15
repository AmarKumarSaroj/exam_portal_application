/**
 * 
 */
package com.onlineExamination.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlineExamination.entity.ExamQuestions;





/**
 * @author Lenovo
 *
 */
@Repository
public interface ExamQuestionsRepo extends JpaRepository<ExamQuestions, Long>  {

	@Query("select u from ExamQuestions u where u.quid = ?1")
	ExamQuestions getQuestionsByRandom(long quid);
	
	@Query("select u from ExamQuestions u where u.quid = ?1")
	ExamQuestions findCorrectOptionByQuid(long quid);

	@Query("select q from ExamQuestions q")
	List<ExamQuestions> getAllQuestions();

	/**
	 * @return
	 */
	List<ExamQuestions> findByChrCourseCode(String chrCourseCode);

	/**
	 * @return
	 */
	 
}
