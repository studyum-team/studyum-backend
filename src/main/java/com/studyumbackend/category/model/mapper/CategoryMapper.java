package com.studyumbackend.category.model.mapper;

import com.studyumbackend.category.model.dto.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    // 카테고리 전체 목록 조회
    List<Category> selectCategoryList(Long categoryId);

    // 카테고리 단건 조회
    Category selectCategoryById(Long categoryId);
}
