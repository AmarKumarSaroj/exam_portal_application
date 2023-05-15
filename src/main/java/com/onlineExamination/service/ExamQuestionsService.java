/**
 * 
 */
package com.onlineExamination.service;

import java.util.List;

import com.onlineExamination.entity.ExamQuestions;



/**
 * @author Lenovo
 *
 */
public interface ExamQuestionsService {

	/**
	 * @return
	 */

    ExamQuestions getQuestions(String subjectName);

	/**
	 * @param quesid
	 * @return
	 */
	ExamQuestions getCorrectValueOfDB(long quesid);

	/**
	 * @param forwordQuesIndicate
	 * @return
	 */
	
	List<ExamQuestions> getAllQuestions();

	ExamQuestions saveExamQuestions(ExamQuestions examQuestions);
	 

}
