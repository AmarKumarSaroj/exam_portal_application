package com.onlineExamination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlineExamination.entity.ExamDetails;
import com.onlineExamination.entity.User;
import com.onlineExamination.repository.ExamDetailsRepo;
import com.onlineExamination.repository.UserRepo;

@Controller
public class UserController {

	@Autowired
	UserRepo userRepo;

	@Autowired
	ExamDetailsRepo examDetailsRepo;

	@GetMapping("/")
	public String homepage() {
		return "home";
	}

	@GetMapping("/instruction")
	public String users(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}

	@PostMapping("/loginUser")
	public String loginUser(@ModelAttribute("user") User user, Model model) {
		String username = user.getUsername();
		User userdata = userRepo.findByUsername(username);

		System.out.println(user.getMobileNumber() + "  " + userdata.getUsername());

		if (user.getMobileNumber().equals(userdata.getMobileNumber())) {
			ExamDetails examDetailsAttributes = examDetailsRepo.findByCandidateId(user.getUsername());
			System.out.println(examDetailsAttributes);
			model.addAttribute("examDetails", examDetailsAttributes);

			return "Instruction";

		} else {
			return "error";

		}

	}

}
