package com.onlineExamination.controller;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.onlineExamination.entity.ExamQuestions;
import com.onlineExamination.repository.ExamQuestionsRepo;
import com.onlineExamination.entity.ExamDetails;
import com.onlineExamination.service.ExamDetailsService;
import com.onlineExamination.service.ExamQuestionsService;

@Controller
public class AdminController {
	@Autowired
	ExamQuestionsService examQuestionsService;
	
	@Autowired
	ExamDetailsService examDetailsService;
	
	@Autowired
	ExamQuestionsRepo examQuestionsRepo;
	

	@GetMapping("/admin")
	public String adminPanel()
	{
		
		return "adminPanel";
	}
	
	//-----------------------MCQ Question insert handler------------------------------------------------------
	
	@GetMapping("/addquestion")
	public String examQuestion()
	{
		
		return "addQuestion";
	}
	
	@PostMapping("/saveExamquestion")
	public String saveExamQuestion(@ModelAttribute ExamQuestions examQuestions)	
    {		
		
	ExamQuestions saveObj = examQuestionsService.saveExamQuestions(examQuestions);
	
	if(Objects.nonNull(saveObj))
	{
		
		return "redirect:/addquestion?success";
	}
		
	return "redirect:/addquestion?success";
	   
	
   }
	
	
	//-----------------------ExamDetails Question insert    handler------------------------------------------------------
	
	@GetMapping("/addexamDetails")
	public String ExamDetails()
	{		
		return "addExamDetails";
		
	}	
	@PostMapping("/saveExamDetails")
	public String saveExamDetails(@ModelAttribute ExamDetails examDetails)	
    {		
		
	ExamDetails saveObj = examDetailsService.saveExamDetails(examDetails);
	
	if(Objects.nonNull(saveObj))
	{
		
		return "redirect:/addexamDetails?success";
	}
		
	return "redirect:/addexamDetails?success";
	   
	      }	
	
	//---------------------- Delete Questions handler ----------------------------------------------------
	@GetMapping("/deleteQuestion")
	public String deleteExamQuestion(Model model)
	{
		model.addAttribute("questionlist",examQuestionsRepo.findAll() );
		return "deleteQuestion";
	}	

		@GetMapping("/deleteQuestion/{quid}")
		public String deleteQuestion(@PathVariable("quid") long quid)
		{		
			examQuestionsRepo.deleteById(quid);
			return"redirect:/deleteQuestion?successfullyDeleted";
		}
	
	//---------------------- Update Questions handler ----------------------------------------------------
		@GetMapping("/updateQuestion")
		public String updateExamQuestion(Model model)
		{
			model.addAttribute("questionlist",examQuestionsRepo.findAll() );
			return "updateQuestion";
		}		
		
		@GetMapping("/updateQuestionPage/{quid}")
		public String showUpdateQuestionPage(@PathVariable("quid") long quid, Model model)
		{
			Optional<ExamQuestions> temp = examQuestionsRepo.findById(quid);
			ExamQuestions examques = temp.get();
			model.addAttribute("examques",examques);		
			
			return "addQuestion";
		}
		
	

	
	
}
