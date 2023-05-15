/**
 * 
 */
package com.onlineExamination.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlineExamination.entity.StudentOfExamQuestions;
import com.onlineExamination.repository.StudentOfExamQuestionsRepo;
import com.onlineExamination.service.StudentOfExamQuestionsService;

@Service
public class StudentOfExamQuestionsImpl implements StudentOfExamQuestionsService {

	@Autowired
	StudentOfExamQuestionsRepo studentOfExamQuestionsRepo;
	
	@Override
	public StudentOfExamQuestions getQuestionByQuid(int forwordQuesIndicate) {		
		
		return studentOfExamQuestionsRepo.findById(forwordQuesIndicate);
	}

	@Override
	public StudentOfExamQuestions getOptionsStatus(String  questionCode) {
		
		StudentOfExamQuestions getOptionStatus = studentOfExamQuestionsRepo.findByQuestionCode(questionCode);		
		
		return getOptionStatus;
	}

	@Override
	public List<StudentOfExamQuestions> getSeletedOptionsStatusByCondidateId(String candidateId) {		
		
		
		return studentOfExamQuestionsRepo.findStatusOptionByCondidateId(candidateId);
	}

}
