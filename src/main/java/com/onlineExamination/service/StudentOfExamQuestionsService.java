/**
 * 
 */
package com.onlineExamination.service;

import java.util.List;

import com.onlineExamination.entity.DataOfStudent;
import com.onlineExamination.entity.StudentOfExamQuestions;



/**
 * @author Lenovo
 *
 */
public interface StudentOfExamQuestionsService {

	/**
	 * @param forwordQuesIndicate
	 * @return
	 */
	StudentOfExamQuestions getQuestionByQuid(int forwordQuesIndicate);

	/**
	 * @param questionCode
	 * @return
	 */
	StudentOfExamQuestions getOptionsStatus(String questionCode);

	/**
	 * @param candidateId
	 * @return
	 */
	List<StudentOfExamQuestions> getSeletedOptionsStatusByCondidateId(String candidateId);

}
