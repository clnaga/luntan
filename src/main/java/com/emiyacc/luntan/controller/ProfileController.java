package com.emiyacc.luntan.controller;

import com.emiyacc.luntan.dto.PaginationDTO;
import com.emiyacc.luntan.mapper.QuestionsMapper;
import com.emiyacc.luntan.mapper.UserMapper;
import com.emiyacc.luntan.model.User;
import com.emiyacc.luntan.service.QuestionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Resource
    UserMapper userMapper;
    @Resource
    QuestionsService questionsService;

    // 获取网页链接中值的另一种方法
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          Model model,
                          @RequestParam(name= "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (null != user) {
                        request.getSession().setAttribute("user", user);
                    }
                }
            }
        }

        if (null == user) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        }else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PaginationDTO paginationDTO = questionsService.list(user.getId(), page, size);
        model.addAttribute("paginationDTO", paginationDTO);
        return "profile";
    }
}
