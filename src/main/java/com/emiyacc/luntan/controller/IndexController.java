package com.emiyacc.luntan.controller;

import com.emiyacc.luntan.dto.PaginationDTO;
import com.emiyacc.luntan.service.QuestionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Resource
    private QuestionsService questionsService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name= "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

        PaginationDTO paginationDTO = questionsService.list(page, size);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }
}
