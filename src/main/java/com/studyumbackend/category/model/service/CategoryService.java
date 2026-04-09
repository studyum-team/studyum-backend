package com.studyumbackend.category.model.service;

import com.studyumbackend.category.model.dto.Category;

import java.util.List;

public interface CategoryService {
    // 카테고리 전체 목록 조회
    List<Category> getCategoryList(Long categoryId);

    // 카테고리 단건 조회
    Category getCategoryById(Long categoryId);

}
