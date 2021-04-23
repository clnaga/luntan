package com.emiyacc.luntan.controller;

import com.emiyacc.luntan.dto.PaginationDTO;
import com.emiyacc.luntan.mapper.UserMapper;
import com.emiyacc.luntan.model.User;
import com.emiyacc.luntan.service.QuestionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private QuestionsService questionsService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name= "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (null != user) {
                        request.getSession().setAttribute("user", user);
                    }
                }
            }
        }

        PaginationDTO paginationDTO = questionsService.list(page, size);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }
}
