package com.studyumbackend.category.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    // 카테고리 고유 식별자 (Auto)
    private long categoryId;
    // 카테고리 이름
    private String categoryName;
    // 상위 카테고리 ID
    private long parentId;
}
