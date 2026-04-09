package com.studyumbackend.category.controller;

import com.studyumbackend.category.model.dto.Category;
import com.studyumbackend.category.model.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 목록 전체 조회
     * @param categoryId 부모 카테고리 ID(없으면 대분류, 있으면 소분류)
     * @return 카테고리 목록
     */
    @GetMapping
    public ResponseEntity<List<Category>> getCategoryList(
            @RequestParam(value = "categoryId", required = false) Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryList(categoryId));
    }


    /**
     * 카테고리 단건 조회
     * @param categoryId 부모 카테고리 ID(없으면 대분류, 있으면 소분류)
     * @return 카테고리 목록
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }
}
