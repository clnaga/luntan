package com.emiyacc.luntan.mapper;

import com.emiyacc.luntan.model.Question;
import org.apache.ibatis.annotations.Insert;

public interface QuestionsMapper {
    @Insert("insert into questions (title, description, creator, tag, gmt_create, gmt_modified, avatar_url)" +
            "value (#{title}, #{description}, #{creator}, #{tag}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void create(Question question);
}
