package com.onlineExamination.commonUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import com.onlineExamination.entity.DataOfStudent;
import com.onlineExamination.entity.ExamDetails;
import com.onlineExamination.entity.ExamQuestions;
import com.onlineExamination.entity.ExamQuestionsOptions;
import com.onlineExamination.entity.StudentExamSchedule;
import com.onlineExamination.entity.StudentOfExamQuestions;
import com.onlineExamination.repository.ExamDetailsRepo;
import com.onlineExamination.repository.ExamQuestionsOptionsRepo;
import com.onlineExamination.repository.ExamQuestionsRepo;
import com.onlineExamination.repository.StudentOfExamQuestionsRepo;
import com.onlineExamination.service.StudentOfExamQuestionsService;
import com.onlineExamination.service.ExamQuestionsService;

@Component
public class CommonUtils {

	@Autowired
	ExamQuestionsService examQuestionsService;

	@Autowired
	ExamQuestionsOptionsRepo examQuestionsOptionsRepo;

	@Autowired
	StudentOfExamQuestionsRepo studentOfExamQuestionsRepo;

	@Autowired
	StudentOfExamQuestionsService studentOfExamQuestionsService;

	@Autowired
	ExamDetailsRepo examDetailsRepo;

	@Autowired
	ExamQuestionsRepo examQuestionsRepo;	
	
	public DataOfStudent getQuestionsAttributes(ExamQuestions gettingQuestionRandom, String autoGenerateCode) {
		DataOfStudent dataofstudent = new DataOfStudent();		
		dataofstudent.setQid(gettingQuestionRandom.getQuid());
		dataofstudent.setChrCourseCode(gettingQuestionRandom.getChrCourseCode());
		dataofstudent.setChrImagePath(gettingQuestionRandom.getChrImagePath());
		dataofstudent.setChrQuestType(gettingQuestionRandom.getChrQuestType());
		dataofstudent.setCorrectOption(gettingQuestionRandom.getCorrectOption());
		dataofstudent.setQuestion(gettingQuestionRandom.getQuestion());
		dataofstudent.setOption1(gettingQuestionRandom.getOption1());
		dataofstudent.setOption2(gettingQuestionRandom.getOption2());
		dataofstudent.setOption3(gettingQuestionRandom.getOption3());
		dataofstudent.setOption4(gettingQuestionRandom.getOption4());
		dataofstudent.setQuestionCode(autoGenerateCode);
		dataofstudent.setChrQuestType(gettingQuestionRandom.getChrQuestType());

//		Use for the further data saving in the database
//		dataofstudent.setOption1(gettingQuestionRandom.getOption1());
//		dataofstudent.setOption2(gettingQuestionRandom.getOption2());
//		dataofstudent.setOption3(gettingQuestionRandom.getOption3());
//		dataofstudent.setOption4(gettingQuestionRandom.getOption4()); 	
		return dataofstudent;
	}
	
	

	public List<String> getOptionsListFromDBToShow(DataOfStudent dataofstudent) {

		List<String> optionList = new ArrayList<String>();
		optionList.add(dataofstudent.getOption1());
		optionList.add(dataofstudent.getOption2());
		optionList.add(dataofstudent.getOption3());
		optionList.add(dataofstudent.getOption4());

		return optionList;
	}

	public void addAttributesInModelToShow(Model model, DataOfStudent dataofstudent, List<String> optionList) {

		model.addAttribute("optionList", optionList);
		model.addAttribute("QuestionsObj", dataofstudent);

	}

	public boolean getResultStatus(String quesId, String[] userValueOfOption, List<String> optionsList) {

		int falseCounter = 0;
		int trueCounter = 0;
		long quid = Long.parseLong(quesId);
		boolean currectOptionStatus = false;

		System.out.println("option printing value : " + userValueOfOption);

		for (String userValueCheck : userValueOfOption) {

			System.out.println(userValueCheck + "            " + quid);

			ExamQuestionsOptions findCorrectOptions = examQuestionsOptionsRepo
					.findCorrectOptionByOptionValue(userValueCheck, quid);

			System.out.println("findCorrectOptions :  " + findCorrectOptions);

			boolean status = findCorrectOptions.isCorrectOption();

			if (status == true) {

				trueCounter++;

			} else {

				falseCounter++;

			}
		}

		// counting of the number of correct options
		ExamQuestions gettingCountOfCorrectOptins = examQuestionsRepo.findCorrectOptionByQuid(quid);

		String correctOptions = gettingCountOfCorrectOptins.getCorrectOption();

		String strArray[] = correctOptions.split(",");

		int noOfCorrectOptions = strArray.length;

		System.out.println("trueCounter :" + trueCounter + "  " + "falseCounter :" + falseCounter
				+ "    noOfCorrectOptions  :  " + noOfCorrectOptions);

		if (falseCounter == 0 && noOfCorrectOptions == trueCounter) {
			currectOptionStatus = true;
		}
		// currectOptionStatus
		return currectOptionStatus;

	}// end getResultCount() Method

