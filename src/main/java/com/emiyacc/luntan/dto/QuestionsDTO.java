package com.emiyacc.luntan.dto;

import com.emiyacc.luntan.model.User;
import lombok.Data;

@Data
public class QuestionsDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private Long gmtCreate;
    private Long gmtModified;
    private User user;
}
