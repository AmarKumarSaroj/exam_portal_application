package com.onlineExamination.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlineExamination.entity.DataOfStudent;
import com.onlineExamination.entity.StudentOfExamQuestions;


@Repository
public interface StudentOfExamQuestionsRepo extends  JpaRepository<StudentOfExamQuestions, Long>{
	
	
	@Query("select u from StudentOfExamQuestions u where u.id = ?1")
	StudentOfExamQuestions findById(long id);
	
	//stdUid 
//	@Query("select u from DataOfStudent u where u.stdUid = ?1")
//    DataOfStudent findByStdUid(long stdUid);

	/**
	 * @param questionCode
	 */
	StudentOfExamQuestions findByQuestionCode(String  questionCode);
	
	
	//void deactivateUsersNotLoggedInSince(@Param("date") LocalDate date);

	/** 
	 * @param listOptions
	 * @param statusOption
	 * @param questionCode
	 */
	@Transactional
	@Modifying 
	@Query("Update StudentOfExamQuestions u set u.statusOption = :statusOption where u.questionCode = :questionCode")
	int updateStudentOfExamQuestions(String statusOption, String questionCode);
	
	
	@org.springframework.data.jpa.repository.Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query("update StudentOfExamQuestions s set s.selectedOption = ?1 , s.statusOption = ?2 where s.questionCode= ?3")
	public void updateStatusOptionByQuestionCode(String selectedOption,String statusOption, String questionCode);

	/**
	 * @return
	 */
	List<StudentOfExamQuestions> findStatusOptionByCondidateId(String condidateId);

	/**
	 * @param quesCode
	 * @return
	 */
	StudentOfExamQuestions findSelectedOptionByQuestionCode(String quesCode);
	
 
	 
 
}
