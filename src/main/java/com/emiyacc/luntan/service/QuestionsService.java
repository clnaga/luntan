package com.emiyacc.luntan.service;

import com.emiyacc.luntan.dto.PaginationDTO;
import com.emiyacc.luntan.dto.QuestionsDTO;
import com.emiyacc.luntan.mapper.QuestionsMapper;
import com.emiyacc.luntan.mapper.UserMapper;
import com.emiyacc.luntan.model.Question;
import com.emiyacc.luntan.model.User;
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

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionsMapper.count();
        paginationDTO.setPagniation(totalCount, page, size);
        // 简单的页码异常处理
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size * (page - 1);

        List<Question> questions = questionsMapper.list(offset, size);
        List<QuestionsDTO> questionsDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findByID(question.getCreator());
            QuestionsDTO questionsDTO = new QuestionsDTO();
            BeanUtils.copyProperties(question, questionsDTO);
            questionsDTO.setUser(user);
            questionsDTOList.add(questionsDTO);
        }
        paginationDTO.setQuestionsDTOList(questionsDTOList);

        return paginationDTO;
    }
}
