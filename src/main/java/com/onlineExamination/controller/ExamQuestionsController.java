package com.onlineExamination.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.onlineExamination.commonUtils.CommonUtils;
import com.onlineExamination.entity.DataOfStudent;
import com.onlineExamination.entity.ExamDetails;
import com.onlineExamination.entity.ExamQuestions;
import com.onlineExamination.entity.StudentExamSchedule;
import com.onlineExamination.entity.StudentOfExamQuestions;
import com.onlineExamination.repository.ExamDetailsRepo;
import com.onlineExamination.repository.ExamQuestionsOptionsRepo;
import com.onlineExamination.repository.ExamQuestionsRepo;
import com.onlineExamination.repository.StudentExamScheduleRepo;
import com.onlineExamination.service.StudentOfExamQuestionsService;
import com.onlineExamination.service.ExamQuestionsService;

@Controller
public class ExamQuestionsController {

	@Autowired
	ExamQuestionsService examQuestionsService;

	@Autowired
	ExamQuestionsRepo examQuestionsRepo;

	@Autowired
	StudentOfExamQuestionsService studentOfExamQuestionsService;

	@Autowired
	StudentExamScheduleRepo studentExamScheduleRepo;

	@Autowired
	ExamDetailsRepo examDetailsRepo;

	@Autowired
	ExamQuestionsOptionsRepo examQuestionsOptionsRepo;

	@Autowired
	CommonUtils commonUtils;

// coder start point from here
	List<DataOfStudent> list = new ArrayList<>();
	int forwordQuesIndicate = 0;
	int OptionSelectedViewTrakar = 0;
	List<String> optionSelectedViewList = new ArrayList<>();
	ExamDetails holdSubjectNameAndCondidateId = new ExamDetails();
	String UserValueOfOption[];
	String quesId;
	String getQuestion;
	String questionCode;
	int onceTimeStudentScheduleCounter = 0;
	String endTimeFromStudentExamTbl;

