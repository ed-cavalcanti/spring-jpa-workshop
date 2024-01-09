package com.testspring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testspring.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
