package com.studyumbackend.category.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private long categoryId;
    private String categoryName;
    private ParentCategory parentCategory;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParentCategory {
        private long categoryId;
        private String categoryName;
    }
}
