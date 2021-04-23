package com.emiyacc.luntan.controller;

import com.emiyacc.luntan.dto.QuestionsDTO;
import com.emiyacc.luntan.mapper.QuestionsMapper;
import com.emiyacc.luntan.mapper.UserMapper;
import com.emiyacc.luntan.model.Question;
import com.emiyacc.luntan.model.User;
import com.emiyacc.luntan.service.QuestionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private QuestionsService questionsService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {

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

        List<QuestionsDTO> questionsList = questionsService.list();
        model.addAttribute("questions_list", questionsList);
        return "index";
    }
}
