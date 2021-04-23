package com.emiyacc.luntan.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionsDTO> questionsDTOList;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagniation(Integer totalCount, Integer page, Integer size) {
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }

        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i ++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 是否展示上一页
        showPrevious = page != 1;
        // 是否展示下一页
        showNext = !page.equals(totalPage);
        // 是否展示第一页
        showFirstPage = !pages.contains(1);
        // 是否展示最后一页
        showEndPage = !pages.contains(totalPage);
    }
}
