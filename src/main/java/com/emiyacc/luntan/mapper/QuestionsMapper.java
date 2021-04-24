package com.emiyacc.luntan.mapper;

import com.emiyacc.luntan.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionsMapper {
    @Insert("insert into questions (title, description, creator, tag, gmt_create, gmt_modified)" +
            "value (#{title}, #{description}, #{creator}, #{tag}, #{gmtCreate}, #{gmtModified})")
    void create(Question question);

    @Select("select * from questions limit #{offset}, #{size}")
    List<Question> list(Integer offset, Integer size);

    @Select("select count(1) from questions")
    Integer count();

    @Select("select * from questions where creator = ${userId} limit #{offset}, #{size}")
    List<Question> getListByUserId(Integer userId, Integer offset, Integer size);

    @Select("select count(1) from questions where creator = ${userId}")
    Integer countByUserId(Integer Userid);
}
