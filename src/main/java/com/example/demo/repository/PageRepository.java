package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Page;

public interface PageRepository extends JpaRepository<Page, Long> {

}
