package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

}
