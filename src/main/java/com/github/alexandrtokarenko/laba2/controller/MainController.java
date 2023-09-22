package com.github.alexandrtokarenko.laba2.controller;

import com.github.alexandrtokarenko.laba2.service.CalucalatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
public class MainController {

    private CalucalatorService calucalatorService;

    @PostMapping("/calculate")
    public String calculateIntegral(Model model, @RequestParam("threads") int numberOfThreads, @RequestParam("intervals") int numberOfIntervals) throws ExecutionException, InterruptedException {

        calucalatorService = new CalucalatorService();
        calucalatorService.calculate(numberOfIntervals,numberOfThreads);
        long time = calucalatorService.getTime();
        double result = calucalatorService.getTotalSum();


        model.addAttribute("time",time);
        model.addAttribute("result",result);
        return "result";
    }
}
