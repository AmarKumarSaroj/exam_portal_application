/**
 * 
 */
package com.onlineExamination.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private long startTime;

    @PostMapping("/start")
    public String startExam(Model model) {
        startTime = System.currentTimeMillis();
        model.addAttribute("timer", startTime);
        return "Questions";
    }
    
    @GetMapping("/time")
    public ResponseEntity<String> getElapsedTime() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        long seconds = elapsedTime / 1000;
        return ResponseEntity.ok("Elapsed time: " + seconds + " seconds");
    }

    
}

