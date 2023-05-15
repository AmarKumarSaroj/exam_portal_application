/**
 * 
 */
package com.onlineExamination.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlineExamination.entity.ExamQuestions;
import com.onlineExamination.repository.ExamQuestionsRepo;
import com.onlineExamination.service.ExamQuestionsService;

@Service
public class ExamQuestionsServiceImpl implements ExamQuestionsService {

	@Autowired
	ExamQuestionsRepo examQuestionsRepo;
	List<Integer> listNonRepeatQuid = new ArrayList<>();

	@Override
	public ExamQuestions getQuestions(String subjectName) {
		List<ExamQuestions> quesList = examQuestionsRepo.findByChrCourseCode(subjectName);
		int min = 1;
		int max = quesList.size() - 1;
		int quidd = (int) (Math.random() * (max + 1 - min) + min);

		int questionLimitition = 60;

		for (int i = 0; listNonRepeatQuid.size() < questionLimitition; i++) {

			int quid = (int) (Math.random() * (max + 1 - min) + min);

			if (!(listNonRepeatQuid.contains(quid))) {

				ExamQuestions examQuestions = quesList.get(quid);
				// ExamQuestions examQuestions = examQuestionsRepo.getQuestionsByRandom(quid);
				listNonRepeatQuid.add(quid);
//				System.out.println("print value for the data "+listNonRepeatQuid.toString());
				return examQuestions;

			} else {

			}
		}
		return null;

	}

	@Override
	public ExamQuestions getCorrectValueOfDB(long quesId) {

		return examQuestionsRepo.findCorrectOptionByQuid(quesId);
	}

	@Override
	public List<ExamQuestions> getAllQuestions() {
		return examQuestionsRepo.getAllQuestions();
	}

	@Override
	public ExamQuestions saveExamQuestions(ExamQuestions eq) {

		ExamQuestions examQuestions = new ExamQuestions(eq.getQuestion(), eq.getOption1(), eq.getOption2(),
				eq.getOption3(), eq.getOption4(), eq.getCorrectOption(), eq.getChrCourseCode(), eq.getChrQuestType(),
				eq.getChrImagePath());

		return examQuestionsRepo.save(examQuestions);
	}

}
