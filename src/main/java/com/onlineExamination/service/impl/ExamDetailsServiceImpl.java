package com.onlineExamination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlineExamination.entity.ExamDetails;
import com.onlineExamination.repository.ExamDetailsRepo;
import com.onlineExamination.service.ExamDetailsService;

@Service
public class ExamDetailsServiceImpl implements ExamDetailsService {
	@Autowired
	ExamDetailsRepo examDetailsRepo;

	@Override
	public ExamDetails saveExamDetails(ExamDetails eds) {

		ExamDetails examDetails = new ExamDetails(

				eds.getExamDetailsId(), eds.getDescription(), eds.getTotalquestions(), eds.getWrongmarks(),
				eds.getCorrectmarks(), eds.getEndTime(), eds.getStartTime(), eds.getExamduration(),
				eds.getCandidateId(), eds.getSubjectName(), eds.getDates()

		);

		return examDetailsRepo.save(examDetails);
	}

}
