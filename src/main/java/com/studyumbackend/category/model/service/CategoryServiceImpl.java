package com.studyumbackend.category.model.service;

import com.studyumbackend.category.model.dto.Category;
import com.studyumbackend.category.model.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    // 카테고리 전체 목록 조회
    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategoryList(Long categoryId){
        List<Category> categoryList = categoryMapper.selectCategoryList(categoryId);
        return categoryList;

    };

    // 카테고리 단건 조회
    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long categoryId){
        Category category = categoryMapper.selectCategoryById(categoryId);
        return category;
    };
}
