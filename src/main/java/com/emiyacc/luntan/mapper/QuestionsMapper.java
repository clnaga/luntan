package com.emiyacc.luntan.mapper;

import com.emiyacc.luntan.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionsMapper {
    @Insert("insert into questions (title, description, creator, tag, gmt_create, gmt_modified)" +
            "value (#{title}, #{description}, #{creator}, #{tag}, #{gmtCreate}, #{gmtModified})")
    void create(Question question);

    @Select("select * from questions")
    List<Question> list();
}