	@PostMapping("/question")
	public String question(@ModelAttribute ExamQuestions examQuestions, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws ParseException {
		int correctValue = 0, wrongValue = 0, skipped = 0;
		int allStoreDataListSize = list.size();
		String operationType = request.getParameter("TypeOperation");
		String questionCode = request.getParameter("questionCode");
		String quesId = request.getParameter("quesId");
		String UserValueOfOption[] = request.getParameterValues("getValueSelectedByUser");
		String getQuestion = request.getParameter("Questions");
		String subjectName = request.getParameter("subjectName");
		String candidateId = request.getParameter("candidateId");

		assigningValue(UserValueOfOption, quesId, getQuestion, questionCode);
		// System.out.println( "Final operationType :"+operationType +"
		// allStoreDataListSize :"+ allStoreDataListSize+" forwordQuesIndicate :"+
		// forwordQuesIndicate );

		if ((subjectName != null) && (candidateId != null)) {
			holdSubjectNameAndCondidateId.setCandidateId(candidateId);
			holdSubjectNameAndCondidateId.setSubjectName(subjectName);
		}

		if (operationType.equalsIgnoreCase("save") && allStoreDataListSize == forwordQuesIndicate) {
			System.out.println("operations 1-");
			optionSelectedViewList.add(questionCode);
			System.out.println("operations 2-");
			// System.out.println("optionSelectedViewList :"+optionSelectedViewList);
			List<String> opList = commonUtils.generateExamResult(UserValueOfOption, quesId, getQuestion, questionCode,
					holdSubjectNameAndCondidateId.getCandidateId());
			System.out.println("operations 3-");
			OptionSelectedViewTrakar++;

		} else if (operationType.equalsIgnoreCase("submit")) {

			List<String> opList = commonUtils.generateExamResult(UserValueOfOption, quesId, getQuestion, questionCode,
					holdSubjectNameAndCondidateId.getCandidateId());

			for (String options : opList) {

//			System.out.println(options);

				if (options.equalsIgnoreCase("[true]")) {
					correctValue++;
				} else if (options.equalsIgnoreCase("[skipped]")) {
					skipped++;
				} else if (options.equalsIgnoreCase("[wrong]")) {
					wrongValue++;
				}
			}
//			
			redirectAttributes.addFlashAttribute("correctValue", correctValue);
			redirectAttributes.addFlashAttribute("wrongValue", wrongValue);
			redirectAttributes.addFlashAttribute("skipped", skipped);
			return "redirect:/result";

		} else if (operationType.equalsIgnoreCase("previous")) {
			// previous operations start
			forwordQuesIndicate--;
			OptionSelectedViewTrakar--;
			// System.out.println( " previous operationType :"+operationType +"
			// allStoreDataListSize :"+ allStoreDataListSize+" forwordQuesIndicate :"+
			// forwordQuesIndicate );

			DataOfStudent previousOp = list.get(forwordQuesIndicate - 1);
//			System.out.println("previousOp data priting here : "+previousOp);

			List<String> optionList = commonUtils.getOptionsListFromDBToShow(previousOp);
			commonUtils.addAttributesInModelToShow(model, previousOp, optionList);

//			System.out.println("previous condition colling times checking "); 
			String selectionType = previousOp.getChrQuestType();

			String quesCode = optionSelectedViewList.get(OptionSelectedViewTrakar);

			StudentOfExamQuestions getOptionSelectedForVeiw = commonUtils.getSelectedOptionByQuesCode(quesCode);

			String selectedOptionString = getOptionSelectedForVeiw.getSelectedOption();

			List<String> selectedOptionStringList = new ArrayList<String>(
					Arrays.asList(selectedOptionString.split(",")));

			String date = commonUtils.getTodayDate();
			// ExamDetails getEndTime =
			// commonUtils.getEndTimeByDateAndCandidateId(date,holdSubjectNameAndCondidateId.getCandidateId());

			model.addAttribute("optionsListSelected", selectedOptionStringList);
			model.addAttribute("selectionType", selectionType);
			model.addAttribute("questionPosition", forwordQuesIndicate);
			model.addAttribute("endTime", endTimeFromStudentExamTbl);
			return "Questions";

		} else if (operationType.equalsIgnoreCase("save") && allStoreDataListSize > forwordQuesIndicate) {
			// previous operations start
			// System.out.println("List printed :"+list);

			DataOfStudent previousOp = list.get(forwordQuesIndicate);
			// System.out.println("check-1");
			commonUtils.updateExamQuestionsResult(questionCode, UserValueOfOption, quesId, model, previousOp);
			// System.out.println("save with middle condition colling times checking
			// previousOp :; "+previousOp);
			// System.out.println("check-2");
			String selectionType = previousOp.getChrQuestType();
			OptionSelectedViewTrakar++;
			// System.out.println("check-3 OptionSelectedViewTrakar :
			// "+OptionSelectedViewTrakar +" optionSelectedViewList
			// "+optionSelectedViewList+" "+optionSelectedViewList.size());
			if (OptionSelectedViewTrakar < optionSelectedViewList.size()) {
				String quesCode = optionSelectedViewList.get(OptionSelectedViewTrakar);

				// System.out.println("check-4 "+OptionSelectedViewTrakar);
				StudentOfExamQuestions getOptionSelectedForVeiw = commonUtils.getSelectedOptionByQuesCode(quesCode);

				// System.out.println("__________getOptionSelectedForVeiw_______"+getOptionSelectedForVeiw.getSelectedOption()+"
				// quesCode :"+quesCode);

				String selectedOptionString = getOptionSelectedForVeiw.getSelectedOption();
				List<String> selectedOptionStringList = new ArrayList<String>(
						Arrays.asList(selectedOptionString.split(",")));
				// System.out.println("selectedOptionStringList :"+selectedOptionStringList);
				model.addAttribute("optionsListSelected", selectedOptionStringList);
			}

			String date = commonUtils.getTodayDate();
			// ExamDetails getEndTime =
			// commonUtils.getEndTimeByDateAndCandidateId(date,holdSubjectNameAndCondidateId.getCandidateId());

			forwordQuesIndicate++;
			// System.out.println( "allStoreDataListSize > forwordQuesIndicate)
			// operationType :"+operationType +" allStoreDataListSize :"+
			// allStoreDataListSize+" forwordQuesIndicate :"+ forwordQuesIndicate );

			model.addAttribute("selectionType", selectionType);
			model.addAttribute("questionPosition", forwordQuesIndicate);
			model.addAttribute("endTime", endTimeFromStudentExamTbl);// studenSchedule get the EndTime

			return "Questions";
		}

//		String date  = commonUtils.getTodayDate(); 
//		ExamDetails getEndTime = commonUtils.getEndTimeByDateAndCandidateId(date, holdSubjectNameAndCondidateId.getCandidateId());
//		

		ExamDetails objExamDatils = examDetailsRepo.findByCandidateId(holdSubjectNameAndCondidateId.getCandidateId());

		long examscheduleId = objExamDatils.getExamDetailsId();

		String examDuration = objExamDatils.getExamduration();
		int examDurationInInt = Integer.valueOf(examDuration);

		System.out.println(
				"getting end Time her ======+++++++++++++++++++++++++++++++++++++++++" + objExamDatils.getEndTime());
		System.out.println("getting start Time her ======+++++++++++++++++++++++++++++++++++++++++"
				+ objExamDatils.getStartTime());

//		    Calendar cal = Calendar.getInstance();
		Date getEndTimeObjDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(objExamDatils.getEndTime());
		Date getStartTimeObjDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(objExamDatils.getStartTime());

		long endTime = getEndTimeObjDate.getTime();

		long startTime = getStartTimeObjDate.getTime();

		Date currentTimeObj = new Date();

		long currentTime = currentTimeObj.getTime();

		if (startTime < currentTime && currentTime < endTime) {

			// adding value once time in the table
			if (onceTimeStudentScheduleCounter == 0) {

				StudentExamSchedule emptyObj = new StudentExamSchedule();

				emptyObj.setCandidateId(holdSubjectNameAndCondidateId.getCandidateId());// "yyyy-MM-dd HH:mm"

				SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				String time = localDateFormat.format(new Date());
				emptyObj.setExamStartTime(time);

				DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
				LocalDateTime ldt = LocalDateTime.now();

				LocalDateTime value = ldt.plusMinutes(examDurationInInt);

				String formattedString = value.format(fomatter);

				emptyObj.setExamEndTIme(formattedString);
				emptyObj.setExamScheduleId(examscheduleId);
				studentExamScheduleRepo.save(emptyObj);
				onceTimeStudentScheduleCounter++;

			}
			StudentExamSchedule objToGetEndTime = studentExamScheduleRepo
					.findByCandidateId(holdSubjectNameAndCondidateId.getCandidateId());

			endTimeFromStudentExamTbl = objToGetEndTime.getExamEndTIme();
			System.out.println(endTimeFromStudentExamTbl);

			// auto generate code for the questions
			String autoGenerateCode = commonUtils.autoGenerateQuestionsCode();
			// fetch and showing question on froenthend from the database
			// System.out.println("subjectName :"+subjectName);

			ExamQuestions gettingQuestionRandom = examQuestionsService
					.getQuestions(holdSubjectNameAndCondidateId.getSubjectName());
			DataOfStudent dataofstudent = commonUtils.getQuestionsAttributes(gettingQuestionRandom, autoGenerateCode);
			List<String> optionList = commonUtils.getOptionsListFromDBToShow(dataofstudent);
			commonUtils.addAttributesInModelToShow(model, dataofstudent, optionList);

			forwordQuesIndicate++;

			list.add(dataofstudent); /// sizelist ==1

			String selectionType = gettingQuestionRandom.getChrQuestType();
			model.addAttribute("questionPosition", forwordQuesIndicate);
			model.addAttribute("selectionType", selectionType);
			model.addAttribute("endTime", endTimeFromStudentExamTbl);

			return "Questions";

		} else {
			return "error";
		}

	}

	@GetMapping("/result")
	public String results() {

		return "Result";
	}

	@GetMapping("/results")
	public String result(RedirectAttributes redirectAttributes) {

		resultCounting(redirectAttributes);

		return "redirect:/result";
	}

	void assigningValue(String UserValueOfOption[], String quesId, String getQuestion, String questionCode) {

		this.UserValueOfOption = UserValueOfOption;
		this.quesId = quesId;
		this.getQuestion = getQuestion;
		this.questionCode = questionCode;

	}

	void resultCounting(RedirectAttributes redirectAttributes) {

		int correctValue = 0, wrongValue = 0, skipped = 0;
		List<String> opList = commonUtils.generateExamResult(UserValueOfOption, quesId, getQuestion, questionCode,
				holdSubjectNameAndCondidateId.getCandidateId());
		for (String options : opList) {
			if (options.equalsIgnoreCase("[true]")) {
				correctValue++;
			} else if (options.equalsIgnoreCase("[skipped]")) {
				skipped++;
			} else if (options.equalsIgnoreCase("[wrong]")) {
				wrongValue++;
			}
		}
		redirectAttributes.addFlashAttribute("correctValue", correctValue);
		redirectAttributes.addFlashAttribute("wrongValue", wrongValue);
		redirectAttributes.addFlashAttribute("skipped", skipped);
	}

//	
//	 @GetMapping("/options")
//	 public String getDetails() {
//	 List<ExamQuestions> questions = examQuestionsService.getAllQuestions();	
//	 ExamQuestionsOptions options = null;
//	 long currentId = 0;
//	 for (ExamQuestions quest : questions) {
//	 long currentQUid = quest.getQuid();
//	
//	 options = new ExamQuestionsOptions();
//	
//	 options.setQuid(currentQUid);
//	 options.setOptionValue(quest.getOption1());
//	 options.setQoid(1);
//	
//	 if (quest.getCorrectOption().split(",")[0].equalsIgnoreCase("1")) {
//	 options.setCorrectOption(true);
//	 }
//	
//	 examQuestionsOptionsRepo.save(options);
//	
//	 options = new ExamQuestionsOptions();
//	
//	 options.setQuid(currentQUid);
//	 options.setOptionValue(quest.getOption2());
//	 options.setQoid(2);
//	
//	 if (quest.getCorrectOption().split(",")[0].equalsIgnoreCase("2")) {
//	 options.setCorrectOption(true);
//	 }
//	
//	 examQuestionsOptionsRepo.save(options);
//	
//	 if(quest.getOption3() != null) {
//	 options = new ExamQuestionsOptions();
//	
//	 options.setQuid(currentQUid);
//	 options.setOptionValue(quest.getOption3());
//	 options.setQoid(3);
//	
//	 if (quest.getCorrectOption().split(",")[0].equalsIgnoreCase("3")) {
//	 options.setCorrectOption(true);
//	 }	
//	 examQuestionsOptionsRepo.save(options);
//	 }
//	
//	 if(quest.getOption4() != null) {
//	 options = new ExamQuestionsOptions();
//	
//	 options.setQuid(currentQUid);
//	 options.setOptionValue(quest.getOption4());
//	 options.setQoid(4);
//	
//	 if (quest.getCorrectOption().split(",")[0].equalsIgnoreCase("4")) {
//	 options.setCorrectOption(true);
//	 }
//	
//	 examQuestionsOptionsRepo.save(options);
//	 }
//	
//	
//	 }
//	 return "Questions";
//	
//	
//	 }
//	 

}
