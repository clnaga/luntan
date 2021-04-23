package com.emiyacc.luntan.service;

import com.emiyacc.luntan.dto.QuestionsDTO;
import com.emiyacc.luntan.mapper.QuestionsMapper;
import com.emiyacc.luntan.mapper.UserMapper;
import com.emiyacc.luntan.model.Question;
import com.emiyacc.luntan.model.User;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsService {

    @Resource
    private QuestionsMapper questionsMapper;
    @Resource
    private UserMapper userMapper;

    public List<QuestionsDTO> list() {
        List<Question> questions = questionsMapper.list();
        List<QuestionsDTO> questionsDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findByID(question.getCreator());
            QuestionsDTO questionsDTO = new QuestionsDTO();
            BeanUtils.copyProperties(question, questionsDTO);
            questionsDTO.setUser(user);
            questionsDTOList.add(questionsDTO);
        }
        return questionsDTOList;
    }
}