	public void addOptionInList(List<String> optionsList, String[] userValueOfOption) {

		if (userValueOfOption == null) {

			optionsList.add("");

		} else {
			for (String option : userValueOfOption) {
				optionsList.add(option);
			}
		}

	}

	public String autoGenerateQuestionsCode() {

		Random randomGenerator = new Random();
		long value = randomGenerator.nextInt(1000000);
		String data = String.valueOf(value);

		return data;
	}

	public StudentOfExamQuestions saveStudentOfExamQuestions(List<String> optionsList, List<String> statusOptions,
			String questionCode, String candidateId) {
		String delim = ",";
		String selectedOptionString = String.join(delim, optionsList);
		StudentOfExamQuestions objStudentOfExamQuestions = new StudentOfExamQuestions();
		objStudentOfExamQuestions.setSelectedOption(selectedOptionString);
		objStudentOfExamQuestions.setStatusOption(statusOptions.toString());
		objStudentOfExamQuestions.setQuestionCode(questionCode);
		objStudentOfExamQuestions.setCondidateId(candidateId);
		// saveDataOfStudent.setQid(quids);
		return objStudentOfExamQuestions;
	}

	public List<String> generateExamResult(String[] userValueOfOption, String quesId, String getQuestion,
			String questionCode, String candidateId) {
		System.out.println(" ");
		List<String> optionsList = new ArrayList<>();
		List<String> statusOptions = new ArrayList<>();
		addOptionInList(optionsList, userValueOfOption);

		if (userValueOfOption != null) {
			boolean resultStatus = getResultStatus(quesId, userValueOfOption, optionsList);
			if (resultStatus) {
				statusOptions.add("true");
			} else {
				statusOptions.add("wrong");
			}

		} else {
			statusOptions.add("skipped");
		}

		// save questions and options in the DataOfStudent in database
		StudentOfExamQuestions saveStudentOfExamQuestionsObj = saveStudentOfExamQuestions(optionsList, statusOptions,
				questionCode, candidateId);

		studentOfExamQuestionsRepo.save(saveStudentOfExamQuestionsObj);

		List<StudentOfExamQuestions> getSeletedOptionsStatus = studentOfExamQuestionsService
				.getSeletedOptionsStatusByCondidateId(candidateId);

		List<String> opList = new ArrayList<>();

		for (StudentOfExamQuestions option : getSeletedOptionsStatus) {
			String opn = option.getStatusOption();
			opList.add(opn);
		}

		return opList;

	}

	public CommonUtils() {
		super();

	}

	public void updateExamQuestionsResult(String questionCode, String[] UserValueOfOption, String quesId, Model model,
			DataOfStudent previousOp) {
		StudentOfExamQuestions optionsOfStatus = studentOfExamQuestionsService.getOptionsStatus(questionCode);
		List<String> optionsList = new ArrayList<>();
		List<String> statusOptions = new ArrayList<>();

		if (UserValueOfOption == null) {
			optionsList.add("");
		} else {
			for (String option : UserValueOfOption) {
				optionsList.add(option);
			}
		}

		// getting result with Map return Type
		if (UserValueOfOption != null) {

			boolean resultStatus = getResultStatus(quesId, UserValueOfOption, optionsList);

			String optionStatusFromDB = optionsOfStatus.getStatusOption();

			if (resultStatus) {
				statusOptions.add("true");

			} else {
				statusOptions.add("wrong");
			}

		} else {

			statusOptions.add("skipped");
		}

		String delim = ",";

		String selectedOptionString = String.join(delim, optionsList);
		String listOptions = selectedOptionString;
		String statusOption = statusOptions.toString();
		/*
		 * System.out.println("listOptions : "+listOptions+"       "+
		 * "statusOption : "+statusOption+"     "+" questionCode : "+questionCode);
		 */
		studentOfExamQuestionsRepo.updateStatusOptionByQuestionCode(listOptions, statusOption, questionCode);

		List<String> optionList = getOptionsListFromDBToShow(previousOp);

		addAttributesInModelToShow(model, previousOp, optionList);

	}

	public StudentOfExamQuestions getSelectedOptionByQuesCode(String quesCode) {
		// TODO Auto-generated method stub
		return studentOfExamQuestionsRepo.findSelectedOptionByQuestionCode(quesCode);
	}

	public ExamDetails getEndTimeByDateAndCandidateId(String formattedDate, String condidateId) {
		// TODO Auto-generated method stub

		return examDetailsRepo.findEndTimeByDatesAndCandidateId(formattedDate, condidateId);
	}

	public String getTodayDate() {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
		// Define the desired date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		// Format today's date using the desired format
		String formattedDate = today.format(formatter);
		return formattedDate;
	}

}// end class
